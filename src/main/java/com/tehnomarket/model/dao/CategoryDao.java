package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
import com.tehnomarket.model.Category;

@Component
public class CategoryDao {

	private Connection connection;
	
	private CategoryDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	
	public Collection<Category> getAllCategories() throws SQLException{
		Collection<Category> categories = new ArrayList<Category>();
		String sql = "SELECT id, name, parent_category FROM tehnomarket.categories order by coalesce(parent_category,id), parent_category IS NOT NULL,id";

		PreparedStatement ps = connection.prepareStatement(sql);
		
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			Category c = new Category(result.getInt("id"),
					result.getString("name"),
					result.getInt("parent_category"));
			categories.add(c);
		}
		return categories;
	}
}