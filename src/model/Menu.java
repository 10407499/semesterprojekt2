package model;

import java.util.ArrayList;
import java.util.List;

public class Menu extends Product {
	
	private List<Dish> dishes;

	public Menu(String description, double price, int productNo, String type, CourseType courseType) {
		super(description, price, productNo, type, courseType);
		dishes = new ArrayList<>();
	}

	public void addDish(Dish dish) {
		dishes.add(dish);
	}

	public List<Dish> getDishes() {
		return dishes;
	}

}