package com.qa.ims.persistence.domain;

public class Product {

	private Long id;
	private String productName;
	private double price;

	public Product(String productName, double price) {
		this.productName = productName;
		this.price = price;
	}

	public Product(Long id, String productName, double price) {
		this.id = id;
		this.productName = productName;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setproductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "id:" + id + " product name:" + productName + " price:" + price;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (price == 0) {
			if (other.price != 0)
				return false;
		} else if (other.price != price)
			return false;
		return true;
	}

}
