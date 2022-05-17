package controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DataAccessException;
import db.OrderDB;
import db.OrderDBIF;
import db.OrderLineDB;
import db.OrderLineDBIF;
import db.ZipCityDB;
import db.ZipCityDBIF;
import model.Customer;
import model.Delivery;
import model.DocumentCreator;
import model.Order;
import model.OrderLine;
import model.Product;
import model.EmployeeRole;

public class OrderController implements OrderControllerIF {

	private List<Product> products;
	private Order order;
	private List<Customer> customers;
	private CustomerControllerIF customerController;
	private ServiceControllerIF serviceController;
	private ProductControllerIF productController;

	private OrderDBIF orderDB;
	private OrderLineDBIF orderLineDB;
	private ZipCityDBIF zipCityDb;
	private DocumentCreator dc;

	public OrderController() {
		customerController = new CustomerController();
		serviceController = new ServiceController();
		productController = new ProductController();
		orderDB = new OrderDB();
		orderLineDB = new OrderLineDB();
		zipCityDb = new ZipCityDB();
	}

	public Order createOrder() {
		order = new Order();
		return order;

	}

	@Override
	public boolean setOrderInfo(int coverAmount, Date fulfillmentdate, String eatingTime) {
		boolean succes = false;
		if (coverAmount >= 4 && coverAmount < 10000) {
			order.setCoverAmount(coverAmount);
			succes = true;
			order.setFulfillmentDate(fulfillmentdate);
			order.setEatingTime(eatingTime);
		}
		return succes;
	}

	@Override
	public List<Customer> findCustomers(String name) {
		customers = customerController.findCustomers(name);
		return customers;
	}

	public void setCustomer(int customerNo) {
		Customer customer = getCustomerByNo(customerNo);
		order.setCustomer(customer);
	}

	private Customer getCustomerByNo(int customerNo) {
		Customer customer = null;
		boolean res = false;
		int index = 0;

		while (!res && index < customers.size()) {
			if (customers.get(index).getCustomerNo() == customerNo) {
				res = true;
				customer = customers.get(index);
			}
			index++;
		}
		return customer;
	}

	public void setDelivery(String houseNo, String street, String city, String zipcode) {
		if (order.getDelivery() == null) {
			Delivery delivery = serviceController.setDelivery(houseNo, street, city, zipcode);
			order.setDelivery(delivery);
		}
	}

	@Override
	public void addService(List<EmployeeRole> employeeRoles) {
		for (EmployeeRole er : employeeRoles) {
			serviceController.addService(er);
		}
	}

	@Override
	public List<Product> findProducts(String description) {
		products = productController.findProducts(description);
		return products;
	}

	@Override
	public void addProduct(int productNo, int quantity) {
		Product product = getProductByNo(productNo);
		// Creates new orderLine Object
		OrderLine orderLine = new OrderLine(product, quantity);
		// Adds OrderLine object to Orders list of orderLines
		order.addOrderLine(orderLine);
	}

	private Product getProductByNo(int productNo) {
		Product product = null;
		boolean res = false;
		int index = 0;
		while (!res && index < products.size()) {
			if (products.get(index).getProductNo() == productNo) {
				res = true;
				product = products.get(index);
			}
			index++;
		}
		return product;
	}

	@Override
	public Order completeOrder() {
		int orderNo = orderDB.insertOrder(order);
		orderLineDB.insertOrderLines(order.getOrderLines(), orderNo);
		if (order.getDelivery() != null) {
			serviceController.insertService(orderNo);
		}
		dc = new DocumentCreator(order);
		return order;
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public List<String> customerDetailsToString(String fname) {
		List<String> res = new ArrayList<>();
		customers = findCustomers(fname);
		if (customers != null) {
			for (Customer c : customers) {
				String currStr = "<html>" + c.getfName() + " " + c.getlName() + "<br>" + c.getEmail();
				res.add(currStr);
			}
		}
		return res;
	}

	@Override
	public int checkCoverAmountOnDate(Date fulfillmentdate) {
		int sumCover = 0;
		if (!fulfillmentdate.equals(null)) {
			sumCover = orderDB.checkCoverAmountOnDate(fulfillmentdate);
		}
		return sumCover;
	}

	@Override
	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public List<String> productDetailsToString(String description) {
		List<String> res = new ArrayList<>();
		products = findProducts(description);
		if (products != null) {
			for (Product p : products) {
				String currStr = "<html>" + p.getDescription() + "<br>" + p.getPrice();
				res.add(currStr);
			}
		}
		return res;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void insertNewCustomer(String fName, String lName, String street, String houseNo, String phoneNo,
			String email, String zipCode, String city) throws DataAccessException {
		Customer customer = customerController.insertNewCustomer(fName, lName, street, houseNo, phoneNo, email, zipCode,
				city);
		if (customer != null) {
			order.setCustomer(customer);
		}
	}

	@Override
	public void removeProductFromOrder(int index) {
		order.getOrderLines().remove(index);
	}

	@Override
	public String getCitiesWithZipcode(String zipcode) {
		String city = zipCityDb.getCityByZipCode(zipcode);
		return city;
	}
	
	@Override
	public void setEatingTime(String eatingTime) {
		order.setEatingTime(eatingTime);
	}

	@Override
	public DocumentCreator getDocumentCreator() {
		return dc;
	}
}
