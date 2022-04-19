package db;

import java.util.List;

import model.Product;

public interface ProductDBIF {

	public List<Product> findProducts(String description);

}
