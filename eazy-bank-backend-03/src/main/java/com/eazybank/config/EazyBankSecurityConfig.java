package com.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
	 * @return
	 * Approach 1 where we use withDefaultPasswordEncoder() method to create UserDetails 
	 */
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		
//		UserDetails admin = User.withDefaultPasswordEncoder()
//				.username("admin")
//				.password("admin")
//				.authorities("admin")
//				.build();
//		UserDetails user = User.withDefaultPasswordEncoder()
//				.username("user")
//				.password("user")
//				.authorities("user")
//				.build();
//				
//		return new InMemoryUserDetailsManager(admin, user);
//	}
	
	/**
	 * @return
	 * Approach 2 where we use NoOpPasswordEncoder bean to create UserDetails 
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails admin = User.withUsername("admin")
				.password("admin")
				.authorities("admin")
				.build();
		UserDetails user = User.withUsername("user")
				.password("user")
				.authorities("user")
				.build();
				
		return new InMemoryUserDetailsManager(admin, user);
	}
	
	/**
	 * @return
	 * NoOpPasswordEncoder is not recommended to use in Production.
	 * use only in non-prod.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
