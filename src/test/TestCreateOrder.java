package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.OrderController;
import controller.OrderControllerIF;
import db.DataAccessException;
import model.CourseType;
import model.Customer;
import model.Delivery;
import model.Menu;
import model.Order;
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
		Product p = new Menu("Menu1", 20.00, 1, "MENU", CourseType.FORRET);
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		List<Product> s = orderController.findProducts("Menu1");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);
		orderController.completeOrder();
		
		// Assert
		assertEquals(orderController.getOrder().getCustomer().getfName(), c.getfName());
		assertEquals(orderController.getOrder().getCoverQuantity(), cover);
		assertEquals(orderController.getOrder().getOrderLines().get(0).getProduct().getDescription(), p.getDescription());
		assertEquals(orderController.getOrder().getFulfillmentDate(), d);
	}

	// test case 4
	@Test
	public void OrderWithoutProducts() { // test case 4
		// Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby", 1);
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.completeOrder();
		
		// Assert
		assertEquals(orderController.getOrder().getCustomer().getfName(), c.getfName());
		assertEquals(orderController.getOrder().getCoverQuantity(), cover);
		assertEquals(orderController.getOrder().getFulfillmentDate(), d);
	}

	// test case 5
	@Test
	public void ProductNotFound() {
		// Arrange
		
		// Act
		orderController.createOrder();
		List<Product> s = orderController.findProducts("WrongProduct");

		// Assert
		assertEquals(s.size(), 0);
	}

	// test case 6
	@Test
	public void addDelivery() {
		// Arrange
		Customer c = new Customer("IB", "IBSEN", "Ibsevej", "69IB", "18181818", "ib@ib.ib", "1818", "Ibby",1);
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		Delivery delivery = new Delivery("69IB", "Ibsevej", "Ibby", "1818");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery(c.getHouseNo(), c.getStreet(), "Ibby", c.getZipCode());
		orderController.completeOrder();
		
		// Assert
		assertEquals(delivery.getCity(), orderController.getOrder().getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), orderController.getOrder().getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), orderController.getOrder().getDelivery().getStreet());

	}

	// test case 7
	@Test
	public void addDeliveryAndService() {
		// Arrange
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		Delivery delivery = new Delivery("69IB", "Ibsevej", "Ibby", "1818");
		List<EmployeeRole> er = new ArrayList<>();
		er.add(EmployeeRole.Kok);
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery("69IB", "Ibsevej", "Ibby", "1818");
		orderController.addService(er);
		orderController.completeOrder();
		
		// Assert
		assertEquals(delivery.getCity(), orderController.getOrder().getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), orderController.getOrder().getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), orderController.getOrder().getDelivery().getStreet());
		assertEquals(EmployeeRole.Kok, orderController.getOrder().getDelivery().getServiceLines().get(0).getRole());

	}

	// test case 8
	@Test
	public void AlternativeDeliveryAddress() {
		// Arrange
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		Delivery delivery = new Delivery("69IBAlternative", "IbsevejAlternative", "Ibby", "1818");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		List<Customer> customers = orderController.findCustomers("IB");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery("69IBAlternative", "IbsevejAlternative", "Ibby", "1818");
		orderController.completeOrder();
		
		// Assert
		assertEquals(delivery.getCity(), orderController.getOrder().getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), orderController.getOrder().getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), orderController.getOrder().getDelivery().getStreet());
	}
	
	// test case 9
	@Test
	public void CustomerDoNotExsist() throws DataAccessException {
		// Arrange
		Customer cc = new Customer("IBBI", "IBBISEN", "Ibbisevej", "69IBBI", "19191919", "ibbi@ibbi.ibbi", "1818", "Ibby");
		int cover = 5;
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.insertNewCustomer(cc.getfName(), cc.getlName(), cc.getStreet(), cc.getHouseNo(), cc.getPhoneNo(), cc.getEmail(), cc.getZipCode(), cc.getCity());
		orderController.setOrderInfo(cover, d, eatingTime);
		List<Customer> customers = orderController.findCustomers("IBBI");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.completeOrder();
		
		// Assert
		assertEquals(orderController.getOrder().getCustomer().getfName(), cc.getfName());
	}

	// test case 10
	@Test 
	public void NotEnoughCovers() {
		// Arrange
		int cover = 2;
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		
		// Assert
		assertEquals(orderController.setOrderInfo(cover, d, eatingTime), false);
	}

	// test case 11
	@Test
	public void OrderWithoutCover() {
		// Arrange
		int cover = 0;
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";
		
		// Act
		orderController.createOrder();
		orderController.setOrderInfo(cover, d, eatingTime);
		
		// Assert
		assertEquals(orderController.setOrderInfo(cover, d, eatingTime), false);
	}
}
