package com.advisor.security;

import com.advisor.model.entity.User;
import com.advisor.repository.UserRepository;
import com.advisor.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomJdbcTokenStore extends JdbcTokenStore {

    private static final Log LOG = LogFactory.getLog(JdbcTokenStore.class);

    private UserService userService;

    public CustomJdbcTokenStore(DataSource dataSource, UserService userService) {
        super(dataSource);
        this.userService = userService;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        try {
            accessToken = new DefaultOAuth2AccessToken(tokenValue);
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token "+tokenValue);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " +tokenValue,e);
            removeAccessToken(tokenValue);
        }

        return accessToken;
    }

    @Transactional
    @Override
    public OAuth2Authentication readAuthentication(String token) {
        OAuth2Authentication authentication = super.readAuthentication(token);
        if(authentication == null) {
            return null;
        }
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authentication.getUserAuthentication();
        User user = userService.findUserByEmail((String)authentication.getPrincipal());
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
        authenticationToken = new UsernamePasswordAuthenticationToken(authenticationToken.getPrincipal(),authenticationToken.getCredentials(),authorities);
        return new OAuth2Authentication(authentication.getOAuth2Request(),authenticationToken);
    }
}
