package controller;

import model.Delivery;
import model.Role;

public interface ServiceControllerIF {

	public void addService(Role role);
	
	public void insertService(int orderNo);

	public Delivery setService(String houseNo, String street, String city, String zipcode);
}
