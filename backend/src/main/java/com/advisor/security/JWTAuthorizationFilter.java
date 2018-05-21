package com.advisor.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    private JWTManager jwtManager;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTManager jwtManager, AuthenticationFailureHandler failureHandler, String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        this.jwtManager = jwtManager;
        this.setAuthenticationFailureHandler(failureHandler);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request,response);
        return;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return Optional.ofNullable(jwtManager.authenticateJwt(request, response))
                .orElseThrow(() -> new BadCredentialsException("Invalid jwt token"));
    }
}
