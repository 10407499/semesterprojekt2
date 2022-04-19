package controller;
import java.util.List;
import db.ProductDBIF;
import model.Product;

public class ProductController implements ProductControllerIF {
	private ProductDBIF productDB;
	

	@Override
	public List<Product> findProducts(String description) {
		List<Product> products = null; 
		products = productDB.findProducts(description); 
		return products;
	}

}
