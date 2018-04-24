package com.tehnomarket.util;

public class HashPassword {

	private static int workload = 13;
	private static HashPassword instance;
	
	private HashPassword() {}
	
	public static HashPassword getInstance() {
		if(instance == null) {
			instance = new HashPassword();
		}
		return instance;
	}
	
	
	// hashes the password with BCrypt
	public static String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}
	
	
	//check password
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
}
	
}
