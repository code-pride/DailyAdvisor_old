package com.advisor.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends AbstractAuthenticationProcessingFilter {

    private JWTManager jwtManager;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JWTManager jwtManager, String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        this.jwtManager = jwtManager;
    }

    /*@Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken authentication = jwtManager.authenticateJwt(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }*/

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return jwtManager.authenticateJwt(request, response);
    }
}
