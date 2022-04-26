package model;


public class Employee extends Person {

	private Role role;
	private int employeeNo;

	public Employee(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, Role role, int employeeNo) {
		super(fName, lName, street, houseNo, phoneNo, email, zipCode);
		this.role = role;
		this.employeeNo = employeeNo;
	}

	public int getEmployeeNo() {
		// TODO Auto-generated method stub
		return employeeNo;
	} 
	
	

}
