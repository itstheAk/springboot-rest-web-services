package com.ak.restwebservices.exception;

import java.util.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	 @ExceptionHandler(MethodArgumentTypeMismatchException.class) 
	 public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(Exception ex, WebRequest request) throws Exception { 
		 AppException exceptionDetails = new AppException(Calendar.getInstance().getTime(), ex.getMessage(), request.getDescription(true)); 
		 return new ResponseEntity<Object>(exceptionDetails, HttpStatus.BAD_REQUEST); 
	 }
	 
	
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) throws Exception {
		AppException exceptionDetails = new AppException(Calendar.getInstance().getTime(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<Object>(exceptionDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) throws Exception {
		AppException exceptionDetails = new AppException(Calendar.getInstance().getTime(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<Object>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	

}
