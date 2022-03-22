package model;

public class OrderLine {

	private int quantityMenu;
	private int quantityDish; 
	private Dish dish; 
	private Menu menu;
	public OrderLine(int quantityMenu, Menu menu) {
		super();
		this.quantityMenu = quantityMenu;
		this.menu = menu;
	}
	public OrderLine(int quantityDish, Dish dish) {
		super();
		this.quantityDish = quantityDish;
		this.dish = dish;
	}
	public int getQuantityMenu() {
		return quantityMenu;
	}
	public void setQuantityMenu(int quantityMenu) {
		this.quantityMenu = quantityMenu;
	}
	public int getQuantityDish() {
		return quantityDish;
	}
	public void setQuantityDish(int quantityDish) {
		this.quantityDish = quantityDish;
	}
	public Dish getDish() {
		return dish;
	}
	public void setDish(Dish dish) {
		this.dish = dish;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	} 
	
	
	
}
