package com.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MysecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint  authenticationEntryPoint;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 auth.userDetailsService(this.detailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
		 .csrf()
		 .disable()
		 .cors()
		 .disable()
		 .authorizeRequests()
		 .antMatchers("/generateToken").permitAll()
		 .antMatchers(HttpMethod.OPTIONS).permitAll()
		 .anyRequest().authenticated()
		 .and()
		 .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		 .and()
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		 
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
