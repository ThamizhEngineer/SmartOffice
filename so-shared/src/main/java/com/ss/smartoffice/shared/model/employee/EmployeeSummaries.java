package com.ss.smartoffice.shared.model.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Entity
@Table(name="m_employee")

@Component
public class EmployeeSummaries {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String empName;
	private String empCode;
	private String loginUserId;
	private String tempContactNo;

	
	
	private String permContactNo;
	
	private String emailId;
	private String curAddress;
	private String permAddress;
	private String maritalStatus;
	public EmployeeSummaries() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeSummaries(Integer id, String empName, String empCode, String loginUserId, String tempContactNo,
			String permContactNo, String emailId, String curAddress, String permAddress, String maritalStatus) {
		super();
		this.id = id;
		this.empName = empName;
		this.empCode = empCode;
		this.loginUserId = loginUserId;
		this.tempContactNo = tempContactNo;
		this.permContactNo = permContactNo;
		this.emailId = emailId;
		this.curAddress = curAddress;
		this.permAddress = permAddress;
		this.maritalStatus = maritalStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getTempContactNo() {
		return tempContactNo;
	}
	public void setTempContactNo(String tempContactNo) {
		this.tempContactNo = tempContactNo;
	}
	public String getPermContactNo() {
		return permContactNo;
	}
	public void setPermContactNo(String permContactNo) {
		this.permContactNo = permContactNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCurAddress() {
		return curAddress;
	}
	public void setCurAddress(String curAddress) {
		this.curAddress = curAddress;
	}
	public String getPermAddress() {
		return permAddress;
	}
	public void setPermAddress(String permAddress) {
		this.permAddress = permAddress;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public int getColumnCount() {

        return getClass().getDeclaredFields().length;
    }
	@Override
	public String toString() {
		return "EmployeeSummaries [id=" + id + ", empName=" + empName + ", empCode=" + empCode + ", loginUserId="
				+ loginUserId + ", tempContactNo=" + tempContactNo + ", permContactNo=" + permContactNo + ", emailId="
				+ emailId + ", curAddress=" + curAddress + ", permAddress=" + permAddress + ", maritalStatus="
				+ maritalStatus + "]";
	}
	
}
