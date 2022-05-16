package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CourseType;
import model.Dish;
import model.Measurement;
import model.MeasurementUnit;
import model.Menu;
import model.Product;

public class ProductDB implements ProductDBIF {

	private static final String FIND_PRODUCTS_Q = "select * from view_product where description like ?";
	
	private PreparedStatement findProductsPS;
	
	private Connection con; 
	
	public ProductDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			findProductsPS = con.prepareStatement(FIND_PRODUCTS_Q);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	@Override
	public List<Product> findProducts(String description) {
		List<Product> products = null;
		try {
			findProductsPS.setString(1, "%" + description + "%");
			ResultSet rs = findProductsPS.executeQuery();
			products = buildProducts(rs); // Builds product objects
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return products;
	}


	private List<Product> buildProducts(ResultSet rs) {
		List<Product> products = new ArrayList<>();
		
		try {
			while(rs.next()) {
				Product product = null;
				product = buildProduct(rs); 
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return products;
	}


	private Product buildProduct(ResultSet rs) {
		Product product = null;
		try {
			String description = rs.getString("description");
			double price = rs.getDouble("price");
			int productNo = rs.getInt("productNo");
			String type = rs.getString("type").toLowerCase();
			
			if(type.equals("dish")) {
				//	Gets the courseType from database and converts to enum & toUpperCase.
				CourseType courseType = CourseType.valueOf(rs.getString("dish_coursetype").toUpperCase());
				double quantity = rs.getDouble("dish_quantity");
				//	Gets the MeasurementUnit from database and converts to enum & toUpperCase.
				MeasurementUnit munit = MeasurementUnit.valueOf(rs.getString("dish_measurementUnit").toUpperCase());
				//	Creates new measurement object
				Measurement measurement = new Measurement(munit);
				//	Creates new Dish object from databases data
				product = new Dish(description, price, productNo, type, measurement, courseType, quantity);
			}else {
				//	Creates new Menu object from databases data
				product = new Menu(description, price, productNo, type);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
}
