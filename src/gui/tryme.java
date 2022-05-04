package gui;

import java.sql.Date;
import java.util.List;

import controller.OrderController;
import controller.OrderControllerIF;
import model.Customer;
import model.Employee;
import model.Product;
import model.EmployeeRole;

public class tryme {
	public static void main(String[] args) {
		OrderControllerIF oc = new OrderController();
		
		oc.createOrder();
	  
		
		List<Customer> customers = oc.findCustomers("ib");
		System.out.println(customers.get(0).getfName());   
		System.out.println(customers.get(1).getfName());   
		
		//oc.setOrderInfo(20, Date.valueOf("2023-01-01"));
		
		//oc.setDelivery("fff", "fff", "fff", "1818");
	
		//oc.addService(Role.EJER);
		//oc.addService(Role.LEDER);
		
		//System.out.println(oc.getOrder().getDelivery().getServiceLines().get(0).getRole());
		//System.out.println(oc.getOrder().getDelivery().getServiceLines().get(1).getRole());
		//List<Product> products = oc.findProducts("med");
		
		//Employee employee = new Employee("w", "w", "w", "w", "w", "w", "w", Role.LEDER, 1);
		//oc.getOrder().getDelivery().getServiceLines().get(0).setEmployee(employee);
		
		//System.out.println(products.get(0).getDescription());
		//System.out.println(products.get(1).getDescription());
		
		//oc.addProduct(products.get(0).getProductNo(), 10);
		//oc.addProduct(products.get(1).getProductNo(), 10);
		
		//oc.completeOrder();
	}
}
