package com.qa.ims.persistence.domain;

import java.util.ArrayList;
import java.util.List;

public class Order {

	private Long id;
	private Long customerID;
	private List<OrderLine> orderLines;
	
	public Order(Long id, Long customerID) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.customerID = customerID;
		this.orderLines = new ArrayList<OrderLine>();
	}
		
	public Order(Long customerID) {
		// TODO Auto-generated constructor stub
		this.customerID = customerID;
		this.orderLines = new ArrayList<OrderLine>();
	}

	public OrderLine AddLine(Long productID, Double price) {
		OrderLine line = new OrderLine(getId(),productID, price);
		orderLines.add(line);
		return line;
	}
	
	public Long getId() {
		return id;
	}
	
	public Long getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(Long customerID) {
		this.customerID = customerID;
	}
	
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	@Override
	public String toString() {
		return "order_id:"+ id + " customer_id:" + customerID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customerID == null) {
			if (other.customerID != null)
				return false;
		} else if (!customerID.equals(other.customerID))
			return false;
		return true;
	}

	public Double getTotal() {
		// TODO Auto-generated method stub
		double Total = 0;
		for(OrderLine line : orderLines) {
			Total = Total + line.getPrice();
		}
		return Total;
	}





	
		
	}


