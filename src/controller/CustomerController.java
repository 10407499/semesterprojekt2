package controller;

import java.util.List;
import db.CustomerDB;
import db.CustomerDBIF;
import db.DataAccessException;
import model.Customer;

public class CustomerController implements CustomerControllerIF {
	
	private CustomerDBIF customerDB;

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
	
	/**
	 * Goes into customerDB to insert a new customer with all the parameter needed & returns the new customerno 
	 * @return Customer
	 */
	@Override
	public Customer insertNewCustomer(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, String city) throws DataAccessException {
		Customer customer = new Customer(fName, lName, street, houseNo, phoneNo, email, zipCode, city);
		int customerNo = customerDB.insertNewCustomer(customer);
		customer.setCustomerNo(customerNo);
		return customer;
	}
	
}
