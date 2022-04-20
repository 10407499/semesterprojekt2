package controller;

import java.sql.Date;
import java.util.List;

import model.Order;
import model.OrderLine;
import model.Product;
import model.Role;

public interface OrderControllerIF {

	public Order createOrder();
	
	public void setOrderInfo(int coverAmount, Date fulfillmentdate);
	
	public void setCustomer(String name); 
	
	public void setDelivery(String deliveryAddress); 
	
	public void addService(Role role);
	
	public List<Product> findProducts(String description); 
	
	public void addProduct(int productNo, int quantity);
	
	public List<OrderLine> getOrderLines(); //TODO REMOVE THIS IS FOR TESTING
}
