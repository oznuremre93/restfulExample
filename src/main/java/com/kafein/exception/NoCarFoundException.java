package com.kafein.exception;

import java.util.function.Supplier;

public class NoCarFoundException extends Exception {

	private String message;
	
	public NoCarFoundException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


}
