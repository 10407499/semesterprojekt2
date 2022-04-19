package controller;
import model.Delivery;
import model.Role;
import model.ServiceLine;

public class ServiceController implements ServiceControllerIF {

	private Delivery delivery;
	private ServiceLine serviceLine; 
	
	@Override
	public void setService(String deliveryAddress) {
		delivery = new Delivery(deliveryAddress);
		
	}

	@Override
	public void addService(Role role) {
		serviceLine = new ServiceLine(role);
		delivery.addService(serviceLine);
		
	}
}
