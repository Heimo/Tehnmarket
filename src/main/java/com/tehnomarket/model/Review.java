package com.tehnomarket.model;

import java.sql.Timestamp;

public class Review {

	private int id;
	private int userId;
	private int productId;
	private int rating;
	private String comment;
	private Timestamp creationDate;
	
	public Review() {}
	
	public Review(int userId, int productId, int rating,Timestamp creationDate, String comment) {
		this.userId = userId;
		this.productId = productId;
		this.rating = rating;
		this.comment = comment;
	}
	
	public Review(int id, int userId, int productId, int rating,Timestamp creationDate, String comment) {
		this(userId,productId,rating,creationDate,comment);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	
	
}
