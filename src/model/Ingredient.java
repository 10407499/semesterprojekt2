package model;

public class Ingredient {

	private String description;
	private int ingredientNo;
	
	public Ingredient(String description, int ingredientNo) {
		this.description = description;
		this.ingredientNo = ingredientNo;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the ingredientNo
	 */
	public int getIngredientNo() {
		return ingredientNo;
	}

	/**
	 * @param ingredientNo the ingredientNo to set
	 */
	public void setIngredientNo(int ingredientNo) {
		this.ingredientNo = ingredientNo;
	}
	
	
	
}
