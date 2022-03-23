package controller;

import java.util.List;

import db.CustomerDB;
import db.CustomerDBIF;
import model.Customer;

/**
 * Customer Controller
 * 
 * @author lassehas
 *
 */

public class CustomerController {
	
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
	
	public List<Customer> findAll() {
		// Create new List of customer objects
		List<Customer> customers = null;
		// Adds customers to the list
		customers = customerDB.findAll();
		// Returns the list with customers
		return customers;
	}

	/**
	 * Goes into customerDB to get the customer object built and returned as a customer
	 * @param name : String
	 * @return Customer
	 */
	
	public Customer findByName(String name) {
		// Create new Customer object
		Customer c = null;
		// Sets customer to the object
		c = customerDB.findByName(name);
		// Returns the customer
		return c;
	}

}
