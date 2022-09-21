package com.tweetapp.api.exception;

public class InvalidUsernameOrPasswordException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUsernameOrPasswordException(String msg){
		super(msg);
	}
	
}
