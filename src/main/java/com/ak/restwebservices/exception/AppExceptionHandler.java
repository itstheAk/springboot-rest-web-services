package com.ak.restwebservices.exception;

import java.util.Calendar;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	 
	
	 @Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String msg = ex.getBindingResult().toString(); 
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		if(allErrors != null && !allErrors.isEmpty()) {
			for(ObjectError err : allErrors) {
				if(err != null) {
					msg = err.getDefaultMessage();
				}
				
			}
		}
		AppException exceptionDetails = new AppException(Calendar.getInstance().getTime(), ex.getMessage(), msg); 
		return new ResponseEntity<Object>(exceptionDetails, HttpStatus.BAD_REQUEST); 
	}


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
