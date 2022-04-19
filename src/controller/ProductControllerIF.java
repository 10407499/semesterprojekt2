package controller;

import java.util.List;

import model.Product;

public interface ProductControllerIF {

	
	public List<Product> findProducts(String description); 
	
	
}
