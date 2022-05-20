package controller;

import java.util.List;
import db.DataAccessException;
import model.Customer;

public interface CustomerControllerIF {

	public List<Customer> findCustomers(String name);
	public Customer insertNewCustomer(String fName, String lName, String street, String houseNo, 
			String phoneNo, String email, String zipCode, String city) throws DataAccessException;
}
