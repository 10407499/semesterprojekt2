package model;

import java.util.List;

public class Delivery {

	private String deliveryAddress;
	private List<ServiceLine> serviceLines; 
	
	public Delivery(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public void addService(ServiceLine serviceLine) {
		serviceLines.add(serviceLine); 
	}

	
	
	
	
}