package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.employee.config.JwtUtil;
import com.employee.model.JwtRequest;
import com.employee.model.JwtResponse;
import com.employee.service.UserDetailsServiceImpl;

@RestController
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//generate token
	
	@PostMapping("/generateToken")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			System.out.println(jwtRequest);
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
		} catch (UsernameNotFoundException e) {
			throw new Exception("User not found !!");
			
		}
		 
		//authenticate
		
		UserDetails userDetails = detailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtil.generateToken(userDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));	
	}
	
	private void authenticate(String username, String password) throws Exception {
		
		try {
			 
			//authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			 
		} catch (DisabledException e ) {
			throw new Exception("User Disabled");
		}catch (BadCredentialsException e) {
		throw new Exception("Invalid Credintial");
		}
	}
}
