package db;

import java.util.List;

import model.Customer;

public interface CustomerDBIF {

	/**
	 * Part of a DAO pattern
	 * @return List of Customers
	 */
	public List<Customer> findCustomers(String name);
	public int insertCustomer(Customer customer);
}

