package model;

public class Customer extends Person{

	private int customerNo; 
	
	public Customer(String fName, String lName, String street, String houseNo, String phoneNo, String email,
			String zipCode, String city, int customerNo) {
		super(fName, lName, street, houseNo, phoneNo, email, zipCode, city);
		this.customerNo = customerNo;
	}

	public int getCustomerNo() {
		return customerNo;
	}

}
