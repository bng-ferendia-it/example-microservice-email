package com.ferendia.emailservice.security;

import com.ferendia.emailservice.filter.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;




@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	AuthenticationFilter authenticationFilter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().httpBasic().disable().csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
		.antMatchers("/send/**", "/v2/api-docs", "/configuration/ui", "/auth/password/**", "/swagger-resources/**", "/configuration/**",
		"/swagger-ui.html").permitAll().and().authorizeRequests()
		.antMatchers("/test/admin")
		.hasAuthority(AuthoritiesConstants.ADMIN).and().authorizeRequests()
		.antMatchers("/test/user")
		.hasAnyAuthority(AuthoritiesConstants.USER, AuthoritiesConstants.ADMIN).and().exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
		
		http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}
}
