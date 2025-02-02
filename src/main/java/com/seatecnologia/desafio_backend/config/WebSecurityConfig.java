package com.seatecnologia.desafio_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
		private static final String[] PUBLIC = {"/auth/login", "/h2-console/**"};
		
		private static final String[] ADMIN = {"/users/**", "/clientes/**"};
		
		private static final String[] OPERATOR_OR_ADMIN = {"/users/**", "/clientes/**"};
	
		@Autowired
		private SecurityFilter securityFilter;
	
	   @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	
	
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		   return httpSecurity
				   .csrf(csrf -> csrf.disable())
				   .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				   .authorizeHttpRequests(authorize -> authorize
						   .requestMatchers(PUBLIC).permitAll()
						   .requestMatchers(HttpMethod.GET,OPERATOR_OR_ADMIN ).hasAnyRole("OPERATOR","ADMIN")
						   .requestMatchers(ADMIN).hasRole("ADMIN")
						  .anyRequest().authenticated()
						   )
				   .headers(headers -> headers.frameOptions().disable())
				   .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				   .build();
	    }
	   
	   @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}