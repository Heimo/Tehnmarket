package com.tehnomarket.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.tehnomarket.controller.DBManager;
import com.tehnomarket.model.Characteristics;
import com.tehnomarket.model.Review;

@Component
public class ReviewDao {

	private Connection connection;

	private ReviewDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public void saveReview(Review r) throws SQLException {
			String sql = "INSERT INTO reviews(users_id,products_id,rating,date,comment) VALUES (?,?,?,?,?)";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setInt(1, r.getUserId());
			ps.setInt(2, r.getProductId());
			ps.setInt(3,r.getRating());
			ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			ps.setString(5, r.getComment());
			ps.executeUpdate();
	}
	
	public ArrayList<Review> getAllProductReview(int productId) throws SQLException{
		ArrayList<Review> all = new ArrayList<Review>();
		//\r\n
		String sql = "SELECT review_id,users_id,products_id,rating,date,comment FROM reviews WHERE products_id=?";
		
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, productId);
		ResultSet result = ps.executeQuery();
		
		while(result.next()) {
			
			Review r = new Review(
							result.getInt("review_id"),
							result.getInt("users_id"),
							result.getInt("products_id"),
							result.getInt("rating"),
							result.getTimestamp("date"),
							result.getString("comment"));
			all.add(r);
			
		}
		
		return all;
	}

}
