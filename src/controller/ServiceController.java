package controller;
import db.DeliveryDB;
import db.DeliveryDBIF;
import db.ServiceLineDB;
import db.ServiceLineDBIF;
import model.Delivery;
import model.Role;
import model.ServiceLine;

public class ServiceController implements ServiceControllerIF {

	private Delivery delivery;
	private ServiceLine serviceLine; 
	private DeliveryDBIF deliveryDB;
	private ServiceLineDBIF serviceLineDB;
	
	public ServiceController () {
		deliveryDB = new DeliveryDB();
		serviceLineDB = new ServiceLineDB();
	}
	
	@Override
	public Delivery setService(String houseNo, String street, String city, String zipcode) {
		return delivery = new Delivery(houseNo, street, city, zipcode);
	}

	@Override
	public void addService(Role role) {
		serviceLine = new ServiceLine(role);
		delivery.addService(serviceLine);
	}

	@Override
	public void insertService(int orderNo) {
		deliveryDB.insertDelivery(delivery, orderNo);
		serviceLineDB.insertServiceLines(delivery.getServiceLines(), orderNo);
	}
}
