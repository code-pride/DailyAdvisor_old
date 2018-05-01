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

	/*@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//resources.
	}*/

    @Override
	public void configure(HttpSecurity http) throws Exception {

		http.
			authorizeRequests()
				//.antMatchers("/oauth/authorize").authenticated()
				//.antMatchers("/login").permitAll()
				//.antMatchers("/").permitAll()
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

	/*@Override
	public void configure(WebSecurity web) {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}*/

	/*@Bean
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
	}*/
}
