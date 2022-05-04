package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import db.OrderDB;
import db.OrderDBIF;
import db.OrderLineDB;
import db.OrderLineDBIF;
import model.Customer;
import model.Delivery;
import model.Order;
import model.OrderLine;
import model.Product;
import model.Role;

public class OrderController implements OrderControllerIF {

	private List<Product> products;
	private Order order;
	private List<Customer> customers;
	private CustomerControllerIF customerController;
	private ServiceControllerIF serviceController;
	private ProductControllerIF productController;
	private OrderDBIF orderDB;
	private OrderLineDBIF orderLineDB;

	public OrderController() {
		customerController = new CustomerController();
		serviceController = new ServiceController();
		productController = new ProductController();
		orderDB = new OrderDB();
		orderLineDB = new OrderLineDB();
	}

	public Order createOrder() {
		order = new Order();
		return order;

	}

	public boolean setOrderInfo(int coverAmount, Date fulfillmentdate) {
		if (coverAmount >= 4) {
			order.setCoverAmount(coverAmount);
		}
		order.setFulfillmentDate(fulfillmentdate);
		return checkCoverAmountOnDate(coverAmount, fulfillmentdate);
	}

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
		Delivery delivery = serviceController.setService(houseNo, street, city, zipcode);
		order.setDelivery(delivery);
	}

	@Override
	public void addService(Role role) {
		serviceController.addService(role);
		// order.getDelivery().addService(null);
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
		if (order.getCoverAmount() >= 4) {
			int orderNo = orderDB.insertOrder(order);
			orderLineDB.insertOrderLines(order.getOrderLines(), orderNo);
			if (order.getDelivery() != null) {
				serviceController.insertService(orderNo);
			}
		}
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
	public void cancelCreateOrder() {
		order = null;

	}

	@Override
	public boolean checkCoverAmountOnDate(int coverAmount, Date fulfillmentdate) {
		List<Order> orders = orderDB.checkCoverAmountOnDate(fulfillmentdate);
		int sumCover = 0;
		int maxCover = 50;
		boolean success = true;
		for (Order o : orders) {
			sumCover += o.getCoverAmount();
		}
		if (sumCover + coverAmount > maxCover) {
			success = false;
		}
		return success;
	}

}
