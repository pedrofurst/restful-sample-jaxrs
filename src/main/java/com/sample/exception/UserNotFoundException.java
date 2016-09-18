package com.sample.exception;

public class UserNotFoundException extends UserException {

	private static final long serialVersionUID = 3597234505803926214L;

	public UserNotFoundException() {
		super( "User not found! :/" );
	}

}
