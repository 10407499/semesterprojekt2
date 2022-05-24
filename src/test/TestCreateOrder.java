package test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.ErrorFeedbackException;
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
		Customer c = new Customer("Lasse", "Haslund", "Riishøjsvej 109", "st 6", "70707010", "haslund@lasse.com",
				"9000", "Aalborg", 1);
		int cover = 20;
		Product p = new Menu("Langtidshævet flutes", 20.00, 1, "MENU", CourseType.FORRET);
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";

		// Act
		orderController.createOrder();
		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		List<Product> s = orderController.findProducts("langtids");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);

		try {
			orderController.completeOrder(cover, d, eatingTime);
		} catch (ErrorFeedbackException e) {
			System.out.println(e.getMessage());
		}
		// Assert
		assertEquals(orderController.getOrder().getCustomer().getfName(), c.getfName());
		assertEquals(orderController.getOrder().getCoverQuantity(), cover);
		assertEquals(orderController.getOrder().getOrderLines().get(0).getProduct().getDescription(),p.getDescription());
		assertEquals(orderController.getOrder().getFulfillmentDate(), d);
	}

	// test case 2
	@Test
	public void OrderWithoutProducts() { 
		// Arrange
		Customer c = new Customer("Lasse", "Haslund", "Riishøjsvej 109", "st 6", "70707010", "haslund@lasse.com",
				"9000", "Aalborg", 1);
		int cover = 20;
		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";

		// Act
		orderController.createOrder();

		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);

		try {
			orderController.completeOrder(cover, d, eatingTime);
		} catch (ErrorFeedbackException e) {
			System.out.println(e.getMessage());
		}

		// Assert
		assertEquals(orderController.getOrder().getCustomer().getfName(), c.getfName());
		assertEquals(orderController.getOrder().getCoverQuantity(), cover);
		assertEquals(orderController.getOrder().getFulfillmentDate(), d);
	}

	// test case 3
	@Test
	public void ProductNotFound() {
		// Arrange

		// Act
		orderController.createOrder();

		List<Product> s = orderController.findProducts("WrongProduct");

		// Assert
		assertEquals(s.size(), 0);
	}

	// test case 4
	@Test
	public void addDelivery() {
		// Arrange
		Customer c = new Customer("Lasse", "Haslund", "Riishøjsvej 109", "st 6", "70707010", "haslund@lasse.com",
				"9000", "Aalborg", 1);
		Delivery delivery = new Delivery("st 6", "Riishøjsvej 109", "Aalborg", "9000");

		// Act
		orderController.createOrder();

		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);

		orderController.setDelivery(c.getHouseNo(), c.getStreet(), "Aalborg", c.getZipCode());

		// Assert
		assertEquals(delivery.getCity(), orderController.getOrder().getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), orderController.getOrder().getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), orderController.getOrder().getDelivery().getStreet());

	}

	// test case 5
	@Test
	public void addDeliveryAndService() {
		// Arrange

		Delivery delivery = new Delivery("st 6", "Riishøjsvej 109", "Aalborg", "9000");
		List<EmployeeRole> er = new ArrayList<>();
		er.add(EmployeeRole.Kok);

		// Act
		orderController.createOrder();

		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		Customer c = orderController.getOrder().getCustomer();
		orderController.setDelivery(c.getHouseNo(), c.getStreet(), "Aalborg", c.getZipCode());
		orderController.addService(er);

		// Assert
		assertEquals(delivery.getCity(), orderController.getOrder().getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), orderController.getOrder().getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), orderController.getOrder().getDelivery().getStreet());
		assertEquals(EmployeeRole.Kok, orderController.getOrder().getDelivery().getServiceLines().get(0).getRole());

	}

	// test case 6
	@Test
	public void AlternativeDeliveryAddress() {
		// Arrange
		Delivery delivery = new Delivery("69IBAlternative", "IbsevejAlternative", "Aalborg", "9000");

		// Act
		orderController.createOrder();

		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);
		orderController.setDelivery("69IBAlternative", "IbsevejAlternative", "Aalborg", "9000");

		// Assert
		assertEquals(delivery.getCity(), orderController.getOrder().getDelivery().getCity());
		assertEquals(delivery.getHouseNo(), orderController.getOrder().getDelivery().getHouseNo());
		assertEquals(delivery.getStreet(), orderController.getOrder().getDelivery().getStreet());
	}

	// test case 7
//	@Test
//	public void CustomerIsNotInTheSystem() throws DataAccessException {
//		// Arrange
//		Customer cc = new Customer("John", "IBBISEN", "Ibbisevej", "694BBI", "19191919", "ibbt@ibbi.ibbi", "9000",
//				"Aalborg");
//
//		// Act
//		orderController.createOrder();
//		// parameterne skal ændre efter hver test, da cc bliver sendt op til vores db
//		orderController.insertNewCustomer(cc.getfName(), cc.getlName(), cc.getStreet(), cc.getHouseNo(),
//				cc.getPhoneNo(), cc.getEmail(), cc.getZipCode(), cc.getCity());
//
////		List<Customer> customers = orderController.findCustomers("John");
////		int customerNo = customers.get(0).getCustomerNo();
////		orderController.setCustomer(customerNo);
//
//		// Assert
////		assertEquals(orderController.getOrder().getCustomer().getfName(), cc.getfName());
//	}

	// test case 8
	@Test
	public void NotEnoughCovers() {
		// Arrange
		int cover = 2;

		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";

		// Act
		orderController.createOrder();

		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);

		List<Product> s = orderController.findProducts("langtids");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);

		// Assert
		try {
			assertEquals(orderController.completeOrder(cover, d, eatingTime), false);
		} catch (ErrorFeedbackException e) {
			System.out.println(e.getMessage());
		}
	}

	// test case 9
	@Test
	public void OrderWithoutCover() {
		// Arrange
		int cover = 0;

		Date d = Date.valueOf("2023-01-01");
		String eatingTime = "17:45";

		// Act
		orderController.createOrder();

		List<Customer> customers = orderController.findCustomers("Lasse");
		int customerNo = customers.get(0).getCustomerNo();
		orderController.setCustomer(customerNo);

		List<Product> s = orderController.findProducts("langtids");
		int productNo = s.get(0).getProductNo();
		orderController.addProduct(productNo, 20);

		// Assert
		try {
			assertEquals(orderController.completeOrder(cover, d, eatingTime), false);
		} catch (ErrorFeedbackException e) {
			System.out.println(e.getMessage());
		}
	}
}
