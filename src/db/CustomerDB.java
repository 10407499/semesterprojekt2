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
 */

public class CustomerDB implements CustomerDBIF {

	/**
	 * SQL injects
	 */

	private static final String FIND_BY_NAME_Q = "select * from Customer where fname like ?";
	private static final String INSERT_Q = "insert into Customer values (?, ?, ?, ?, ?, ?, ?)";

	private Connection con;
	private PreparedStatement findByName;
	private PreparedStatement insertCustomerPS;

	/**
	 * The constructor of the class. Try to make connection with the database and
	 * prepares statements, when created
	 */

	public CustomerDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			findByName = con.prepareStatement(FIND_BY_NAME_Q);
			insertCustomerPS = con.prepareStatement(INSERT_Q, insertCustomerPS.RETURN_GENERATED_KEYS);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Search by name in db and returns list of all customers with the name
	 * @param name : String
	 * @return Customer
	 */

	@Override
	public List<Customer> findCustomers(String name) {
		List<Customer> customers = null;

		try {
			findByName.setString(1, "%" + name + "%");
			ResultSet rs = findByName.executeQuery();
			customers = buildObjects(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return customers;
	}

	/**
	 * Gets information about customer from the database, uses it to build customer
	 * objects and adds it to a list and returns it
	 * 
	 * @param rs : ResultSet
	 * @return Customers
	 */

	private List<Customer> buildObjects(ResultSet rs) {
		List<Customer> customers = new ArrayList<>();
		try {
			while (rs.next()) {
				// Create new customer as null;
				Customer c = null;
				// Builds the new customer object
				c = buildObject(rs);
				// Adds the new customers to the list
				customers.add(c);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return customers;
	}

	/**
	 * Gets information about the customer from the database, uses it to build a new
	 * customer object and returns it
	 * 
	 * @param rs : ResultSet
	 * @return Customer
	 */

	private Customer buildObject(ResultSet rs) {
		ZipCityDBIF zipCity = new ZipCityDB();
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
			String city = zipCity.getCityByZipCode(zipCode);
			// builds new customer
			c = new Customer(fName, lName, street, houseNo, phoneNo, email, zipCode, city, customerNo);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return c;
	}
	
	/**
	 * This method takes a parameter customer object, then it uses the object to change a preparedstatement values with the customer objects. It then calls execute in our DBConnection which inserts the new customer to db and returns the customerNo of the new customer
	 * @param customer
	 * @return res
	 */
	
	@Override
	public int insertNewCustomer(Customer customer) throws DataAccessException {
		//This method throws the SQLException, so we can catch it at the UI to display an error if customer is already in the database
		int res = -1;
		try {
			insertCustomerPS.setString(1, customer.getfName());
			insertCustomerPS.setString(2, customer.getlName());
			insertCustomerPS.setString(3, customer.getStreet());
			insertCustomerPS.setString(4, customer.getHouseNo());
			insertCustomerPS.setString(5, customer.getPhoneNo());
			insertCustomerPS.setString(6, customer.getEmail());
			insertCustomerPS.setString(7, customer.getZipCode());
			res = DBConnection.getInstance().executeInsertWithIdentity(insertCustomerPS);
		} catch (SQLException e) {
			throw new DataAccessException("Fejl i SQL i CustomerDB klassen", e);
		}
		return res;
	}

}
