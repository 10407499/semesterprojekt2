package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Delivery;
import model.Employee;
import model.ServiceLine;

public class DeliveryDB implements DeliveryDBIF {
	public final static String INSERT_DELIVERY_Q = "insert into Delivery values(?,?,?,?)";
	private final static String INSERT_SERVICELINE_Q = "insert into ServiceLine values(?,?,?,?,?)";
	private PreparedStatement insertServiceLinesPS;
	public PreparedStatement insertDeliveryPS;
	public Connection con;
	
	public DeliveryDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertDeliveryPS = con.prepareStatement(INSERT_DELIVERY_Q);
			insertServiceLinesPS = con.prepareStatement(INSERT_SERVICELINE_Q);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	@Override
	public void insertServiceLines(List<ServiceLine> serviceLines, int orderNo) {
		EmployeeDBIF employeeDB = new EmployeeDB();
		List<Employee> employees = employeeDB.getEmployees();
		try {
			for(int i = 0; i < serviceLines.size(); i++) {
				insertServiceLinesPS.setString(1, serviceLines.get(i).getRole().toString());
				insertServiceLinesPS.setString(2, serviceLines.get(i).getStartTime());
				insertServiceLinesPS.setString(3, serviceLines.get(i).getEndTime());
				insertServiceLinesPS.setInt(4, orderNo);
				insertServiceLinesPS.setInt(5, employees.get(i).getEmployeeNo());
				insertServiceLinesPS.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
