package com.onkar.employeedetails.exceptionhandling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler 
{
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) 
	{
		String errorMsg = "No handler found for Http " + ex.getHttpMethod() + " request with url - " + ex.getRequestURL();
		CustomErrorMessage customErrorMsg = new CustomErrorMessage(HttpStatus.NOT_FOUND, errorMsg);
		return new ResponseEntity<Object>(customErrorMsg, customErrorMsg.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		String errorMsg = "Http " + ex.getMethod() +" request method not supported. Supported methods are " + ex.getSupportedHttpMethods();
		CustomErrorMessage customErrorMsg = new CustomErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, errorMsg);
		return new ResponseEntity<Object>(customErrorMsg, customErrorMsg.getStatus());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleAllExceptions(Exception ex)
	{
		log.info(ex.getClass().getName());
		log.error("Error", ex);
		CustomErrorMessage customErrorMsg = new CustomErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured");
		return new ResponseEntity<Object>(customErrorMsg, customErrorMsg.getStatus());
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFound(Exception ex)
	{
		CustomErrorMessage customErrorMsg = new CustomErrorMessage(HttpStatus.BAD_REQUEST, "Bad Request");
		return new ResponseEntity<Object>(customErrorMsg, customErrorMsg.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		String errorMsg = "Http media response type " + ex.getContentType() + " not supported.";
		CustomErrorMessage customErrorMsg = new CustomErrorMessage(HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorMsg);
		return new ResponseEntity<Object>(customErrorMsg, customErrorMsg.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String errorMsg = "Method Argument " + ex.getParameter() + " not supported.";
		CustomErrorMessage customErrorMsg = new CustomErrorMessage(HttpStatus.BAD_REQUEST, errorMsg);
		return new ResponseEntity<Object>(customErrorMsg, customErrorMsg.getStatus());
	}
	
}
