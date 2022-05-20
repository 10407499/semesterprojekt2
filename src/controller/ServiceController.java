package controller;
import db.DeliveryDB;
import db.DeliveryDBIF;
import model.Delivery;
import model.EmployeeRole;
import model.ServiceLine;

public class ServiceController implements ServiceControllerIF {

	private Delivery delivery;
	private ServiceLine serviceLine; 
	private DeliveryDBIF deliveryDB;
	
	public ServiceController () {
		deliveryDB = new DeliveryDB();
	}
	
	@Override
	public Delivery setDelivery(String houseNo, String street, String city, String zipcode) {
		delivery = new Delivery(houseNo, street, city, zipcode);
		return delivery;
	}

	@Override
	public void addService(EmployeeRole employeeRole) {
		serviceLine = new ServiceLine(employeeRole);
		delivery.addService(serviceLine);
	}

	@Override
	public void insertService(int orderNo) {
		deliveryDB.insertDelivery(delivery, orderNo);
		deliveryDB.insertServiceLines(delivery.getServiceLines(), orderNo);
	}
}
