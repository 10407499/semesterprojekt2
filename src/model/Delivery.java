	package model;

import java.util.ArrayList;
import java.util.List;

public class Delivery {

	private String street;
	private String houseNo;
	private String city;
	private String zipcode;
	private List<ServiceLine> serviceLines; 

	public Delivery(String street, String houseNo, String city, String zipcode) {
		this.street = street;
		this.houseNo = houseNo;
		this.city = city;
		this.zipcode = zipcode;
		serviceLines = new ArrayList<>();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void addService(ServiceLine serviceLine) {
		serviceLines.add(serviceLine); 
	}

	public List<ServiceLine> getServiceLines() {
		return serviceLines;
	}

}
