package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import model.OrderLine;

public class OrderLineDB implements OrderLineDBIF {

	private static final String INSERT_ORDERLINES_Q = "insert into OrderLine values (?,?,?)";
	private PreparedStatement insertOrderLinesPS;
	
	private Connection con;
	
	public OrderLineDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertOrderLinesPS = con.prepareStatement(INSERT_ORDERLINES_Q);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	@Override
	public void insertOrderLines(List<OrderLine> orderLines, int orderNo) {
		try {
		for(OrderLine orderLine: orderLines) {
			insertOrderLinesPS.setInt(1, orderLine.getQuantity());
			insertOrderLinesPS.setInt(1, orderNo);
			insertOrderLinesPS.setInt(1, orderLine.getProduct().getProductNo());
			insertOrderLinesPS.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
