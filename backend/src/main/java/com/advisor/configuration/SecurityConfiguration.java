package com.advisor.configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;

import com.advisor.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
@Order(200)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

    @Autowired
    private OAuth2ClientContextFilter oauth2ClientContextFilter;

    @Autowired
    private LoginAuthenticationSuccessHandler successHandler;

    @Autowired
	private JWTManager jwtManager;

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.
			jdbcAuthentication()
				.usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource)
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		http
				.csrf().disable()
				.logout().addLogoutHandler(new LogoutHandler()).logoutRequestMatcher(new AntPathRequestMatcher("/logout1"))
				.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
                .csrf().disable()
				.authorizeRequests()
				.antMatchers("/oauth/authorize").authenticated()
				.antMatchers("/**").permitAll()
				.and()
                .requestMatchers()
                .antMatchers("/oauth/authorize")
				//.antMatchers("/logout")
                .and()
                .addFilterBefore(new JWTAuthorizationFilter(authenticationManager(),jwtManager), BasicAuthenticationFilter.class)
				.cors();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedHeaders(Collections.singletonList("*"));
		configuration.setAllowedOrigins(Collections.singletonList("*"));
		configuration.setAllowedMethods(Collections.singletonList("*"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	@Bean
	public FilterRegistrationBean corsFilterBean() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		bean.setOrder(0);
		return bean;
	}


    @Bean
    public FilterRegistrationBean authenticationFilterRegistration() throws Exception {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
        filter.setAuthenticationSuccessHandler(successHandler);
        registration.setFilter(filter);
        registration.addUrlPatterns("/login");
        registration.setName("authenticationFilter");
        registration.setOrder(-100);
        return registration;
    }

	/*@Bean
	public FilterRegistrationBean authorizationFilterRegistration() throws Exception {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		JWTAuthorizationFilter filter = new JWTAuthorizationFilter(authenticationManager(),jwtManager);
		registration.setFilter(filter);
		registration.addUrlPatterns("/oauth/authorize");
		registration.addUrlPatterns("/logout");
		registration.setName("authenticationFilter");
		registration.setOrder(-100);
		return registration;
	}*/

    @Bean
    public FilterRegistrationBean facebookFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ssoFilter(facebook(), "/login/facebook"));
        registration.setName("facebookFilter");
        registration.setOrder(-102);
        return registration;
    }

    @Bean
    public FilterRegistrationBean googleFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ssoFilter(google(), "/login/google"));
        registration.setName("googleFilter");
        registration.setOrder(-102);
        return registration;
    }

    private Filter ssoFilter(ClientResources client, String path) {
        OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
        oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
                client.getClient().getClientId());
        tokenServices.setRestTemplate(oAuth2RestTemplate);
        oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
        oAuth2ClientAuthenticationFilter.setAuthenticationSuccessHandler(successHandler);
        return oAuth2ClientAuthenticationFilter;
    }

	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(
			OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-103);
		return registration;
	}

    @Bean
    @ConfigurationProperties("google")
    public ClientResources google() {
        return new ClientResources();
    }

    @Bean
    @ConfigurationProperties("facebook")
    public ClientResources facebook() {
        return new ClientResources();
    }

}