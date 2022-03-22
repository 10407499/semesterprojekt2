package controller;

import db.CustomerDB;
import model.Customer;

public class CustomerController {

	public Customer addCustomer(String name) {
		Customer c = null;
		c = CustomerDB.findCustomer(name);

		return c;
	}
	
}
