package controller;

import java.util.List;

import model.Customer;

public interface CustomerControllerIF {

	public List<Customer> findCustomers(String name);
	
}
