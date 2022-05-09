package controller;

import java.util.List;

import model.Customer;

public interface CustomerControllerIF {

	public List<Customer> findCustomers(String name);
	public Customer insertCustomer(String fName, String lName, String street, String houseNo, 
			String phoneNo, String email, String zipCode, String city);
}
