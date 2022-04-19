package controller;

import model.Role;

public interface ServiceControllerIF {
	public void setService(String deliveryAddress);

	public void addService(Role role);
}
