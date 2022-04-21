package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 * Flectning data from db and use it to build new Customer & returns it/them
 * @author lassehas
 */

public class CustomerDB implements CustomerDBIF {

	/**
	 * SQL injects
	 */
	
	private static final String FIND_ALL_Q = "select * from customers";
	private static final String FIND_BY_NAME_Q = "select * from customers where name = ?";
	private static final String UPDATE_Q = "update persons set name = ?, address = ?, phone = ? , email = ?, zipcode = ? where name = ?";

	private Connection con;

	private PreparedStatement findAll;
	private PreparedStatement findByName;
	private PreparedStatement update;

	/**
	 * The constructor of the class.
	 * Try to make connection with the database and prepares statements, when created
	 */
	
	public CustomerDB() {
		con = DBConnection.getInstance().getConnection();
		try {
			findAll = con.prepareStatement(FIND_ALL_Q);
			findByName = con.prepareStatement(FIND_BY_NAME_Q);
			update = con.prepareStatement(UPDATE_Q);
		} catch (SQLException e) {
			// TODO throw exception
		}
	}

	/**
	 * Holds the logic for going into database and uses buildObject method to build the customer objects and returns it
	 * @return Customers 
	 */
	
	@Override
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<Customer>();

		try {
			ResultSet rs;
			rs = findAll.executeQuery();
			customers = buildObjects(rs);
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return customers;
	}

	/**
	 * Search by name in db and returns list of all customers with the name
	 * @param name : String 
	 * @return Customer
	 */
	
	@Override
	public List<Customer> findByName(String name) {
		List<Customer> customers = null;

		try {
			findByName.setString(1, name);
			ResultSet rs = findByName.executeQuery();
			if (rs.next()) {
				customers = buildObjects(rs);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}

		return customers;
	}

	@Override
	public Customer findCustomer(String name) {
		Customer customer = null;
		
		try {
			findByName.setString(1, name);
			ResultSet rs = findByName.executeQuery();
			if(rs.next()) {
				customer = buildObject(rs);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return customer;
	}
	
	/**
	 * Gets information about customer from the database, uses it to build customer objects and adds it to a list and returns it
	 * @param rs : ResultSet
	 * @return Customers
	 */
	
	private List<Customer> buildObjects(ResultSet rs) {
		List<Customer> customers = new ArrayList<>();
		try {
			while (rs.next()) {
				//Create new customer as null;
				Customer c = null;
				//Builds the new customer object
				c = buildObject(rs);
				//Adds the new customers to the list
				customers.add(c);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customers;
	}

	/**
	 * Gets information about the customer from the database, uses it to build a new customer object and returns it
	 * @param rs : ResultSet
	 * @return Customer
	 */
	
	private Customer buildObject(ResultSet rs) {
		Customer c = null;
	
		try {
			// Flectning data from db needed to build customer
			String fName = rs.getString("fname");
			String lName = rs.getString("lname");
			String street = rs.getString("street");
			String houseNo = rs.getString("houseno");
			String phoneNo = rs.getString("phoneno");
			String email = rs.getString("email");
			int customerNo = rs.getInt("customerno");
			String zipCode = rs.getString("zipcode");
			// builds new customer
			c = new Customer(fName,lName,street,houseNo,phoneNo, email,zipCode,customerNo);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}

}
