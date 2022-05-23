package model;

public class Ingredient {

	private String description;
	private int ingredientNo;
	
	public Ingredient(String description, int ingredientNo) {
		this.description = description;
		this.ingredientNo = ingredientNo;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIngredientNo() {
		return ingredientNo;
	}

	public void setIngredientNo(int ingredientNo) {
		this.ingredientNo = ingredientNo;
	}
}
