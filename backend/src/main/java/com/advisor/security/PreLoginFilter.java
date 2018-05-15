package com.advisor.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class PreLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTManager jwtManager;

    private String alreadyLoggedRedirectUrl;

    public PreLoginFilter(String defaultFilterProcessesUrl, JWTManager jwtManager, String alreadyLoggedRedirectUrl) {
        super(defaultFilterProcessesUrl);
        this.jwtManager = jwtManager;
        this.alreadyLoggedRedirectUrl = alreadyLoggedRedirectUrl;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return Optional.ofNullable((AbstractAuthenticationToken)jwtManager.authenticateJwt(request, response))
                .orElseGet(() -> new AnonymousAuthenticationToken("No valid jwt token found", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        if (authResult instanceof AnonymousAuthenticationToken) {
            chain.doFilter(request, response);
            return;
        }
        response.sendRedirect(alreadyLoggedRedirectUrl);
        return;
    }
}
