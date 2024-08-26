package com.employee.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employee.service.UserDetailsServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		System.out.println(header);
		String username = null;
		String jwtToken = null;
		if (header != null && header.startsWith("Bearer ")) {
			jwtToken = header.substring(7);
			try {
				username = this.jwtUtil.extractUsername(jwtToken);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("invaid token !!");
		}

		// validation

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			final UserDetails details = this.detailsServiceImpl.loadUserByUsername(username);

			if (this.jwtUtil.validateToken(jwtToken, details)) {
				// token is valid
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						details, null, details.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}else {
				System.out.println("token is not valid");
			}
			
			
		}
		
		System.out.println("jdkdskdjskdjksjd");
		filterChain.doFilter(request,response);	
	}
	

  }
