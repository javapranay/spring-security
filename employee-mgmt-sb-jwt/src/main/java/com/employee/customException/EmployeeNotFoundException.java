package com.employee.customException;

public class EmployeeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private String string;

	public EmployeeNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
