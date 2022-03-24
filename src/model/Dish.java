package model;

import java.util.ArrayList;
import java.util.List;

public class Dish extends Product{

	private double totalWeight;
	private List<Ingredient> ingredients; 
	
	public Dish() {
		ingredients = new ArrayList<>();
	}

	public double getTotalWeight() {
		return totalWeight;
	}

	public void setTotalWeight(double totalWeight) {
		this.totalWeight = totalWeight;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
}
