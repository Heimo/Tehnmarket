package com.tehnomarket.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.tehnomarket.util.HashPassword;

public class User {
	
	private long id;
	
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	private String firstName;
	
	@NotNull
	@NotEmpty
	private String lastName;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String passwordCheck;
	
	@NotNull
	@NotEmpty
	private String gender;
	
	@NotNull
	@NotEmpty
	
	private Date dateOfBirth;
	
	private boolean isAdmin;
	
	
	public User() {}
	
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
		
	
	public User(String firstName, String lastName, String password, String passwordCheck, String gender,
			Date dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.passwordCheck = passwordCheck;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPasswordCheck() {
		return passwordCheck;
	}

	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	
	
}
