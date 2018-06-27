package com.advisor.controllers;

import com.advisor.security.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
public class LogoutController {

    @Autowired
    private JWTManager jwtManager;

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout() {
        ServletRequestAttributes attributes =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
        jwtManager.jwtLogout(attributes.getRequest(),attributes.getResponse());
        return new ResponseEntity(HttpStatus.OK);
    }
}
