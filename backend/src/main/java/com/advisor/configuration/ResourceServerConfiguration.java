package com.advisor.configuration;

import com.advisor.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Autowired
	private OAuth2ClientContextFilter oauth2ClientContextFilter;

	@Autowired
	private LoginAuthenticationHandler handler;

	@Autowired
	private Oauth2LoginAuthenticationHandler oauth2Handler;

	@Autowired
	private JWTManager jwtManager;

    @Value("${frontend.url.parent}")
    String frontendUrl;

    @Value("${frontend.url.main}")
    String dashboardUrl;

	RequestMatcher csrfRequestMatcher = new RequestMatcher() {

		// Enabled CSFR protection on the following urls:
		private AntPathRequestMatcher[] requestMatchers = {
				new AntPathRequestMatcher("/logout"),
				new AntPathRequestMatcher("/login")
		};

		@Override
		public boolean matches(HttpServletRequest request) {
			// If the request match one url the CSFR protection will be enabled
			for (AntPathRequestMatcher rm : requestMatchers) {
				if (rm.matches(request)) { return true; }
			}
			return false;
		}

	};

	@Autowired
	AuthenticationManager authenticationManager;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

	}

    @Override
	public void configure(HttpSecurity http) throws Exception {

		http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
				.addFilterBefore(filterChain(), UsernamePasswordAuthenticationFilter.class)
				.logout().disable()
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).requireCsrfProtectionMatcher(csrfRequestMatcher)
				.and()
			    .authorizeRequests()
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
                .antMatchers("/registrationConfirm").permitAll()
				.antMatchers("/admin/**").hasAuthority("ADMIN")
				.and()
				.cors();
	}

	private Filter filterChain() throws Exception {
		List<Filter> filters = new ArrayList<Filter>();
        filters.add(new PreLoginFilter("/login/**", jwtManager, frontendUrl + dashboardUrl));
		filters.add(ssoFilter(facebook(), "/login/facebook"));
		filters.add(ssoFilter(google(), "/login/google"));

		JWTAuthenticationFilter loginFilter = new JWTAuthenticationFilter(authenticationManager,jwtManager);
		loginFilter.setAuthenticationSuccessHandler(handler);
		loginFilter.setAuthenticationFailureHandler(handler);
		loginFilter.setFilterProcessesUrl("/login");
		filters.add(loginFilter);

		CompositeFilter compositeFilter = new CompositeFilter();
		compositeFilter.setFilters(filters);
		return compositeFilter;
	}

	private Filter ssoFilter(ClientResources client, String path) {
		OAuth2ClientAuthenticationProcessingFilter oAuth2ClientAuthenticationFilter = new OAuth2ClientAuthenticationProcessingFilter(path);
		OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(client.getClient(), oauth2ClientContext);
		oAuth2ClientAuthenticationFilter.setRestTemplate(oAuth2RestTemplate);
		UserInfoTokenServices tokenServices = new UserInfoTokenServices(client.getResource().getUserInfoUri(),
				client.getClient().getClientId());
		tokenServices.setRestTemplate(oAuth2RestTemplate);
		oAuth2ClientAuthenticationFilter.setTokenServices(tokenServices);
		oAuth2ClientAuthenticationFilter.setAuthenticationSuccessHandler(oauth2Handler);
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
    public FilterRegistrationBean corsConfigFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(frontendUrl);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(-100);
        return bean;
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
