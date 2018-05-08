package com.advisor.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.advisor.model.entity.User;
import com.advisor.model.request.NewUserRequest;
import com.advisor.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@RestController
public class LoginController {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity createNewUser(@Valid @RequestBody NewUserRequest newUserRequest) {
		User userExists = userService.findUserByEmail(newUserRequest.getEmail());
		if (userExists == null) {
            userService.saveUser(newUserRequest);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.IM_USED);
		}
	}

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public ResponseEntity afterLogin() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity hello() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/login")
    public ResponseEntity login() {
        return buildTokenResponse(((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    @RequestMapping(value = "/login/google")
    public ResponseEntity loginFacebook(String s) {
        HttpServletRequest currentRequest =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                        .getRequest();
        return buildTokenResponse(((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
    }

    private ResponseEntity buildTokenResponse(String principal) {
        String token = Jwts.builder()
                .setSubject(principal)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        return ResponseEntity.ok().header(HEADER_STRING, TOKEN_PREFIX + token).build();
    }
}
