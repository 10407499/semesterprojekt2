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

	private static final String FIND_PRODUCTS_Q = "select * from view_products where description like ?";
	private static final String FIND_MENUDISH_Q = "select * from MenuDish where Menu_productno = ?";
	private static final String FIND_DISH_BY_NO_Q = "select * from view_products where productno = ?";
	
	private PreparedStatement findProductsPS;
	private PreparedStatement findMenuDishPS;
	private PreparedStatement findDishByNoPS;
	private Connection con; 
	
	public ProductDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			findProductsPS = con.prepareStatement(FIND_PRODUCTS_Q);
			findMenuDishPS = con.prepareStatement(FIND_MENUDISH_Q);
			findDishByNoPS = con.prepareStatement(FIND_DISH_BY_NO_Q);
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
			int productNo = rs.getInt("productno");
			String type = rs.getString("type").toLowerCase();
			//Gets the courseType from database and converts to enum & toUpperCase.
			CourseType courseType = CourseType.valueOf(rs.getString("courseType").toUpperCase());
			
			if(type.equals("dish")) {
				double quantity = rs.getDouble("dish_unitQuantity");
				//	Gets the MeasurementUnit from database and converts to enum & toUpperCase.
				MeasurementUnit munit = MeasurementUnit.valueOf(rs.getString("dish_measurementUnit").toUpperCase());
				//	Creates new measurement object
				Measurement measurement = new Measurement(munit);
				//	Creates new Dish object from databases data
				product = new Dish(description, price, productNo, type, courseType, measurement, quantity);
			}else {
				//	Creates new Menu object from databases data
				Menu menu = new Menu(description, price, productNo, type, courseType);
				
				List<Product> products = buildDishFromMenu(productNo);
				for(Product p : products) {
					if(p instanceof Dish d) {
						d = (Dish) p;
						menu.addDish(d);
					}
				}
				product = menu;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return product;
	}
	
	private List<Product> buildDishFromMenu(int menuNo) {
		List<Product> products = new ArrayList<>();
		try {
			findMenuDishPS.setInt(1, menuNo);
			ResultSet rs = findMenuDishPS.executeQuery();
			while(rs.next()) {
				Product product = findDishByNo(rs.getInt("Dish_productno"));
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	
	private Product findDishByNo(int productNo) {
		Product product = null;
		try {
			findDishByNoPS.setInt(1, productNo);
			ResultSet rs = findDishByNoPS.executeQuery();
			product = buildDish(rs);
		} catch (SQLException e) {
			System.out.println(e.getMessage() + " FindDishByNo");
		}
		return product;
	}
	
	private Product buildDish(ResultSet rs) {
		Product product = null;
		try {
			if(rs.next()) {
				String description = rs.getString("description");
				double price = rs.getDouble("price");
				int productNo = rs.getInt("productno");
				String type = rs.getString("type").toLowerCase();
				//Gets the courseType from database and converts to enum & toUpperCase.
				CourseType courseType = CourseType.valueOf(rs.getString("courseType").toUpperCase());
				
				double quantity = rs.getDouble("dish_unitQuantity");
				//	Gets the MeasurementUnit from database and converts to enum & toUpperCase.
				MeasurementUnit munit = MeasurementUnit.valueOf(rs.getString("dish_measurementUnit").toUpperCase());
				//	Creates new measurement object
				Measurement measurement = new Measurement(munit);
				//	Creates new Dish object from databases data
				product = new Dish(description, price, productNo, type, courseType, measurement, quantity);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}		
		return product;
	}
	
}
