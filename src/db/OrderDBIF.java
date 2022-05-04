package db;

import java.sql.Date;
import java.util.List;

import model.Order;

public interface OrderDBIF {

	public int insertOrder(Order order);

	public List<Order> checkCoverAmountOnDate(Date fulfillmentdate);
	
	
}
