package com.rest.demo.exception;

public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		super("Could not find resource");
	}
	
}
