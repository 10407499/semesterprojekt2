package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.Delivery;
import model.ServiceLine;

public class ServiceLineDB implements ServiceLineDBIF {
	public final static String INSERT_SERVICELINE_Q = "insert into ServiceLine values(?,null,null,?,null)";
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
	
	/*FIXME Problemstillingen er at databasen har brug for et employee nr dog n�r
	 * der bliver tilf�jet en serviceline i forbindelsen med en oprettelse af order
	 * har vi som udgangspunkt ikke fundet den medarbejder som skal bruges til at l�se
	 * opgaven. Derved er det n�dtvendigt at lave en �ndring i enten databasen eller 
	 *systemet. 	
	*/
	@Override
	public void insertServiceLines(List<ServiceLine> serviceLines, int orderNo) {
		try {
			for(ServiceLine s : serviceLines) {
				insertServiceLinesPS.setString(1, s.getRole().toString());
				//insertServiceLinesPS.setString(2, s.getStartTime());
				//insertServiceLinesPS.setString(3, s.getEndTime());
				insertServiceLinesPS.setInt(2, orderNo);
				//insertServiceLinesPS.setInt(5, 0);// 0 erstatter = s.getEmployee().getEmployeeNo()
				insertServiceLinesPS.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
