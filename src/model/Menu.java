package model;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Product {
	
	private List<Dish> dishes;

	public Menu() {
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
