package com.advisor.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("SESJA KURWA!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
