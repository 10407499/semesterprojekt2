package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZipCityDB implements ZipCityDBIF {
	
	private static final String FIND_BY_ZIPCODE_Q = "select city from ZipCity where zipcode = ?";
	private PreparedStatement findCityByZipcodeps;
	private Connection con;
	
	public ZipCityDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			findCityByZipcodeps = con.prepareStatement(FIND_BY_ZIPCODE_Q);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
 
	/**
	 * This method takes a parameter "zipcode", it does executeQuery to return a resultset that contains the city of the zipcode
	 * @param String
	 * @return city
	 */
	
	@Override
	public String getCityByZipCode(String zipcode) {
		String city = null;
		try {
			findCityByZipcodeps.setString(1, zipcode);
			ResultSet rs = findCityByZipcodeps.executeQuery();
			if(rs.next()) {
				city = rs.getString("city");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return city;
	}

}
