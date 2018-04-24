package com.tehnomarket.model;

import java.sql.Date;
import java.sql.Timestamp;

public class User {
	
	private long id;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String gender;
	private Date dateOfBirth;
	private boolean isAdmin;
	
	
	public User(String email, String firstName, String lastName, String password, String gender, Date dateOfBirth,
			boolean isAdmin) {
		super();
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.isAdmin = isAdmin;
	}
	
	public User(long id, String email, String firstName, String lastName, String password, String gender, Date dateOfBirth,
			boolean isAdmin) {
		this(email,firstName,lastName,password,gender,dateOfBirth,isAdmin);
		this.id = id;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public String getFirstName() {
		return firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public String getPassword() {
		return password;
	}


	public String getGender() {
		return gender;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public boolean isAdmin() {
		return isAdmin;
	}
	
	
}
