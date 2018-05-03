package com.tehnomarket.model;

public class Characteristics {

	private int productsId;
	private int characteristicsId;
	private String name;
	private String input;
		
	public Characteristics() {}
	
	public Characteristics(int productsId, int characteristicsId, String name, String input) {
		super();
		this.productsId = productsId;
		this.characteristicsId = characteristicsId;
		this.name = name;
		this.input = input;
	}
	
	public int getProductsId() {
		return productsId;
	}
	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}
	public int getCharacteristicsId() {
		return characteristicsId;
	}
	public void setCharacteristicsId(int characteristicsId) {
		this.characteristicsId = characteristicsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	
	
	
	
}
