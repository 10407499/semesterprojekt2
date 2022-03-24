package model;

public abstract class Product {
	private String description;
	private double price;
	
	public Product() {
		//Do nothing yet
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


