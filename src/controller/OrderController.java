package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import db.DataAccessException;
import db.OrderDB;
import db.OrderDBIF;
import db.ZipCityDB;
import db.ZipCityDBIF;
import model.Customer;
import model.Delivery;
import model.OrderConfirmationDocument;
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
	private ZipCityDBIF zipCityDb;

	public OrderController() {
		customerController = new CustomerController();
		serviceController = new ServiceController();
		productController = new ProductController();
		orderDB = new OrderDB();
		zipCityDb = new ZipCityDB();
	}

	public void createOrder() {
		order = new Order();
	}

	@Override
	public boolean setOrderInfo(int coverQuantity, Date fulfillmentdate, String eatingTime) {
		boolean succes = false;
		if (coverQuantity >= 4 && coverQuantity < 10000) {
			order.setCoverQuantity(coverQuantity);
			order.setFulfillmentDate(fulfillmentdate);
			order.setEatingTime(eatingTime);
			succes = true;
		}
		return succes;
	}
	
	@Override
	public List<String> findCustomers(String name) {
		customers = customerController.findCustomers(name);
		return customerDetailsToString(customers);
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
	
	private boolean checkExistingOrderLines(Product product, int quantity) {
		boolean existing = false;
		
		for(OrderLine ol : order.getOrderLines()) {
			if(product.getProductNo() == ol.getProduct().getProductNo()) {
				int sum = ol.getQuantity() + quantity;
				combineQuantityOnOrderLine(ol, sum);
				existing = true;
			}
		}
		return existing;
	}
	
	private void combineQuantityOnOrderLine(OrderLine orderLine, int quantity) {
		orderLine.setQuantity(quantity);
	}
	
	@Override
	public void addProduct(int productNo, int quantity) {
		Product product = getProductByNo(productNo);
		if(!checkExistingOrderLines(product, quantity)) {
			// Creates new orderLine Object
			OrderLine orderLine = new OrderLine(product, quantity);
			// Adds OrderLine object to Orders list of orderLines
			order.addOrderLine(orderLine);
		}
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
	public void completeOrder() {
		int orderNo = orderDB.insertOrder(order);
		orderDB.insertOrderLines(order.getOrderLines(), orderNo);
		if (order.getDelivery() != null) {
			serviceController.insertService(orderNo);
		}
		order.createOrderConfirmationDocument();
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public List<String> customerDetailsToString(List<Customer> customers) {
		List<String> res = new ArrayList<>();
		if (customers != null) {
			for (Customer c : customers) {
				String currStr = "<html>" + c.getfName() + " " + c.getlName() + "<br>" + c.getEmail();
				res.add(currStr);
			}
		}
		return res;
	}

	@Override
	public int checkCoverQuantityOnDate(Date fulfillmentdate) {
		int sumCover = 0;
		if (fulfillmentdate != null) {
			sumCover = orderDB.checkCoverQuantityOnDate(fulfillmentdate);
		}
		return sumCover;
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
	public List<Customer> getCustomers() {
		return customers;
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
}
