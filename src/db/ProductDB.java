package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Dish;
import model.Product;

public class ProductDB implements ProductDBIF {

	private static final String FIND_PRODUCTS_Q = ""; //FIXME: TIlføj det præcise sql queries
	
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
			
			ResultSet rs = findProductsPS.executeQuery();
			products = buildProducts(rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return products;
	}


	private List<Product> buildProducts(ResultSet rs) {
		List<Product> products = new ArrayList<Product>();
		
		try {
			while(rs.next()) {
				Product product = null;
				product = buildProduct(rs); 
				products.add(product);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return products;
	}


	private Product buildProduct(ResultSet rs) { //FIXME : Sikre at metoden afspejler database designet angående kolonner
		Product product = null; 
		try {
			String description = rs.getString("description");
			double price = rs.getDouble("price");
			int productNo = rs.getInt("productNo");
			product = new Product(description,price, productNo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
