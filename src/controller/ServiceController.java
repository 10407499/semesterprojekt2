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
	
	/**
	 * Put values into our delivery object by the given parameters & returns the delivery with the new values
	 * @return delivery
	 */
	
	@Override
	public Delivery setDelivery(String houseNo, String street, String city, String zipcode) {
		delivery = new Delivery(houseNo, street, city, zipcode);
		return delivery;
	}

	/**
	 * Puts value into our serviceLine object by the given parameters & returns the serviceLine with the new value
	 */
	
	@Override
	public void addService(EmployeeRole employeeRole) {
		serviceLine = new ServiceLine(employeeRole);
		delivery.addService(serviceLine);
	}

	/**
	 * Goes to the deliveryDB class to insert the delivery- & serviceLine objects into our database
	 */
	
	@Override
	public void insertService(int orderNo) {
		deliveryDB.insertDelivery(delivery, orderNo);
		deliveryDB.insertServiceLines(delivery.getServiceLines(), orderNo);
	}
}
