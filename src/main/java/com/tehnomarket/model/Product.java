package com.tehnomarket.model;

import java.sql.Date;

public class Product {

	private int id;
	private String name;
	private String brand;
	private float price;
	private String info;
	private int discount;
	private Date discountEnd;
	private String image;
	private long categoryId;
	private Product theProduct = this;
	
	//will do a builder here
	
	public Product() {}
	
	public Product(int id, String name, String brand, float price,String info, int discount,Date discountEnd,String image,long categoryId) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
		this.info = info;
		this.discount = discount;
		this.discountEnd = discountEnd;
		this.image = image;
		this.categoryId = categoryId;
	}
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getBrand() {
		return brand;
	}
	public float getPrice() {
		return price;
	}
	public int getDiscount() {
		return discount;
	}
	public Date getDiscountEnd() {
		return discountEnd;
	}
	public String getImage() {
		return image;
	}
	public long getCategoryId() {
		return categoryId;
	}

	public String getInfo() {
		return info;
	}
	
	

	public Product getTheProduct() {
		return theProduct;
	}

	public void setTheProduct(Product theProduct) {
		this.theProduct = theProduct;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public void setDiscountEnd(Date discountEnd) {
		this.discountEnd = discountEnd;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Product other = (Product) obj;
		if(id==other.id) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	
	
}
