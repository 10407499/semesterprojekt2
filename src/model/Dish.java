package model;

import java.util.ArrayList;
import java.util.List;

public class Dish extends Product{

	private Measurement measurement;
	private CourseType courseType;
	private double quantity;
	private List<IngredientLine> ingredients;
	
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
	
	public Dish(String description, double price, int productNo, String type, Measurement measurement, CourseType courseType, double quantity) {
		super(description, price, productNo, type);
		ingredients = new ArrayList<>();
		this.measurement = measurement;
		this.courseType = courseType;
		this.quantity = quantity;
	}
	
	/**
	 * This constructor is used if a new dish is being made
	 * @param description
	 * @param price
	 * @param productNo
	 * @param type
	 */
	
	public Dish(String description, double price, String type) {
		super(description, price, type);
		ingredients = new ArrayList<>();
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public List<IngredientLine> getIngredients() {
		return ingredients;
	}

	/**
	 * @return the measurement
	 */
	public Measurement getMeasurement() {
		return measurement;
	}

	/**
	 * @param measurement the measurement to set
	 */
	public void setMeasurement(Measurement measurement) {
		this.measurement = measurement;
	}
	
	
	
}
