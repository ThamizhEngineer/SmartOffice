package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_emp_payout_lb")



@Scope("prototype")
public class EmployeePayoutLeaveBalance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="m_emp_payout_id")
	private String mEmpPayoutId;
	private String employeeId;
	private String leaveType;
	private String openingBalance;
	private String accured;
	private String availed;
	private String balance;
	private String totalOpeningBalance;
	private String totalAccured;
	private String totalAvailed;
	private String totalBalance;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public EmployeePayoutLeaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeePayoutLeaveBalance(Integer id, String mEmpPayoutId, String employeeId, String leaveType,
			String openingBalance, String accured, String availed, String balance, String totalOpeningBalance,
			String totalAccured, String totalAvailed, String totalBalance, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.mEmpPayoutId = mEmpPayoutId;
		this.employeeId = employeeId;
		this.leaveType = leaveType;
		this.openingBalance = openingBalance;
		this.accured = accured;
		this.availed = availed;
		this.balance = balance;
		this.totalOpeningBalance = totalOpeningBalance;
		this.totalAccured = totalAccured;
		this.totalAvailed = totalAvailed;
		this.totalBalance = totalBalance;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getmEmpPayoutId() {
		return mEmpPayoutId;
	}
	public void setmEmpPayoutId(String mEmpPayoutId) {
		this.mEmpPayoutId = mEmpPayoutId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getAccured() {
		return accured;
	}
	public void setAccured(String accured) {
		this.accured = accured;
	}
	public String getAvailed() {
		return availed;
	}
	public void setAvailed(String availed) {
		this.availed = availed;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getTotalOpeningBalance() {
		return totalOpeningBalance;
	}
	public void setTotalOpeningBalance(String totalOpeningBalance) {
		this.totalOpeningBalance = totalOpeningBalance;
	}
	public String getTotalAccured() {
		return totalAccured;
	}
	public void setTotalAccured(String totalAccured) {
		this.totalAccured = totalAccured;
	}
	public String getTotalAvailed() {
		return totalAvailed;
	}
	public void setTotalAvailed(String totalAvailed) {
		this.totalAvailed = totalAvailed;
	}
	public String getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
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
		return "EmployeePayoutLeaveBalance [id=" + id + ", mEmpPayoutId=" + mEmpPayoutId + ", employeeId=" + employeeId
				+ ", leaveType=" + leaveType + ", openingBalance=" + openingBalance + ", accured=" + accured
				+ ", availed=" + availed + ", balance=" + balance + ", totalOpeningBalance=" + totalOpeningBalance
				+ ", totalAccured=" + totalAccured + ", totalAvailed=" + totalAvailed + ", totalBalance=" + totalBalance
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}
