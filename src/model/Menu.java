package model;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Product {
	
	private List<Dish> dishes;
	private String type;

	public Menu(String description, double price, int productNo, String type) {
		super(description, price, productNo, type);
		dishes = new ArrayList<>();
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

	public void addDish(Dish dish) {
		dishes.add(dish);
	}

	public List<Dish> getDishes() {
		return dishes;
	}

}
