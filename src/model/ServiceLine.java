package model;

public class ServiceLine {
	
	private EmployeeRole employeeRole;
	private String startTime;
	private String endTime;
	private Employee employee;

	public ServiceLine(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}
	
	public ServiceLine(EmployeeRole employeeRole, String startTime, String endTime) {
		this.employeeRole = employeeRole;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public ServiceLine(EmployeeRole employeeRole, String startTime, String endTime, Employee employee) {
		this.employeeRole = employeeRole;
		this.startTime = startTime;
		this.endTime = endTime;
		this.employee = employee;
	}

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Employee getEmployee() {
			
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}

	public EmployeeRole getRole() {
		return employeeRole;
	}
}
