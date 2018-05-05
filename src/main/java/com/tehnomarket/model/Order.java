package com.tehnomarket.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Order {

	private int id;
	private Date dateOfOrder;
	private double totalCost;
	// Status should have more than two conditions , it can be in processing, canceled, lost or delayed , not just delivered or not 
	private int status;
	private long userId;
	private String City;
	private String Street;
	private String Entrance;
	private int phoneNumber;
	private HashMap<Product,Integer> theOrders;
	
	public Order() {
		
	}
	
	public Order(String city, String street, String entrance, int phoneNumber) {
		super();
		City = city;
		Street = street;
		Entrance = entrance;
		this.phoneNumber = phoneNumber;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long l) {
		this.userId = l;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}

	public String getEntrance() {
		return Entrance;
	}

	public void setEntrance(String entrance) {
		Entrance = entrance;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public HashMap<Product, Integer> getTheOrders() {
		return theOrders;
	}

	public void setTheOrders(HashMap<Product, Integer> order) {
		this.theOrders = order;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Order [dateOfOrder=" + dateOfOrder + ", totalCost=" + totalCost + ", status=" + status + ", userId="
				+ userId + ", City=" + City + ", Street=" + Street + ", Entrance=" + Entrance + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}
