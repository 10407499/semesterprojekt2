package model;

public abstract class Product {
	private String description;
	private double price;
	private int productNo; 
	
	
	
	public Product(String description, double price, int productNo) {
		this.description = description;
		this.price = price;
		this.productNo = productNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getDescription() {return description;}
	public double getPrice() {return price;}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}


