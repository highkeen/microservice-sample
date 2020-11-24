package com.microservice.identity.exception;

import lombok.Getter;

@Getter
public class MicroserviceIdentityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int statusCode;

	public MicroserviceIdentityException() {
		super();
	}

	
	public MicroserviceIdentityException(String message,int statusCode, Throwable cause) {
		super(message, cause);
		this.statusCode=statusCode;
	}
	
	public MicroserviceIdentityException(String message,int statusCode) {
		super(message);
		this.statusCode=statusCode;
	}

	

}
