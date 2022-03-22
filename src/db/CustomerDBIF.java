package db;

import model.Customer;

public interface CustomerDBIF {

	public Customer findCustomer(String name);
	
}
