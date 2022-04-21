package model;


public class Employee extends Person {

	private Role role;

	public Employee(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, Role role) {
		super(fName, lName, street, houseNo, phoneNo, email, zipCode);
		this.role = role;
	} 
	
	

}
