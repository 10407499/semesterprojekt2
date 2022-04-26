package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Delivery;

public class DeliveryDB implements DeliveryDBIF {
	public final static String INSERT_DELIVERY_Q = "insert into Delivery values(?,?,?,?)";
	public PreparedStatement insertDeliveryPS;
	public Connection con;
	
	public DeliveryDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertDeliveryPS = con.prepareStatement(INSERT_DELIVERY_Q);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void insertDelivery(Delivery delivery, int orderNo) {
		try {
			insertDeliveryPS.setString(1, delivery.getHouseNo());
			insertDeliveryPS.setString(2, delivery.getStreet());
			insertDeliveryPS.setInt(3, orderNo);
			insertDeliveryPS.setString(4, delivery.getZipcode());
			insertDeliveryPS.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
