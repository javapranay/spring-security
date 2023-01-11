package com.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class EazyBankSecurityConfig {
	
	
	/**
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 * Below is the custom security configuration
	 */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
			.requestMatchers("/myAccount", "/myBalance", "/myCards", "/myLoans").authenticated()
			.requestMatchers("/notices", "/contact").permitAll();
		http.formLogin();
		http.httpBasic();
		return http.build();
	}
	
	
	/**
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 * Configuration to deny all requests, not recommended in production
	 */
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().denyAll()
//			.and().formLogin()
//			.and().httpBasic();
//		return http.build();
//	}
	
	/**
	 * @param http
	 * @return SecurityFilterChain
	 * @throws Exception
	 * Configuration to permits all requests, dev env for quick debug, not recommended in production
	 */
//	@Bean
//	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().permitAll()
//			.and().formLogin()
//			.and().httpBasic();
//		return http.build();
//	}
}
