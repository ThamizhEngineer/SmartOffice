package com.ss.smartoffice.soservice.transaction.cashbalance;

import java.time.LocalDate;
import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_emp_cash_balance")
@Scope("prototype")
public class EmployeeCashBalance {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="m_employee_id")
	private String employeeId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id= m_employee_id)")
	private String empName;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate importDt;
	private String payToCompAmt;
	private String payToEmpAmt;
	@Column(name="t_cash_balance_import_id")
	private String cashBalImportId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public EmployeeCashBalance() {
		super();
	}
	public EmployeeCashBalance(int id, String employeeId, String empName, LocalDate importDt, String payToCompAmt,
			String payToEmpAmt, String cashBalImportId, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.empName = empName;
		this.importDt = importDt;
		this.payToCompAmt = payToCompAmt;
		this.payToEmpAmt = payToEmpAmt;
		this.cashBalImportId = cashBalImportId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public LocalDate getImportDt() {
		return importDt;
	}
	public void setImportDt(LocalDate importDt) {
		this.importDt = importDt;
	}
	public String getPayToCompAmt() {
		return payToCompAmt;
	}
	public void setPayToCompAmt(String payToCompAmt) {
		this.payToCompAmt = payToCompAmt;
	}
	public String getPayToEmpAmt() {
		return payToEmpAmt;
	}
	public void setPayToEmpAmt(String payToEmpAmt) {
		this.payToEmpAmt = payToEmpAmt;
	}
	public String getCashBalImportId() {
		return cashBalImportId;
	}
	public void setCashBalImportId(String cashBalImportId) {
		this.cashBalImportId = cashBalImportId;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public LocalDateTime getCreatedDt() {
		return createdDt;
	}
	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}
	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}
	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "EmployeeCashBalance [id=" + id + ", employeeId=" + employeeId + ", empName=" + empName + ", importDt="
				+ importDt + ", payToCompAmt=" + payToCompAmt + ", payToEmpAmt=" + payToEmpAmt + ", cashBalImportId="
				+ cashBalImportId + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
		
}
