package model;

public class Dish extends Product{

	private Measurement measurement;
	private double quantity;
	
	/**
	 * This constructor is used if its being built from database
	 * @param description
	 * @param price
	 * @param productNo
	 * @param type
	 * @param measurement
	 * @param courseType
	 * @param quantity
	 */
	
	public Dish(String description, double price, int productNo, String type, CourseType courseType, Measurement measurement, double quantity) {
		super(description, price, productNo, type, courseType);
		this.measurement = measurement;
		this.quantity = quantity;
	}

	/**
	 * This constructor is used if a new dish is being made
	 * @param description
	 * @param price
	 * @param productNo
	 * @param type
	 */
	
	public Dish(String description, double price, String type, CourseType courseType) {
		super(description, price, type, courseType);
	}

	public double getQuantity() {
		return quantity;
	}

	public Measurement getMeasurement() {
		return measurement;
	}
}
