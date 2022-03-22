package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Order {

	private LocalDate deliveryDate; 
	private int coverAmount;
	private int orderNr; 
	private boolean paid; 
	private boolean request; 
	private boolean delivery; 
	private String deliveryAddress; 
	private ArrayList<OrderLine> orderLines;
	private Customer customer;
	
	public Order() {
		super();
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public int getCoverAmount() {
		return coverAmount;
	}

	public void setCoverAmount(int coverAmount) {
		this.coverAmount = coverAmount;
	}

	public int getOrderNr() {
		return orderNr;
	}

	public void setOrderNr(int orderNr) {
		this.orderNr = orderNr;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public boolean isRequest() {
		return request;
	}

	public void setRequest(boolean request) {
		this.request = request;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
}
