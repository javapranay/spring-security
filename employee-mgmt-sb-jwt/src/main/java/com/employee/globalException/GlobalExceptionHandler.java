package com.employee.globalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.employee.customException.BusinessException;
import com.employee.customException.EmployeeNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<String> employeeNotFoundexception(EmployeeNotFoundException  employeeNotFoundException){
		return  new ResponseEntity<String>("Employee is Not Found in DB",HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<String> handleEmptyInput(BusinessException businessException){
		return  new ResponseEntity<String>("Input field is Empty please fill in proper way",HttpStatus.BAD_REQUEST);
	}
	
}
