package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import db.DataAccessException;
import model.Customer;
import model.OrderConfirmationDocument;
import model.Order;
import model.OrderLine;
import model.Product;
import model.EmployeeRole;

public interface OrderControllerIF {

	public void createOrder();
	
	public List<Customer> findCustomers(String name);
	
	public void setCustomer(int customerNo);
	
	public void insertNewCustomer(String fName, String lName, String street, String houseNo, 
			String phoneNo, String email, String zipCode, String city) throws DataAccessException;
	
	public void setDelivery(String houseNo, String street, String city, String zipcode); 
	
	public List<Product> findProducts(String description); 
	
	public void addProduct(int productNo, int quantity);
	
	public boolean completeOrder(int coverQuantity, Date fulfillmentDate, String eatingTime, String houseNo, String street, String city, String zipcode, List<EmployeeRole> employeeRoles) throws ErrorFeedbackException;
	
	public boolean completeOrder(int coverQuantity, Date fulfillmentDate, String eatingTime) throws ErrorFeedbackException;
	
	public Order getOrder();
	
	public int checkCoverQuantityOnDate(Date fulfillmentdate);
	
	public List<Customer> getCustomers();
	
	public List<Product> getProducts();

	public void addService(List<EmployeeRole> employeeRoles);
	
	public void removeProductFromOrder(int index);

	public String getCitiesWithZipcode(String text);
	
	public void setEatingTime(String eatingTime);
}
