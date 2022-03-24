package db;

import java.util.List;

import model.Customer;

public interface CustomerDBIF {

	public List<Customer> findAll();
	public List<Customer> findByName(String name);
	
}
