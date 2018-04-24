package com.tehnomarket.util.exception;

public class UserDataException extends Exception{
	
	private String wrongValue;
	
	public UserDataException(String wrongVal) {
		super("Invalid user credentials:  " + wrongVal);
	}

}
