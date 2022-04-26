package model;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceLine {
	
	private Role role;
	private String startTime;
	private String endTime;
	private Employee employee;

	public ServiceLine(Role role) {
		this.role = role;
	}
	
	public ServiceLine(Role role, String startTime, String endTime) {
		this.role = role;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public ServiceLine(Role role, String startTime, String endTime, Employee employee) {
		this.role = role;
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

	public void setRole(Role role) {
		this.role = role;
	}

	public Role getRole() {
		return role;
	}
}
