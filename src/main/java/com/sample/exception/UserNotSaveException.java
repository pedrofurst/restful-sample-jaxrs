package com.sample.exception;

public class UserNotSaveException extends UserException {

	private static final long serialVersionUID = 1L;

	public UserNotSaveException() {
		super( "Couldn't save this user" );
	}

}
