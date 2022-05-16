package gui;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import controller.OrderController;
import model.Customer;
import model.DocumentCreator;
import model.Menu;
import model.Order;
import model.Product;

public class tryme {
	public static void main(String[] args) {
		
		OrderController orderController = new OrderController();
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69", "18181818", "ib@ib.ib", "1818", "Ibby", 1);
		int cover = 20;
		Date d = Date.valueOf("2022-05-12");
		String eatingTime = "17:45";

		int antalProdukterAdd = 10;

		// Act
		Order order = orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		

		order.setConfimation(true);
		
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		
		List<Product> s = orderController.findProducts("Menu1");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		s = orderController.findProducts("ret");
		productNo = s.get(0).getProductNo();
		
		for (int i = 0; i < antalProdukterAdd; i++) {
			orderController.addProduct(productNo, 13);
		}

		DocumentCreator dc = new DocumentCreator(order);

	}
}
