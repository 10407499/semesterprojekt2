package controller;

import java.sql.SQLException;
import java.util.List;

import db.CustomerDB;
import db.CustomerDBIF;
import db.DataAccessException;
import model.Customer;

/**
 * Customer Controller
 * 
 * @author lassehas
 *
 */

public class CustomerController implements CustomerControllerIF {
	
	private CustomerDBIF customerDB;

	/*
	 * The constructor of the class
	 */
	
	public CustomerController() {
		customerDB = new CustomerDB();
	}

	/**
	 * Goes into customerDB to get the customer objects built and returned as a list of customers
	 * @return Customers
	 */
	
	public List<Customer> findCustomers(String name) {
		// Create new List of customer objects
		List<Customer> customers = null;
		// Adds customers to the list
		customers = customerDB.findCustomers(name);
		// Returns the list with customers
		return customers;
	}

	@Override
	public Customer insertNewCustomer(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, String city) throws DataAccessException {
		Customer customer = new Customer(fName, lName, street, houseNo, phoneNo, email, zipCode, city);
		int customerNo = customerDB.insertNewCustomer(customer);
		customer.setCustomerNo(customerNo);
		return customer;
	}
	
}
