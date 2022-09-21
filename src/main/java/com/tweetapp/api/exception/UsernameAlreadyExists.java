package com.tweetapp.api.exception;

public class UsernameAlreadyExists extends Exception{
	
	/**
	 * UsernameAlreadyExists Exception
	 */
	private static final long serialVersionUID = 1L;

	public UsernameAlreadyExists(String msg) {

		super(msg);
	}
}
