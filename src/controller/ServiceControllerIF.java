package controller;

import model.Delivery;
import model.Role;

public interface ServiceControllerIF {
	public Delivery setService(String deliveryAddress);

	public void addService(Role role);
	
	public void insertService(int orderNo);
}
