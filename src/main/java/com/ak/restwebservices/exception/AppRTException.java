package com.ak.restwebservices.exception;

public class AppRTException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6980081726204145078L;
	
	private String msg;
	
	public AppRTException(String msg) {
		super(msg);
	}
	
	
}
