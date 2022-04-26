package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Delivery;
import model.ServiceLine;

public class ServiceLineDB implements ServiceLineDBIF {
	public final static String INSERT_SERVICELINE_Q = "insert into Delivery values(?,?,?,?,?)";
	public PreparedStatement insertServiceLinesPS;
	public Connection con;

	public ServiceLineDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertServiceLinesPS = con.prepareStatement(INSERT_SERVICELINE_Q);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	@Override
	public void insertServiceLines(List<ServiceLine> serviceLines, int orderNo) {
		try {
			for(ServiceLine s : serviceLines) {
				insertServiceLinesPS.setString(1, s.getRole().toString());
				insertServiceLinesPS.setString(2, s.getStartTime());
				insertServiceLinesPS.setString(3, s.getEndTime());
				insertServiceLinesPS.setInt(4, s.getEmployee().getEmployeeNo());
				insertServiceLinesPS.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
