package model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {

	private Date creationDate; 
	private Date fulfillmentDate;
	private int coverAmount; 
	private boolean paid; 
	private boolean confimation;
	private int orderNo; 
	private Delivery delivery; 
	private List<OrderLine> orderLines; 
	private Customer customer;
	
	public Order() {
		orderLines = new ArrayList<>();
		creationDate = Date.valueOf(LocalDate.now()); 
		paid = false; 
	}
	public Date getCreationDate() {
		return creationDate;
	}

	public Date getFulfillmentDate() {
		return fulfillmentDate;
	}
	public void setFulfillmentDate(Date fulfillmentDate) {
		this.fulfillmentDate = fulfillmentDate;
	}
	public int getCoverAmount() {
		return coverAmount;
	}
	public void setCoverAmount(int coverAmount) {
		this.coverAmount = coverAmount;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public boolean isConfimation() {
		return confimation;
	}
	public void setConfimation(boolean confimation) {
		this.confimation = confimation;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	public List<OrderLine> getOrderLines() {
		return orderLines;
	}
	public void addOrderLine(OrderLine orderLine) {
		orderLines.add(orderLine);
	}
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	} 
	
	
}
