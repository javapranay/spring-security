package com.employee.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeRepo;
import com.employee.entity.Employee;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Employee employee = this.employeeRepo.findByUsername(username);
		if(employee==null) {
			throw new UsernameNotFoundException("User Not Found !!");
		}
		
		return (UserDetails) employee;
	}

}
