package model;

public class Person {

	private String name; 
	private String address;
	private int phoneNr;
	private String email;
	private int zipCode;
	
	public Person(String name, String address, int phoneNr, String email, int zipCode) {
		super();
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

	public int getPhoneNr() {
		return phoneNr;
	}

	public void setPhoneNr(int phoneNr) {
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
