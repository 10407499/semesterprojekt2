package gui;

import java.util.List;

import controller.OrderController;
import controller.OrderControllerIF;
import model.Product;
import model.Role;

public class tryme {
	public static void main(String[] args) {
		OrderControllerIF oc = new OrderController();
		
		oc.createOrder();
		
		oc.setDelivery("fff", "fff", "fff", "fff");
		oc.addService(Role.EJER);
		oc.addService(Role.LEDER);
		
		System.out.println(oc.getOrder().getDelivery().getServiceLines().get(0).getRole());
		System.out.println(oc.getOrder().getDelivery().getServiceLines().get(1).getRole());
		List<Product> products = oc.findProducts("med");
		
		
		System.out.println(products.get(0).getDescription());
		System.out.println(products.get(1).getDescription());
		
		
		oc.addProduct(products.get(0).getProductNo(), 10);
		
		
		
	}
}
