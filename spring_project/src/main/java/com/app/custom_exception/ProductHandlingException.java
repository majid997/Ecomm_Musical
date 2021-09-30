package com.app.custom_exception;

@SuppressWarnings("serial")
public class ProductHandlingException extends RuntimeException {
	public ProductHandlingException(String mesg) {
		super(mesg);
	}
}