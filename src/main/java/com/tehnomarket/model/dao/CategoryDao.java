package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
import com.tehnomarket.model.Category;

@Component
public class CategoryDao {

	private static CategoryDao instance;
	private Connection connection;
	
	public static CategoryDao getInstance() {
		if(instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}
	
	private CategoryDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	
	public static Collection<Category> getAllCategories() throws SQLException{
		Collection<Category> categories = new ArrayList<Category>();
		String sql = "SELECT id, name FROM categories";

		Connection connection = DBManager.getInstance().getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			Category c = new Category(result.getInt("id"),
					result.getString("name"));
			categories.add(c);
		}
		return categories;
	}
}