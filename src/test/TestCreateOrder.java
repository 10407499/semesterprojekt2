package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
import model.Delivery;
import model.Dish;
import model.Menu;
import model.Order;
import model.OrderLine;
import model.Product;
import model.EmployeeRole;

public class TestCreateOrder {
	private OrderControllerIF orderController;

	@Before
	public void setUp() throws Exception {
		orderController = new OrderController();
	}

	@After
	public void tearDown() throws Exception {
	}

	// Test case 1
	@Test
	public void SuccessfullCreationOfOrdertest() {

		// Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby", 1);
		int cover = 20;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d = Date.valueOf("2023-01-01");

		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		List<Product> s = orderController.findProducts("Menu1");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		Order order = orderController.completeOrder();
		// Assert
		assertEquals(order.getCustomer().getfName(), c.getfName());
		assertEquals(order.getCoverAmount(), cover);
		assertEquals(order.getOrderLines().get(0).getProduct().getDescription(), p.getDescription());
		assertEquals(order.getFulfillmentDate(), d);
	}

//	
	// test case 3
	@Test
	public void Overbooking() {
		// Arrange
		int cover = 51;
		Date d = Date.valueOf("2023-03-02");

		// Act
		orderController.createOrder();
		boolean expected = orderController.setOrderInfo(cover, d);

		// Assert
		assertEquals(expected, false);

	}

	// test case 4
	@Test
	public void OrderWithoutProducts() { // test case 4
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby", 1);
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		Order order = orderController.completeOrder();
		
		// Assert
		assertEquals(order.getCustomer().getfName(), c.getfName());
		assertEquals(order.getCoverAmount(), cover);
		assertEquals(order.getFulfillmentDate(), d);

	}

	// test case 5
	@Test
	public void ProductNotFound() {

		// Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby",1);
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		List<Product> s = orderController.findProducts("WrongProduct");

		// Assert

		assertEquals(s.size(), 0);
	}

	// test case 6
	@Test
	public void addDelivery() {
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby",1);
		int cover = 20;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d = Date.valueOf("2023-01-01");
		Delivery delivery = new Delivery("69IB", "Ibsevej", "Ibby", "1818");
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery(c.getHouseNo(), c.getStreet(), "Ibby", c.getZipCode());
		orderController.findProducts("Menu1");
		int productNo = orderController.getProducts().get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		Order order = orderController.completeOrder();
		
		// Assert
		assertEquals(delivery.getCity(), order.getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), order.getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), order.getDelivery().getStreet());

	}

	// test case 7
	@Test
	public void addDeliveryAndService() {
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818","Ibby", 1);
		int cover = 20;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d = Date.valueOf("2023-01-01");
		Delivery delivery = new Delivery("69IB", "Ibsevej", "Ibby", "1818");
		List<EmployeeRole> er = new ArrayList<>();
		er.add(EmployeeRole.Kok);
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery("69IB", "Ibsevej", "Ibby", "1818");
		orderController.addService(er);
		List<Product> s = orderController.findProducts("Menu1");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		Order order = orderController.completeOrder();
		// Assert
		assertEquals(delivery.getCity(), order.getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), order.getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), order.getDelivery().getStreet());
		assertEquals(EmployeeRole.Kok, order.getDelivery().getServiceLines().get(0).getRole());

	}

	// test case 8
	public void AlternativeDeliveryAddress() {
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby",1);
		int cover = 20;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d = Date.valueOf("2023-01-01");
		Delivery delivery = new Delivery("69IB", "Ibsevej", "Ibby", "1818");
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery("69IB", "Ibsevej", "Ibby", "1818");
		List<Product> s = orderController.findProducts("Menu1");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		Order order = orderController.completeOrder();
		
		// Assert
		assertEquals(delivery.getCity(), order.getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), order.getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), order.getDelivery().getStreet());

	}

	// test case 9
	// FIXME lars Sp�rgsm�l !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@Test 
	public void NotEnoughCovers() {
		//Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby", 1);
		int cover = 2;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d = Date.valueOf("2023-01-01");

		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		
		
		// Assert
		
	}

	// test case 11
	@Test
	public void OrderWithoutCover() {

		// Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby",3);
		int cover = 0;
		Product p = new Menu("Menu1", 20.00, 1, "MENU");
		Date d = Date.valueOf("2023-01-01");
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		List<Product> s = orderController.findProducts("Menu1");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		Order order = orderController.completeOrder();
		// Assert
		assertEquals(order.getCustomer().getCustomerNo(), c.getCustomerNo());

		// orderController.setOrderInfo(coverField.getText() != null &&
		// Integer.parseInt(coverField.getText()) >= 4 ?
		// Integer.parseInt(coverField.getText()) : 0, DatePicker.getDateValue());
	}

}
