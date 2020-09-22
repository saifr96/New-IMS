package com.qa.ims.persistence.domain;

public class OrderLine {

	private Long id;
	private Long orderID;
	private Long productID;
	private double price;
	
	public OrderLine(Long id, Long orderID, Long productID, double price) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.orderID = orderID;
		this.productID = productID;
		this.price = price;
	}

	public OrderLine(Long orderID, Long productID , double price) {
		// TODO Auto-generated constructor stub
		
		this.orderID = orderID;
		this.productID = productID;
		this.price = price;
	}



	public Long getId() {
		return id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Long getOrderID() {
		return orderID;
	}

	public void setOrderID(Long orderID) {
		this.orderID = orderID;
	}

	public Long getProductID() {
		return productID;
	}

	public void setProductID(Long productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "id:" + id +  " order_id:" + orderID + " product_id:" + productID + " price:" + price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderLine other = (OrderLine) obj;
		if (orderID == null) {
			if (other.orderID != null)
				return false;
		} else if (!orderID.equals(other.orderID))
			return false;
		if (price == 0) {
			if (other.price != 0)
				return false;
		} else if (other.price != price)
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		return true;
	}





	
		
	}


