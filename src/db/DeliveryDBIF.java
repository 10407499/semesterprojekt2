package db;

import model.Delivery;

public interface DeliveryDBIF {
	public void insertDelivery(Delivery delivery, int orderNo);
}
