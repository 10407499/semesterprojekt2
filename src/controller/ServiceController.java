package controller;
import model.Delivery;
import model.Role;
import model.ServiceLine;

public class ServiceController implements ServiceControllerIF {

	private Delivery delivery;
	private ServiceLine serviceLine; 
	
	@Override
	public Delivery setService(String deliveryAddress) {
		return delivery = new Delivery(deliveryAddress);
		
		
	}

	@Override
	public void addService(Role role) {
		serviceLine = new ServiceLine(role);
		delivery.addService(serviceLine);
		
	}

	@Override
	public void insertService(int orderNo) {
		// TODO Auto-generated method stub
		
	}
}
