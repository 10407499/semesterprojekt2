package test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.CustomerController;
import controller.CustomerControllerIF;
import controller.OrderController;
import controller.OrderControllerIF;
import controller.ProductController;
import controller.ProductControllerIF;
import controller.ServiceController;
import controller.ServiceControllerIF;
import model.Customer;
import model.Dish;
import model.Menu;
import model.Order;
import model.Product;

public class TestCreateOrder {
	private OrderControllerIF orderController;
	private ServiceControllerIF serviceController;
	private CustomerControllerIF customerController;
	private ProductControllerIF productController;
	
	@Before
	public void setUp() throws Exception {
		orderController = new OrderController();
		serviceController = new ServiceController();
		customerController = new CustomerController();
		productController = new ProductController();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		//Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", 1);
		int cover = 20;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d =  Date.valueOf("2023-01-01");
		
		//Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		orderController.setCustomer("IB");
		orderController.findProducts("Menu1");
		orderController.addProduct(1, 20); //TODO: Valg ud fra liste
		Order order = orderController.completeOrder();
		
		
		//Assert
		
		assertEquals(order.getCustomer(), c);
		assertEquals(order.getCoverAmount(), cover);
		assertEquals(order.getOrderLines().get(0), p);
		assertEquals(order.getFulfillmentDate(), d);
		
		fail("Not yet implemented");
	}

}
