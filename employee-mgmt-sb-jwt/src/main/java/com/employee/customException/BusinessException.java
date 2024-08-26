package com.employee.customException;

public class BusinessException extends RuntimeException {
	
	 
	private static final long serialVersionUID = 1L;
	private String string;

	public BusinessException(String string) {
		super();
		this.string = string;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "BusinessException [string=" + string + "]";
	}

	 
	
	
	

}
