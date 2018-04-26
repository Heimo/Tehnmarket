package com.tehnomarket.model;

public class Categories {

	private int categoryId;
	private String categoryName;
	private int parentCategoryId;
	
	public Categories(int id,String name){
		this.categoryId = id;
		this.categoryName = name;
	}
	
	public Categories(int id,String name,int parent) {
		this(id,name);
		this.parentCategoryId = parent;
	}
	
	
	public int getCategoryId() {
		return categoryId;
	}




	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}




	public String getCategoryName() {
		return categoryName;
	}




	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




	public int getParentCategoryId() {
		return parentCategoryId;
	}




	public void setParentCategoryId(int parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	
	
}
