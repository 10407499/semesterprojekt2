package model;

public abstract class Product {
	
	private String description;
	private double price;
	private int productNo;
	private String type;
	private CourseType courseType;
	
	protected Product(String description, double price, int productNo, String type, CourseType courseType) {
		this.description = description;
		this.price = price;
		this.productNo = productNo;
		this.type = type;
		this.courseType = courseType;
	}
	
	protected Product(String description, double price, String type, CourseType courseType) {
		this.description = description;
		this.price = price;
		this.type = type;
		this.courseType = courseType;
	}
	
	public int getProductNo() {
		return productNo;
	}
	
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the courseType
	 */
	public CourseType getCourseType() {
		return courseType;
	}
	
	
}


