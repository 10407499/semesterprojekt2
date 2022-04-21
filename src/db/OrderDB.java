package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Order;

public class OrderDB implements OrderDBIF {

	private static final String INSERT_ORDER_Q = "insert into Salesorder values(?,?,?,?,?,?)";
	private PreparedStatement insertOrderPS; 
	
	private Connection con; 
	
	
	public OrderDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertOrderPS = con.prepareStatement(INSERT_ORDER_Q);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	public int insertOrder(Order order) {
		int res = -1; 
		try {
			insertOrderPS.setDate(1, order.getCreationDate());
			insertOrderPS.setDate(2, order.getFulfillmentDate());
			insertOrderPS.setInt(3, order.getCoverAmount());
			insertOrderPS.setBoolean(4, order.isPaid());
			insertOrderPS.setBoolean(5, order.isConfimation());
			insertOrderPS.setInt(6, order.getCustomer().getCustomerNo());
			res = DBConnection.getInstance().executeInsertWithIdentity(insertOrderPS);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res; 
	}

}
