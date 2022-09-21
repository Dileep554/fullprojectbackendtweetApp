package com.tweetapp.api.model;
public enum ErrorMessages {
	
	INVALID_CREDENTIALS(1,"Invalid Username Or Password Exception"),
	AUTHORIZATION_EXCEPTION(2,"This request is not authorized please login again"),
	USER_ALREADY_EXISTS(3,"This User already exists..!"),
	INCORRECT_OR_DELETED_TWEET(4,"IncorrectOrDeletedTweet");
	

	private String message;
	private int id;

	ErrorMessages(int id, String message) {
		this.id=id;
		this.message=message;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}
}
