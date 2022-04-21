package model;

public class Person {

	private String fName; 
	private String lName; 
	private String street;
	private String houseNo;
	private String phoneNo;
	private String email;
	private String zipCode;
	
	
	
	public Person(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode) {
		this.fName = fName;
		this.lName = lName;
		this.street = street;
		this.houseNo = houseNo;
		this.phoneNo = phoneNo;
		this.email = email;
		this.zipCode = zipCode;
	}



	public String getfName() {
		return fName;
	}



	public String getlName() {
		return lName;
	}



	public String getStreet() {
		return street;
	}



	public String getHouseNo() {
		return houseNo;
	}



	public String getPhoneNo() {
		return phoneNo;
	}



	public String getEmail() {
		return email;
	}



	public String getZipCode() {
		return zipCode;
	}
	
	
	
	
	
	
}
