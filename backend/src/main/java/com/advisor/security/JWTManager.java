package com.advisor.security;

import com.advisor.model.entity.Role;
import com.advisor.model.entity.TokenJWT;
import com.advisor.model.entity.User;
import com.advisor.repository.TokenJWTRepository;
import com.advisor.repository.UserRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JWTManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenJWTRepository tokenJWTRepository;

    private static final String TOKEN_COOKIE_NAME = "_secu";
    private static final String SECRET = "SecretKeyToGenJWTs";
    private static final String [] roles = {"USER","ADMIN","COACH"};

    public void jwtLogout(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if(cookies == null) {
            return;
        }
        Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(TOKEN_COOKIE_NAME))
                .findFirst()
                .ifPresent(cookie -> {
                    Optional.ofNullable(cookie.getValue()).ifPresent(value -> {
                        Jws<Claims> jws = Jwts.parser()
                                .setSigningKey(SECRET.getBytes())
                                .parseClaimsJws(value);
                        String userName = jws.getBody().getSubject();
                        User user = userRepository.findByEmail(userName);
                        Optional.ofNullable(user).ifPresent(lUser -> {
                            if(tokenJWTRepository.existsById(UUID.fromString(jws.getBody().getId()))) {
                                TokenJWT jwtToken = tokenJWTRepository.getOne(UUID.fromString(jws.getBody().getId()));
                                tokenJWTRepository.delete(jwtToken);
                            }
                        });
                    });
                    cookie.setMaxAge(0);
                    res.addCookie(cookie);
                });
        return;
    }

    public void jwtLogin(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        User user = null;
        List<String> authorities = new ArrayList<>();
        if(principal instanceof org.springframework.security.core.userdetails.User) {
            user = userService.findUserByEmail(((org.springframework.security.core.userdetails.User) principal).getUsername());
            authorities = authentication.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority()).collect(Collectors.toList());
        } else {
            final String email = (String) ((Map) ((OAuth2Authentication) authentication).getUserAuthentication().getDetails()).get("email");
            user = Optional.ofNullable(userService.findUserByEmail(email)).orElseGet(() -> {
                return userService.registerOauth2User(email);
            });
            authorities = user.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
        }
        TokenJWT tokenEntity = new TokenJWT();
        tokenEntity.setTimestamp(new Date(System.currentTimeMillis()));
        tokenEntity.setUser(user);
        tokenEntity = tokenJWTRepository.save(tokenEntity);
        response.addCookie(createTokenCookie(user.getEmail(), authorities, tokenEntity.getTokenId().toString()));
    }

    public UsernamePasswordAuthenticationToken authenticateJwt(HttpServletRequest req, HttpServletResponse res) {
        Cookie[] cookies = req.getCookies();
        if(cookies == null) {
            return null;
        }
        Optional<Cookie> jwtCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(TOKEN_COOKIE_NAME) && cookie.getValue()!=null)
                .findFirst();
        if(jwtCookie.isPresent()) {
            Jws<Claims> jws = null;
            try {
                jws = Jwts.parser()
                        .setSigningKey(SECRET.getBytes())
                        .parseClaimsJws(jwtCookie.get().getValue());
            } catch (JwtException | IllegalArgumentException e) {
                return null;
            }
            String user = jws.getBody().getSubject();
            if (user != null
                    && tokenJWTRepository.existsById(UUID.fromString(jws.getBody().getId()))) {
                        res.addCookie(jwtCookie.get());
                        List<GrantedAuthority> authorities = new ArrayList<>();
                        for(String role : roles) {
                            if((boolean)jws.getBody().get(role)) {
                                authorities.add(new SimpleGrantedAuthority(role));
                            }
                        }
                        return new UsernamePasswordAuthenticationToken(user, null, authorities);
            }
        }
        return null;
    }

    private Cookie createTokenCookie(String subject, List<String> authorities, String id) {
        Cookie tokenCookie = new Cookie(TOKEN_COOKIE_NAME, createToken(subject, authorities, id));
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        return tokenCookie;
    }

    private String createToken(String subject, List<String> authorities, String id) {
        Claims claims = Jwts.claims()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setId(id);
        for(String role : roles) {
            claims.put(role, authorities.contains(role));
        }
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
    }
}
