package db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import model.Order;

public class OrderDB implements OrderDBIF {

	private static final String INSERT_ORDER_Q = "insert into Salesorder values(?,?,?,?,?,?)";
	private static final String FIND_ORDERS_BY_DATE_Q = "select * from Salesorder where fulfillmentDate = ?";
	private PreparedStatement insertOrderPS; 
	private PreparedStatement findOrdersByDatePS;
	
	//private static final String Insert_Order_Q = "insert into SalesOrder values "
	
	private Connection con; 
	
	
	public OrderDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertOrderPS = con.prepareStatement(INSERT_ORDER_Q,insertOrderPS.RETURN_GENERATED_KEYS);
			findOrdersByDatePS = con.prepareStatement(FIND_ORDERS_BY_DATE_Q);
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

	@Override
	public List<Order> checkCoverAmountOnDate(Date fulfillmentdate) {
		List<Order> orders = null;
		try {
			findOrdersByDatePS.setDate(1, fulfillmentdate);
			
			ResultSet rs = findOrdersByDatePS.executeQuery();
			orders = buildOrders(rs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		return orders;
		
		
	}

	private List<Order> buildOrders(ResultSet rs) {
		List<Order> orders = new ArrayList<>();
		try {
			while(rs.next()) {
				Order order = null;
				order = buildOrder(rs);
				orders.add(order);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return  orders;
	}

	private Order buildOrder(ResultSet rs) {
		Order order = null;
		
		try {
			//FIXME Hvad skal der med til en Order osv. 
			Date creationDate = rs.getDate("date");
			Date fulfillmentDate = rs.getDate("fulfillmentDate");
			int coverAmount = rs.getInt("coverAmount");
			boolean paid = rs.getBoolean("paid");
			boolean confirmation = rs.getBoolean("confirmation");
			int orderNo = rs.getInt("orderNo");
			int customerNo = rs.getInt("customerNo");
			order = new Order(creationDate, fulfillmentDate, coverAmount, paid, confirmation, orderNo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return order;
	}

}
