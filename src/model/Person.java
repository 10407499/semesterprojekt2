package model;

public class Person {

	private String name; 
	private String address;
	private String phoneNr;
	private String email;
	private int zipCode;
	
	public Person(String name, String address, String phoneNr, String email, int zipCode) {
		this.name = name; 
		this.address = address;
		this.phoneNr = phoneNr;
		this.email = email;
		this.zipCode = zipCode;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(String phoneNr) {
		this.phoneNr = phoneNr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	} 
	
	
	
	
	
	
}
