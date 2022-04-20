package model;

public class IngredientLine {

	private Measurement measurement;
	private int quantity;
	private Ingredient ingredient;
	private int productNo;
	
	public IngredientLine(Measurement measurement, int quantity, Ingredient ingredient, int productNo) {
		this.measurement = measurement;
		this.quantity = quantity;
		this.ingredient = ingredient;
		this.productNo = productNo;
	}
	
	public IngredientLine(Measurement measurement, int quantity, Ingredient ingredient) {
		this.measurement = measurement;
		this.quantity = quantity;
		this.ingredient = ingredient;
	}
	
	
	
}
