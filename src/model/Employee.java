package model;


public class Employee extends Person {

	private Role role; 
	
	public Employee(String name, String address, int phoneNr, String email, int zipCode, Role role) {
		super(name, address, phoneNr, email, zipCode);
		this.role = role; 
		// TODO Auto-generated constructor stub
	}

}
