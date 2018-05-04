package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
import com.tehnomarket.model.User;


@Component
public class UserDao {

	private Connection connection;

	private UserDao() {
		connection = DBManager.getInstance().getConnection();
	}

	public void saveUser(User u) throws SQLException {
		String sql = "INSERT INTO users(email, first_name, last_name, password, gender, birth_date, is_admin) VALUES (?, ?, ?, ?,?,?,?)";

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

	public User getUser(String email, String pass) throws SQLException {
		String sql = "SELECT id, email, first_name, last_name, password, gender, birth_date, is_admin FROM users WHERE email = ? AND password = ?";
		//PreparedStatement ps = manager.getConnection().prepareStatement(sql);
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

	public String getHashPass(String email) throws SQLException {
		String sql = "SELECT password FROM users WHERE email = ? ";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			return result.getString("password");
		}
		else {
			return null;
		}
	}

	public void editUser(User u) throws SQLException {
		String sql = "UPDATE users SET password=?,first_name=?,last_name=?,birth_date=?,gender=? WHERE id=?";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, u.getPassword());
		System.out.println(u.getPassword());
		ps.setString(2, u.getFirstName());
		System.out.println(u.getFirstName());
		ps.setString(3, u.getLastName());
		System.out.println(u.getLastName());
		ps.setDate(4, u.getDateOfBirth());
		System.out.println(u.getDateOfBirth());
		ps.setString(5, u.getGender());
		System.out.println(u.getGender());
		ps.setInt(6, (int)u.getId());
		System.out.println(u.getId());

		ps.executeUpdate();
	}

	public User checkEmail(String email) throws SQLException {

		String sql = "SELECT id, email, first_name, last_name, password, gender, birth_date, is_admin FROM users WHERE email = ?";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, email);

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

}
