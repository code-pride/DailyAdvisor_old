package com.advisor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2LoginAuthenticationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    @Autowired
    JWTManager jwtManager;

    @Value("${frontend.url.parent}")
    String frontendUrl;

    @Value("${frontend.url.main}")
    String dashboardUrl;

    @Value("${frontend.url.login}")
    String loginUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        jwtManager.jwtLogin(request,response,authentication);
        response.sendRedirect(frontendUrl + "/#" + dashboardUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.sendRedirect(frontendUrl + "/#" + loginUrl);
    }
}
