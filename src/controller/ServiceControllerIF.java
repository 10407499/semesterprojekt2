package controller;

import model.Delivery;
import model.EmployeeRole;

public interface ServiceControllerIF {

	public void addService(EmployeeRole employeeRole);
	
	public void insertService(int orderNo);

	public Delivery setService(String houseNo, String street, String city, String zipcode);
}
