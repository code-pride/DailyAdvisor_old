package com.advisor.controllers;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;

@Controller
public class CsrfController {

    CookieCsrfTokenRepository csrfRepository = CookieCsrfTokenRepository.withHttpOnlyFalse();

    @RequestMapping(value = "/csrf", method = RequestMethod.GET)
    public ResponseEntity setCsrf() {
        ServletRequestAttributes attributes =
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes());
        CsrfToken token = csrfRepository.generateToken(attributes.getRequest());
        csrfRepository.saveToken(token,attributes.getRequest(),attributes.getResponse());
        return ResponseEntity.ok("");
    }
}
