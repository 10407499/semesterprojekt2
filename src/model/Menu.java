package model;

import java.util.List;

public class Menu {

	private String description; 
	private double price; 
	private List<Dish> dishes;
	
	
	public Menu(String description) {
		super();
		this.description = description;
		
	
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public void addDish(Dish dish) {
		dishes.add(dish);
	}
	


	public double getPrice() {
		return price;
	}


	public List<Dish> getDishes() {
		return dishes;
	} 
	
}
