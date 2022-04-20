package controller;
import java.util.List;

import db.ProductDB;
import db.ProductDBIF;
import model.Product;

public class ProductController implements ProductControllerIF {
	private ProductDBIF productDB;
	
	public ProductController() {
		this.productDB = new ProductDB();
	}

	@Override
	public List<Product> findProducts(String description) {
		List<Product> products = null; 
		products = productDB.findProducts(description); 
		return products;
	}

}
