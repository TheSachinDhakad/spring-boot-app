package com.josh.config;

import javax.security.sasl.AuthorizeCallback;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.Management;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeRequests(Authorize -> Authorize
				.requestMatchers("/api/**").authenticated().anyRequest().permitAll())
		.httpBasic().and()
		.csrf(csrf -> csrf.disable());
		
		return httpSecurity.build();
		
	}
	

}
