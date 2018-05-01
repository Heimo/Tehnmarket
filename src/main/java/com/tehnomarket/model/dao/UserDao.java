package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
import com.tehnomarket.model.User;


@Component
public class UserDao {

	private static UserDao instance;
	@Autowired
	private static DBManager manager;
	
	public static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	public static void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users(email, first_name, last_name, password, gender, birth_date, is_admin) VALUES (?, ?, ?, ?,?,?,?)";
		Connection connection = DBManager.getInstance().getConnection();
		
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getFirstName());
		ps.setString(3, u.getLastName());
		ps.setString(4, u.getPassword());
		ps.setString(5, u.getGender());
		ps.setDate(6, u.getDateOfBirth());
		ps.setBoolean(7, false);
		ps.executeUpdate();
	}

	public static User getUser(String email, String pass) throws SQLException {
		String sql = "SELECT id, email, first_name, last_name, password, gender, birth_date, is_admin FROM users WHERE email = ? AND password = ?";
		//PreparedStatement ps = manager.getConnection().prepareStatement(sql);
		
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, pass);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			return new User(result.getInt("id"),
					result.getString("email"),
					result.getString("first_name"),
					result.getString("last_name"),
					result.getString("password"),
					result.getString("gender"),
					result.getDate("birth_date"),
					result.getBoolean("is_admin"));
		}
		else {
			return null;
		}
	}

	public static String getHashPass(String email) throws SQLException {
		String sql = "SELECT password FROM users WHERE email = ? ";
		PreparedStatement ps = DBManager.getInstance().getConnection().prepareStatement(sql);
		ps.setString(1, email);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			return result.getString("password");
		}
		else {
			return null;
		}
	}

	public static void editUser(User u) throws SQLException {
		String sql = "UPDATE users SET password=?,first_name=?,last_name=?,birth_date=?,gender=? WHERE id=?";
		
		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, u.getPassword());
		ps.setString(2, u.getFirstName());
		ps.setString(1, u.getLastName());
		ps.setDate(4, u.getDateOfBirth());
		ps.setString(5, u.getGender());
		ps.setInt(1, (int)u.getId());
		
		ps.executeUpdate();
	}

	
	
}
