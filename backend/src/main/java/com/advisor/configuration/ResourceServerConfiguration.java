package com.advisor.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Collections;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

	}

    @Override
	public void configure(HttpSecurity http) throws Exception {

		http.
			authorizeRequests()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/afterLogin").permitAll()
				.antMatchers("/populate").permitAll()
				.antMatchers("/hello").permitAll()
				.antMatchers("/getUserProfile/**").hasAuthority("USER")
                .antMatchers("/updateUserProfile").hasAuthority("USER")
                .antMatchers("/advertisement/**").hasAuthority("USER")
                .antMatchers("/upgradeToCoach/**").hasAuthority("USER")
				.antMatchers("/coaching/**").hasAuthority("COACH")
				.antMatchers("/client/**").hasAuthority("USER")
				.antMatchers("/meeting/**").hasAuthority("USER")
				.antMatchers("/diet/**").hasAuthority("USER")
				.antMatchers("/train/**").hasAuthority("USER")
				.antMatchers("/calendar/**").hasAuthority("USER")
		 		.antMatchers("/message/**").hasAuthority("USER")
				.antMatchers("/registration").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.and()
				.cors();
	}
}
