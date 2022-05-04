package controller;
import db.DeliveryDB;
import db.DeliveryDBIF;
import db.ServiceLineDB;
import db.ServiceLineDBIF;
import model.Delivery;
import model.EmployeeRole;
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
	public void addService(EmployeeRole employeeRole) {
		serviceLine = new ServiceLine(employeeRole);
		delivery.addService(serviceLine);
	}

	@Override
	public void insertService(int orderNo) {
		deliveryDB.insertDelivery(delivery, orderNo);
		serviceLineDB.insertServiceLines(delivery.getServiceLines(), orderNo);
	}
}
