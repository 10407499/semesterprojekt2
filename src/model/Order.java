package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class Order {

	private Date creationDate; 
	private Date fulfillmentDate;
	private int coverQuantity; 
	private boolean paid; 
	private boolean confimation;
	private int orderNo; 
	private Delivery delivery; 
	private List<OrderLine> orderLines; 
	private Customer customer;
	private OrderConfirmationDocument orderConfirmationDocument;
	private String eatingTime;
	
	public Order() {
		orderLines = new ArrayList<>();
		creationDate = Date.valueOf(LocalDate.now()); 
		paid = false; 
	}
	
	public Order(Date creationDate, Date fulfillmentDate, int coverQuantity, boolean paid, boolean confimation,
			int orderNo) {
		this.creationDate = creationDate;
		this.fulfillmentDate = fulfillmentDate;
		this.coverQuantity = coverQuantity;
		this.paid = paid;
		this.confimation = confimation;
		this.orderNo = orderNo;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public Date getFulfillmentDate() {
		return fulfillmentDate;
	}
	
	/**
	 * This method uses DateTimeFormatter to format the localDate of fulfillmenetdate to this format "dd-MM-YY"
	 * @return dato
	 */
	public String getFulfillmentDateToString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yy");
		LocalDate dd = getFulfillmentDate().toLocalDate();
		return dd.format(dtf);
	}
	
	public void setFulfillmentDate(Date fulfillmentDate) {
		this.fulfillmentDate = fulfillmentDate;
	}
	
	public int getCoverQuantity() {
		return coverQuantity;
	}
	
	public void setCoverQuantity(int coverQuantity) {
		this.coverQuantity = coverQuantity;
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
	
	public void setEatingTime(String eatingTime) {
		this.eatingTime = eatingTime;
	}
	
	public String getEatingTime() {
		return eatingTime;
	}
	
	public void setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public void createOrderConfirmationDocument() {
		orderConfirmationDocument = new OrderConfirmationDocument(this);
	}
	
	public OrderConfirmationDocument getOrderConfirmationDocument() {
		return orderConfirmationDocument;
	}
}