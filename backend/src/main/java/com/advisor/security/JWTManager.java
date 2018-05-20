package com.advisor.security;

import com.advisor.model.entity.AccessToken;
import com.advisor.model.entity.TokenJWT;
import com.advisor.model.entity.User;
import com.advisor.repository.AccessTokenRepository;
import com.advisor.repository.TokenJWTRepository;
import com.advisor.repository.UserRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class JWTManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenJWTRepository tokenJWTRepository;

    @Autowired
    private AccessTokenRepository accessTokenRepository;

    private static final String TOKEN_COOKIE_NAME = "_secu";
    private static final String SECRET = "SecretKeyToGenJWTs";
    private static final long EXPIRATION_TIME = 864_000_0000L; // 100 days

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
                            List<AccessToken> tokens = accessTokenRepository.findByUserName(lUser.getEmail());
                            tokens.forEach(token -> accessTokenRepository.delete(token));
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
        if(principal instanceof org.springframework.security.core.userdetails.User) {
            user = userService.findUserByEmail(((org.springframework.security.core.userdetails.User) principal).getUsername());
        } else {
            final String email = (String) ((Map) ((OAuth2Authentication) authentication).getUserAuthentication().getDetails()).get("email");
            user = Optional.ofNullable(userService.findUserByEmail(email)).orElseGet(() -> {
                User lUser = new User();
                lUser.setEmail(email);
                try {
                    return userService.create(lUser);
                } catch (DataRepositoryException e) {
                    throw new RuntimeException();
                }
            });
        }
        TokenJWT tokenEntity = new TokenJWT();
        tokenEntity.setTimestamp(new Date(System.currentTimeMillis()));
        tokenEntity.setUser(user);
        tokenEntity = tokenJWTRepository.save(tokenEntity);
        response.addCookie(createTokenCookie(user.getEmail(),tokenEntity.getTokenId().toString()));
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
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey(SECRET.getBytes())
                    .parseClaimsJws(jwtCookie.get().getValue());
            String user = jws.getBody().getSubject();
            if (user != null
                    && tokenJWTRepository.existsById(UUID.fromString(jws.getBody().getId()))) {
                        res.addCookie(createTokenCookie(user,jws.getBody().getId()));
                        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
            }
        }
        return null;
    }

    private Cookie createTokenCookie(String subject, String id) {
        Cookie tokenCookie = new Cookie(TOKEN_COOKIE_NAME, createToken(subject,id));
        tokenCookie.setHttpOnly(true);
        tokenCookie.setPath("/");
        return tokenCookie;
    }

    private String createToken(String subject, String id) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .setId(id)
                .compact();
    }
}
