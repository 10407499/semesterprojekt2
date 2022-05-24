package model;


public class Employee extends Person {
	
	private int employeeNo;
	private String kitchenRole;

	public Employee(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, String city, String kitchenRole, int employeeNo) {
		super(fName, lName, street, houseNo, phoneNo, email, zipCode, city);
		this.employeeNo = employeeNo;
		this.kitchenRole = kitchenRole;
	}

	public int getEmployeeNo() {
		return employeeNo;
	}
	
	public String getKitchenRole() {
		return kitchenRole;
	}
}