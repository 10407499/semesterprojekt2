package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Employee;

public class EmployeeDB implements EmployeeDBIF{

	private static final String GET_EMPLOYEES_Q = "select * from Employee";
	private PreparedStatement getEmployeePS;
	private Connection con;
	
	public EmployeeDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			getEmployeePS = con.prepareStatement(GET_EMPLOYEES_Q);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public List<Employee> getEmployees() {
		List<Employee> employees = new ArrayList();
		ZipCityDBIF zipCity = new ZipCityDB();
		try {
			ResultSet rs = getEmployeePS.executeQuery();
			
			while(rs.next()) {
				String fName = rs.getString("fname");
				String lName = rs.getString("lname");
				String street = rs.getString("street");
				String houseNo = rs.getString("houseno");
				String phoneNo = rs.getString("phoneno");
				String email = rs.getString("email");
				String zipCode = rs.getString("zipcode");
				String city = zipCity.getCityByZipCode(zipCode);
				String kitchenRole = rs.getString("role");
				int employeeNo = rs.getInt("employeeno");
				
				Employee e = new Employee(fName, lName, street, houseNo, phoneNo, email, zipCode, city, kitchenRole, employeeNo);
				employees.add(e);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return employees;
	}

}
