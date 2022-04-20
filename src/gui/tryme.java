package gui;

import java.util.List;

import controller.OrderController;
import controller.OrderControllerIF;
import model.Product;

public class tryme {
	public static void main(String[] args) {
		OrderControllerIF oc = new OrderController();
		
		oc.createOrder();
		
		List<Product> products = oc.findProducts("med");
		
		System.out.println(products.get(0).getDescription());
		System.out.println(products.get(1).getDescription());
		
		oc.addProduct(products.get(0).getProductNo(), 10);
		
		System.out.println(oc.getOrderLines().get(0).getProduct().getDescription());
		
	}
}
