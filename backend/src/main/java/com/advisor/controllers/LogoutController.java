package com.advisor.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {
    @RequestMapping("/logout")
    public ResponseEntity logout() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
