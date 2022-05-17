package db;

import java.sql.SQLException;
import java.util.List;

import model.Customer;

public interface CustomerDBIF {

	/**
	 * Part of a DAO pattern
	 * @return List of Customers
	 */
	public List<Customer> findCustomers(String name);
	public int insertNewCustomer(Customer customer) throws DataAccessException;
}

