package com.gag.util.exceptions;

public class RegisterException extends Exception {

	public RegisterException(String message) {
		super("Invalid user credentials: " + message);
	}
}
