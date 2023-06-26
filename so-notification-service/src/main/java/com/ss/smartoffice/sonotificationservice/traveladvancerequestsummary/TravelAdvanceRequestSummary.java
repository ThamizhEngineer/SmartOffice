package com.ss.smartoffice.sonotificationservice.traveladvancerequestsummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_tvl_adv_req")
public class TravelAdvanceRequestSummary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String tarCode;
	@Column(name="m_employee_id")
	private String employeeId;
	private String tarStatus;
	private String jobId;
	@Column(name="n1_employee_id")
	private String n1EmployeeId;
	@Column(name="acc2_user_group_id")
	private String acc2UserGroupId;
	@Column(name="acc2_employee_id")
	private String acc2EmployeeId;
	
	public TravelAdvanceRequestSummary() {
		super();
	}
	public TravelAdvanceRequestSummary(Integer id, String tarCode, String employeeId, String tarStatus, String jobId,
			String n1EmployeeId, String acc2UserGroupId, String acc2EmployeeId) {
		super();
		this.id = id;
		this.tarCode = tarCode;
		this.employeeId = employeeId;
		this.tarStatus = tarStatus;
		this.jobId = jobId;
		this.n1EmployeeId = n1EmployeeId;
		this.acc2UserGroupId = acc2UserGroupId;
		this.acc2EmployeeId = acc2EmployeeId;
	}
	@Override
	public String toString() {
		return "TravelAdvanceRequestSummary [id=" + id + ", tarCode=" + tarCode + ", employeeId=" + employeeId
				+ ", tarStatus=" + tarStatus + ", jobId=" + jobId + ", n1EmployeeId=" + n1EmployeeId
				+ ", acc2UserGroupId=" + acc2UserGroupId + ", acc2EmployeeId=" + acc2EmployeeId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTarCode() {
		return tarCode;
	}
	public void setTarCode(String tarCode) {
		this.tarCode = tarCode;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getTarStatus() {
		return tarStatus;
	}
	public void setTarStatus(String tarStatus) {
		this.tarStatus = tarStatus;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getN1EmployeeId() {
		return n1EmployeeId;
	}
	public void setN1EmployeeId(String n1EmployeeId) {
		this.n1EmployeeId = n1EmployeeId;
	}
	public String getAcc2UserGroupId() {
		return acc2UserGroupId;
	}
	public void setAcc2UserGroupId(String acc2UserGroupId) {
		this.acc2UserGroupId = acc2UserGroupId;
	}
	public String getAcc2EmployeeId() {
		return acc2EmployeeId;
	}
	public void setAcc2EmployeeId(String acc2EmployeeId) {
		this.acc2EmployeeId = acc2EmployeeId;
	}
	
	
}
