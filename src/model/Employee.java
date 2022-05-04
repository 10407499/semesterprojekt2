package model;


public class Employee extends Person {

	private EmployeeRole employeeRole;
	private int employeeNo;

	public Employee(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, String city, EmployeeRole employeeRole, int employeeNo) {
		super(fName, lName, street, houseNo, phoneNo, email, zipCode, city);
		this.employeeRole = employeeRole;
		this.employeeNo = employeeNo;
	}

	public int getEmployeeNo() {
		return employeeNo;
	} 
	
	

}
