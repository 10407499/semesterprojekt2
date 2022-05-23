package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import db.DataAccessException;
import db.OrderDB;
import db.OrderDBIF;
import db.ZipCityDB;
import db.ZipCityDBIF;
import model.Customer;
import model.Delivery;
import model.OrderConfirmationDocument;
import model.Order;
import model.OrderLine;
import model.Product;
import model.EmployeeRole;

public class OrderController implements OrderControllerIF {

	private List<Product> products;
	private Order order;
	private List<Customer> customers;
	private CustomerControllerIF customerController;
	private ServiceControllerIF serviceController;
	private ProductControllerIF productController;

	private OrderDBIF orderDB;
	private ZipCityDBIF zipCityDb;

	public OrderController() {
		customerController = new CustomerController();
		serviceController = new ServiceController();
		productController = new ProductController();
		orderDB = new OrderDB();
		zipCityDb = new ZipCityDB();
	}

	/**
	 * Initialize our order object
	 */
	
	public void createOrder() {
		order = new Order();
	}

	/**
	 * This method gives parameter to check if the coverQuantity is equals 4 or higher, but lower than 10000 then it returns true
	 * @param coverQuantity
	 * @param fulfillmentdate
	 * @param eatingTime
	 * @return success
	 */
	
	private boolean setOrderInfo(int coverQuantity, Date fulfillmentdate, String eatingTime) {
		boolean succes = false;
		if (coverQuantity >= 4 && coverQuantity < 10000) {
			order.setCoverQuantity(coverQuantity);
			order.setFulfillmentDate(fulfillmentdate);
			order.setEatingTime(eatingTime);
			succes = true;
		}
		return succes;
	}
	
	/**
	 * Goes to customerController & returns list of customers 
	 * @returns customers
	 */
	
	@Override
	public List<Customer> findCustomers(String name) {
		customers = customerController.findCustomers(name); 
		return customers;
	}

	/**
	 * Puts a customer onto the order object by using the method "getCustomerByNo"
	 */
	
	public void setCustomer(int customerNo) {
		Customer customer = getCustomerByNo(customerNo);
		order.setCustomer(customer);
	}

	/**
	 * Uses parameter "customerNo" to do a while loop search to check if the customerNo is matching the customer in the list of customer at our field of variables
	 * @param customerNo
	 * @return customer
	 */
	
	private Customer getCustomerByNo(int customerNo) {
		Customer customer = null;
		boolean res = false;
		int index = 0;

		while (!res && index < customers.size()) {
			if (customers.get(index).getCustomerNo() == customerNo) {
				res = true;
				customer = customers.get(index);
			}
			index++;
		}
		return customer;
	}
	
	/**
	 * This method checks if the order object is null then call a method in the serviceController "setDelivery" which takes parameters to initialize a new delivery object.
	 * Then puts a delivery object onto our order object
	 */
	
	public void setDelivery(String houseNo, String street, String city, String zipcode) {
		if (order.getDelivery() == null) {
			Delivery delivery = serviceController.setDelivery(houseNo, street, city, zipcode);
			order.setDelivery(delivery);
		}
	}

	/**
	 * This method gives a list of EmployeeRole then uses a foreach loop to add each employeeRole to the serviceLine in the serviceController
	 */
	
	@Override
	public void addService(List<EmployeeRole> employeeRoles) {
		for (EmployeeRole er : employeeRoles) {
			serviceController.addService(er);
		}
	}

	/**
	 * Goes to productController with the paramater "descripition" to return a list of product which each product object contains the parameter
	 * @return products
	 */
	
	@Override
	public List<Product> findProducts(String description) {
		products = productController.findProducts(description);
		return products;
	}
	
	/**
	 * This method goes through each orderline with a foreach loop in the order object to check if there are duplicates of orderLines. If so it returns true
	 * @param product
	 * @param quantity
	 * @return existing
	 */
	
	private boolean checkExistingOrderLines(Product product, int quantity) {
		boolean existing = false;
		
		for(OrderLine ol : order.getOrderLines()) {
			if(product.getProductNo() == ol.getProduct().getProductNo()) {
				int sum = ol.getQuantity() + quantity;
				combineQuantityOnOrderLine(ol, sum);
				existing = true;
			}
		}
		return existing;
	}
	
	/**
	 * Subfunction for checkExistingOrderLines to setQuantity if there were a duplicate of orderlines
	 * @param orderLine
	 * @param quantity
	 */
	
	private void combineQuantityOnOrderLine(OrderLine orderLine, int quantity) {
		orderLine.setQuantity(quantity);
	}
	
	/**
	 * This method gets product by its productNo by using the method "getProductByNo(int)", then checks if the product already exists as a orderLine in the order object by using the method checkExistingOrderLine()
	 */
	
	@Override
	public void addProduct(int productNo, int quantity) {
		Product product = getProductByNo(productNo);
		if(!checkExistingOrderLines(product, quantity)) {
			// Creates new orderLine Object
			OrderLine orderLine = new OrderLine(product, quantity);
			// Adds OrderLine object to Orders list of orderLines
			order.addOrderLine(orderLine);
		}
	}

	/**
	 * Uses parameter "productNo" to do a while loop search to check if the productNo is matching the product in the list of product at our field of variables
	 * @param productNo
	 * @return product
	 */
	
	private Product getProductByNo(int productNo) {
		Product product = null;
		boolean res = false;
		int index = 0;
		while (!res && index < products.size()) {
			if (products.get(index).getProductNo() == productNo) {
				res = true;
				product = products.get(index);
			}
			index++;
		}
		return product;
	}

	@Override
	public boolean completeOrder(int coverQuantity, Date fulfillmentDate, String eatingTime, String houseNo, String street, String city, String zipcode, List<EmployeeRole> employeeRoles) throws ErrorFeedbackException {
		boolean success = true;
		
		if(!setOrderInfo(coverQuantity, fulfillmentDate, eatingTime)) {
			success = false;
			throw new ErrorFeedbackException("Minimum 4 kuverter*");
		}
		
		if(order.getCustomer() == null) {
			success = false;
			throw new ErrorFeedbackException("Kunde information mangler*");
		}
		
		setDelivery(houseNo, street, city, zipcode);
		
		addService(employeeRoles);
		
		if(success) {
			int orderNo = orderDB.insertOrder(order);
			orderDB.insertOrderLines(order.getOrderLines(), orderNo);
			if (order.getDelivery() != null) {
				serviceController.insertService(orderNo);
			}
			order.createOrderConfirmationDocument();
		}
		
		return success;
	}
	
	@Override
	public boolean completeOrder(int coverQuantity, Date fulfillmentDate, String eatingTime) throws ErrorFeedbackException {
		boolean success = true;
		
		if(!setOrderInfo(coverQuantity, fulfillmentDate, eatingTime)) {
			success = false;
			throw new ErrorFeedbackException("Minimum 4 kuverter*");
		}
		
		if(order.getCustomer() == null) {
			success = false;
			throw new ErrorFeedbackException("Kunde information mangler*");
		}
		
		if(success) {
			int orderNo = orderDB.insertOrder(order);
			orderDB.insertOrderLines(order.getOrderLines(), orderNo);
			if (order.getDelivery() != null) {
				serviceController.insertService(orderNo);
			}
			order.createOrderConfirmationDocument();
		}
		
		return success;
	}

	@Override
	public Order getOrder() {
		return order;
	}

	/**
	 * This method check the total quantity of covers on a specific date by going to the orderDB with the parameter "fulfilmentDate" 
	 * @return sumCover
	 */
	
	@Override
	public int checkCoverQuantityOnDate(Date fulfillmentdate) {
		int sumCover = 0;
		if (fulfillmentdate != null) {
			sumCover = orderDB.checkCoverQuantityOnDate(fulfillmentdate);
		}
		return sumCover;
	}
	
	@Override
	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * This method goes to the customerController with the parameters & it returns a customer object, that is being used to put onto our order object.
	 */
	
	@Override
	public void insertNewCustomer(String fName, String lName, String street, String houseNo, String phoneNo,
			String email, String zipCode, String city) throws DataAccessException {
		Customer customer = customerController.insertNewCustomer(fName, lName, street, houseNo, phoneNo, email, zipCode,
				city);
		if (customer != null) {
			order.setCustomer(customer);
		}
	}
	
	/**
	 * Removes a orderLine from our order object by using index 
	 */

	@Override
	public void removeProductFromOrder(int index) {
		order.getOrderLines().remove(index);
	}
	
	/**
	 * Goes into the zipCityDB with the parameter "zipcode" to get the name of a city with given zipcode by getting it from our db
	 */

	@Override
	public String getCitiesWithZipcode(String zipcode) {
		String city = zipCityDb.getCityByZipCode(zipcode);
		return city;
	}
	
	/**
	 * Sets the eatingTime on our order object by the parameter 
	 */
	
	@Override
	public void setEatingTime(String eatingTime) {
		order.setEatingTime(eatingTime);
	}

}
