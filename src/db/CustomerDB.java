package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDB implements CustomerDBIF {

	private static final String FIND_ALL_Q = "select * from customers";
	private static final String FIND_BY_NAME_Q = " where name = ?";
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
	 * FIXME searching by name in db, can be difficult if there is more than one with the same name
	 * maybe change it to search by phone something unique 
	 * @return Customer
	 */
	
	@Override
	public Customer findByName(String name) {
		Customer customer = null;

		try {
			findByName.setString(1, name);
			ResultSet rs = findByName.executeQuery();
			if (rs.next()) {
				customer = buildObject(rs);
			}
		} catch (SQLException e) {
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
				String name = rs.getString(1);
				String address = rs.getString(2);
				String phone = rs.getString(3);
				String email = rs.getString(4);
				int zipcode = rs.getInt(5);
				Customer c = new Customer(name, address, phone, email, zipcode);
				customers.add(c);
			}
		} catch (SQLException e) {
			//TODO throw exception
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
			String name = rs.getString(1);
			String address = rs.getString(2);
			String phone = rs.getString(3);
			String email = rs.getString(4);
			int zipcode = rs.getInt(5);
			// builds new customer
			c = new Customer(name, address, phone, email, zipcode);
		} catch (SQLException e) {
			//TODO throw exception
		}
		return c;
	}

}
