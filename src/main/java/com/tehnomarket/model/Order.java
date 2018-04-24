package com.tehnomarket.model;

import java.text.SimpleDateFormat;

public class Order {

	
	private long orderId;
	private SimpleDateFormat dateOfOrder;
	private boolean status;
	private int quantity;
	private SimpleDateFormat dateOfDelivery;
	private int discount;
	
	
	public Order(SimpleDateFormat dateOfOrder, boolean status, int quantity, SimpleDateFormat dateOfDelivery,
			int discount) {
		
		this.dateOfOrder = dateOfOrder;
		this.status = status;
		this.quantity = quantity;
		this.dateOfDelivery = dateOfDelivery;
		this.discount = discount;
	}


	public Order(long orderId,SimpleDateFormat dateOfOrder, boolean status, int quantity, SimpleDateFormat dateOfDelivery,
			int discount) {
		this(dateOfOrder,status, quantity, dateOfDelivery,discount);
		this.orderId = orderId;
	}


	public long getOrderId() {
		return orderId;
	}


	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}


	public SimpleDateFormat getDateOfOrder() {
		return dateOfOrder;
	}


	public void setDateOfOrder(SimpleDateFormat dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public SimpleDateFormat getDateOfDelivery() {
		return dateOfDelivery;
	}


	public void setDateOfDelivery(SimpleDateFormat dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}


	public int getDiscount() {
		return discount;
	}


	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
	
	
	
	
	
}
