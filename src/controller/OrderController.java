package controller;

import java.sql.Date;
import java.util.List;

import model.Customer;
import model.Order;
import model.Product;
import model.Role;

public class OrderController implements OrderControllerIF {

	private List<Product> products; 
	private Order order;
	private Customer customer;
	private CustomerControllerIF customerController; 
	private ServiceControllerIF serviceController; 
	private ProductControllerIF productController; 
	
	
	public OrderController() {
		customerController = new CustomerController();
		serviceController = new ServiceController();
		productController = new ProductController();
	}
	
	
	public Order createOrder() {
		order = new Order();
		return order;
		
	}
	
	public void setOrderInfo(int coverAmount, Date fulfillmentdate) {
		order.setCoverAmount(coverAmount);
		order.setFulfillmentdate(fulfillmentdate);
	}
	
	public void setCustomer(String name) {
		customer = customerController.findCustomer(name);
		 order.setCustomer(customer);
	}
	
	public void setDelivery(String deliveryAddress) {
		serviceController.setService(deliveryAddress);
	}


	@Override
	public void addService(Role role) {
		serviceController.addService(role);
		
	}


	@Override
	public List<Product> findProducts(String description) {
		
		products = productController.findProducts(description);
		
		return products;
	}
	
}







