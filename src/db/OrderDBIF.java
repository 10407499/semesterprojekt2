package db;

import java.sql.Date;
import java.util.List;

import model.Order;
import model.OrderLine;

public interface OrderDBIF {

	public int insertOrder(Order order);
	public int checkCoverQuantityOnDate(Date fulfillmentdate);
	void insertOrderLines(List<OrderLine> orderLines, int orderNo);
}
