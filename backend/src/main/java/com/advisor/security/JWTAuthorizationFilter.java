package com.advisor.security;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

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

    public JWTAuthorizationFilter(JWTManager jwtManager, RequestMatcher matcher) {
        super("/");
        this.setRequiresAuthenticationRequestMatcher(matcher);
        this.jwtManager = jwtManager;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("Jwt authentication finished. Updating SecurityContextHolder to contain: "
                    + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request,response);
        return;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return Optional.ofNullable((AbstractAuthenticationToken)jwtManager.authenticateJwt(request, response))
                .orElseGet(() -> new AnonymousAuthenticationToken("No valid jwt token found", "anonymousUser", AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")));
    }
}
