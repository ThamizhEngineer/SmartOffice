package com.ss.smartoffice.sonotificationservice.expenseclaimsummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_expense_claim")
public class ExpenseClaimSummary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String expenseClaimCode;
	private String jobId;
	private String expenseClaimStatus;
	private String employeeId;
	@Column(name="n1_emp_id")
	private String n1EmpId;
	@Column(name="n2_emp_id")
	private String n2EmpId;
	private String validateUserGroupId;
	@Column(name="settle_user_group_id")
	private String settleUserGroupId;


	public ExpenseClaimSummary() {
		super();
	}


	public ExpenseClaimSummary(Integer id, String expenseClaimCode, String jobId, String expenseClaimStatus,
			String employeeId, String n1EmpId, String n2EmpId, String validateUserGroupId, String settleUserGroupId) {
		super();
		this.id = id;
		this.expenseClaimCode = expenseClaimCode;
		this.jobId = jobId;
		this.expenseClaimStatus = expenseClaimStatus;
		this.employeeId = employeeId;
		this.n1EmpId = n1EmpId;
		this.n2EmpId = n2EmpId;
		this.validateUserGroupId = validateUserGroupId;
		this.settleUserGroupId = settleUserGroupId;
	}


	@Override
	public String toString() {
		return "ExpenseClaimSummary [id=" + id + ", expenseClaimCode=" + expenseClaimCode + ", jobId=" + jobId
				+ ", expenseClaimStatus=" + expenseClaimStatus + ", employeeId=" + employeeId + ", n1EmpId=" + n1EmpId
				+ ", n2EmpId=" + n2EmpId + ", validateUserGroupId=" + validateUserGroupId + ", settleUserGroupId="
				+ settleUserGroupId + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getExpenseClaimCode() {
		return expenseClaimCode;
	}


	public void setExpenseClaimCode(String expenseClaimCode) {
		this.expenseClaimCode = expenseClaimCode;
	}


	public String getJobId() {
		return jobId;
	}


	public void setJobId(String jobId) {
		this.jobId = jobId;
	}


	public String getExpenseClaimStatus() {
		return expenseClaimStatus;
	}


	public void setExpenseClaimStatus(String expenseClaimStatus) {
		this.expenseClaimStatus = expenseClaimStatus;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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


	public String getValidateUserGroupId() {
		return validateUserGroupId;
	}


	public void setValidateUserGroupId(String validateUserGroupId) {
		this.validateUserGroupId = validateUserGroupId;
	}


	public String getSettleUserGroupId() {
		return settleUserGroupId;
	}


	public void setSettleUserGroupId(String settleUserGroupId) {
		this.settleUserGroupId = settleUserGroupId;
	}


}
