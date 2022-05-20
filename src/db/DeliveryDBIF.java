package db;

import java.util.List;

import model.Delivery;
import model.ServiceLine;

public interface DeliveryDBIF {
	public void insertDelivery(Delivery delivery, int orderNo);
	public void insertServiceLines(List<ServiceLine> serviceLines, int orderNo);
}
