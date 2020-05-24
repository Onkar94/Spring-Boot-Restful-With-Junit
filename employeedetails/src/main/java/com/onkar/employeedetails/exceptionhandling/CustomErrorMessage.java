package com.onkar.employeedetails.exceptionhandling;

import org.springframework.http.HttpStatus;

public class CustomErrorMessage extends Throwable {
	
	private static final long serialVersionUID = 8468579512282653907L;
	
	private HttpStatus status;
	private String errorMessage;
	
	public CustomErrorMessage()
	{
		//super();
	}
	
	public CustomErrorMessage(HttpStatus status, String errorMessage) {
		//super();
		this.status = status;
		this.errorMessage = errorMessage;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	@Override
	public synchronized Throwable fillInStackTrace() 
	{
		return this;
	}


}
