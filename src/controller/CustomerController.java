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
	
	public List<Customer> findAll() {
		// Create new List of customer objects
		List<Customer> customers = null;
		// Adds customers to the list
		customers = customerDB.findAll();
		// Returns the list with customers
		return customers;
	}

	/**
	 * Goes into customerDB to get the customer objects built and returned as a list of customers
	 * @param name : String
	 * @return Customers
	 */
	
	public List<Customer> findByName(String name) {
		// Create new list of customer objects
		List<Customer> customers = null;
		// Sets the list of customers to the object
		customers = customerDB.findByName(name);
		// Returns the list of customers
		return customers;
	}

	public Customer findCustomer(String name) {
		Customer customer = null;
		customer = customerDB.findCustomer(name);
		return customer;
	}
	
}
