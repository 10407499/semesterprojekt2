package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import model.Order;

public class OrderDB implements OrderDBIF {

	private static final String INSERT_ORDER_Q = "insert into Salesorder values(?,?,?,?,?,?)";
	private PreparedStatement insertOrderPS; 
	
	//private static final String Insert_Order_Q = "insert into SalesOrder values "
	
	private Connection con; 
	
	
	public OrderDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertOrderPS = con.prepareStatement(INSERT_ORDER_Q,insertOrderPS.RETURN_GENERATED_KEYS);
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
			//res = insertOrderPS.executeUpdate(); //FIXME som udgangspunkt så virker det ikke at benytte den metode neden for. 
			res = DBConnection.getInstance().executeInsertWithIdentity(insertOrderPS);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res; 
	}

}
