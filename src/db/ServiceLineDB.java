package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Delivery;
import model.Employee;
import model.ServiceLine;

public class ServiceLineDB implements ServiceLineDBIF {
	
	private final static String INSERT_SERVICELINE_Q = "insert into ServiceLine values(?,?,?,?,?)";
	private PreparedStatement insertServiceLinesPS;
	private Connection con;

	public ServiceLineDB() {
		try {
			con = DBConnection.getInstance().getConnection();
			insertServiceLinesPS = con.prepareStatement(INSERT_SERVICELINE_Q);
		} catch (Exception e) {
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
