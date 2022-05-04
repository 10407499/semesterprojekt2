package controller;

import java.sql.Date;
import java.util.List;

import model.Customer;
import model.Order;
import model.OrderLine;
import model.Product;
import model.Role;

public interface OrderControllerIF {

	public Order createOrder();
	
	public boolean setOrderInfo(int coverAmount, Date fulfillmentdate);
	
	public List<Customer> findCustomers(String name);
	
	public void setCustomer(int customerNo); 
	
	public void setDelivery(String houseNo, String street, String city, String zipcode); 
	
	public void addService(Role role);
	
	public List<Product> findProducts(String description); 
	
	public void addProduct(int productNo, int quantity);
	
	public Order completeOrder();
	
	public Order getOrder();
	
	public List<String> customerDetailsToString(String fname);
	
	public boolean checkCoverAmountOnDate(int coverAmount, Date fulfillmentdate);
	
	
	
}
