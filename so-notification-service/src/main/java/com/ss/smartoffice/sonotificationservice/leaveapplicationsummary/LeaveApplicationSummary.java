package com.ss.smartoffice.sonotificationservice.leaveapplicationsummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_emp_leave_appln")
public class LeaveApplicationSummary {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String leaveCode;
	@Column(name="m_employee_id")
	private String employeeId;
	private String leaveStatus;
	private String n1EmpId;
	private String n2EmpId;
	private String hr1UserGroupId;
	private String hr1EmpId;
	
	public LeaveApplicationSummary() {
		super();
	}

	public LeaveApplicationSummary(Integer id, String leaveCode, String employeeId, String leaveStatus, String n1EmpId,
			String n2EmpId, String hr1UserGroupId, String hr1EmpId) {
		super();
		this.id = id;
		this.leaveCode = leaveCode;
		this.employeeId = employeeId;
		this.leaveStatus = leaveStatus;
		this.n1EmpId = n1EmpId;
		this.n2EmpId = n2EmpId;
		this.hr1UserGroupId = hr1UserGroupId;
		this.hr1EmpId = hr1EmpId;
	}

	@Override
	public String toString() {
		return "LeaveApplicationSummary [id=" + id + ", leaveCode=" + leaveCode + ", employeeId=" + employeeId
				+ ", leaveStatus=" + leaveStatus + ", n1EmpId=" + n1EmpId + ", n2EmpId=" + n2EmpId + ", hr1UserGroupId="
				+ hr1UserGroupId + ", hr1EmpId=" + hr1EmpId + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(String leaveCode) {
		this.leaveCode = leaveCode;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	public String getN1EmpId() {
		return n1EmpId;
	}

	public void setN1EmpId(String n1EmpId) {
		this.n1EmpId = n1EmpId;
	}

	public String getN2EmpId() {
		return n2EmpId;
	}

	public void setN2EmpId(String n2EmpId) {
		this.n2EmpId = n2EmpId;
	}

	public String getHr1UserGroupId() {
		return hr1UserGroupId;
	}

	public void setHr1UserGroupId(String hr1UserGroupId) {
		this.hr1UserGroupId = hr1UserGroupId;
	}

	public String getHr1EmpId() {
		return hr1EmpId;
	}

	public void setHr1EmpId(String hr1EmpId) {
		this.hr1EmpId = hr1EmpId;
	}

	
}
