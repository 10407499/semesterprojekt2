package db;

import java.util.List;

import model.OrderLine;

public interface OrderLineDBIF {

	public void insertOrderLines(List<OrderLine> orderLines, int orderNo);
	
}
