package com.tehnomarket.model;

public class Characteristics {

	private int id;
	private String name;
	private Categories category;
	
	public Characteristics(int id,String name,Categories cat) {
		this.id=id;
		this.name=name;
		this.category=cat;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}
	
	
	
	
}
