package com.tehnomarket.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class DBManager {
	
	private static DBManager instance;
	private static Connection con;
	
	private static final String DB_NAME = "tehnomarket";
	private static final String DB_IP = "127.0.0.1";
	private static final String DB_PORT = "3306";
	private static final String DB_USERNAME = "root";
	private static final String DB_PASSWORD = "rootrootroot";
	
	private DBManager() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not loaded.");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://"+DB_IP+":"+DB_PORT+"/"+DB_NAME + "?characterEncoding=utf8", DB_USERNAME, DB_PASSWORD);
			System.out.println("GOT CONNECTION SQL");
			
		} catch (SQLException e) {
			System.out.println("Connection to database failed.");
		}
		
		this.con=con;
		
	}
	
	public static DBManager getInstance() {
		if(instance == null) {
			instance = new DBManager();
		}
		return instance;
}
	
	
	public Connection getConnection() {
		return con;
	}

}
