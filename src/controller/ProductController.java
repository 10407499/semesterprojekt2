package controller;
import java.util.List;

import db.ProductDB;
import db.ProductDBIF;
import model.Product;

public class ProductController implements ProductControllerIF {
	private ProductDBIF productDB;
	
	/**
	 * Constructor of productController class
	 */
	public ProductController() {
		this.productDB = new ProductDB();
	}

	/**
	 * Goes to the productDB class to do a SQL select with the parameter "description" & returns a list of product that contains this parameter
	 * @return products
	 */
	@Override
	public List<Product> findProducts(String description) {
		List<Product> products = null; 
		products = productDB.findProducts(description); 
		return products;
	}

}
