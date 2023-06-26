package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuthEmployee {

	private Integer authId;
	private Integer employeeId;
	private String employee;
	private String emailId;
	private String empType;
	private String empStatus;
	private String userName;
	private String userStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime loginAccess;
	
	public AuthEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthEmployee(Integer authId, Integer employeeId, String employee, String emailId, String empType,
			String empStatus, String userName, String userStatus, LocalDateTime loginAccess) {
		super();
		this.authId = authId;
		this.employeeId = employeeId;
		this.employee = employee;
		this.emailId = emailId;
		this.empType = empType;
		this.empStatus = empStatus;
		this.userName = userName;
		this.userStatus = userStatus;
		this.loginAccess = loginAccess;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}
	
	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public LocalDateTime getLoginAccess() {
		return loginAccess;
	}

	public void setLoginAccess(LocalDateTime loginAccess) {
		this.loginAccess = loginAccess;
	}

	@Override
	public String toString() {
		return "AuthEmployee [authId=" + authId + ", employeeId=" + employeeId + ", employee=" + employee + ", emailId="
				+ emailId + ", empType=" + empType + ", empStatus=" + empStatus + ", userName=" + userName
				+ ", userStatus=" + userStatus + ", loginAccess=" + loginAccess + "]";
	}
	
	
}
