package com.ajith.blog.exceptions;

public class UsernameAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExistsException() {
		super("Username already exists");
	}
}
