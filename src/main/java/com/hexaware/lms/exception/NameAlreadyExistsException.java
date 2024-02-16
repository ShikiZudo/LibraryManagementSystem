package com.hexaware.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NameAlreadyExistsException extends Exception{
	private String message;

	public NameAlreadyExistsException(String message) {
		super(message);
	}
	
}
