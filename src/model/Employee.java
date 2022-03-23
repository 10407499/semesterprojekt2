package model;


public class Employee extends Person {

	private Role role; 
	
	public Employee(String name, String address, String phoneNr, String email, int zipCode, Role role) {
		super(name, address, phoneNr, email, zipCode);
		this.role = role; 
	}

}
