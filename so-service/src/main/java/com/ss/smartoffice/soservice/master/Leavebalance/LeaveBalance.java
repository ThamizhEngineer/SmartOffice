package com.ss.smartoffice.soservice.master.Leavebalance;
import java.time.LocalDateTime;
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
@Table(name="m_emp_leave_bal")

@Scope("prototype")
public class LeaveBalance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String employeeId;
	private String leaveTypeId;
	private String leavesType;
	@Formula("(SELECT lt.leave_type_name FROM s_leave_type lt WHERE lt.id = leave_type_id)")
	private String leaveTypeName;
	@Formula("(SELECT lt.leave_type_code FROM s_leave_type lt WHERE lt.id = leave_type_id)")
	private String leaveTypeCode;
	@Formula("(SELECT lt.min_duration FROM s_leave_type lt WHERE lt.id = leave_type_id)")
	private String minDuration;
	@Formula("(SELECT lt.max_duration FROM s_leave_type lt WHERE lt.id = leave_type_id)")
	private String maxDuration;
	@Formula("(SELECT lt.yearly_balance FROM s_leave_type lt WHERE lt.id = leave_type_id)")
	private String yearlyBalance;
	@Formula("(SELECT lt.approval_driven FROM s_leave_type lt WHERE lt.id = leave_type_id)")
	private String approvalDriven;
	private String fiscalYearId;
	private String leaveBalance;
	private String carriedOver;
	private String availedCount;
	private String remarks;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public LeaveBalance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveBalance(int id, String employeeId, String leaveTypeId, String leavesType, String leaveTypeName,
			String leaveTypeCode, String minDuration, String maxDuration, String yearlyBalance, String approvalDriven,
			String fiscalYearId, String leaveBalance, String carriedOver, String availedCount, String remarks,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.leaveTypeId = leaveTypeId;
		this.leavesType = leavesType;
		this.leaveTypeName = leaveTypeName;
		this.leaveTypeCode = leaveTypeCode;
		this.minDuration = minDuration;
		this.maxDuration = maxDuration;
		this.yearlyBalance = yearlyBalance;
		this.approvalDriven = approvalDriven;
		this.fiscalYearId = fiscalYearId;
		this.leaveBalance = leaveBalance;
		this.carriedOver = carriedOver;
		this.availedCount = availedCount;
		this.remarks = remarks;
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
	public String getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(String leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public String getLeavesType() {
		return leavesType;
	}
	public void setLeavesType(String leavesType) {
		this.leavesType = leavesType;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}
	public String getMinDuration() {
		return minDuration;
	}
	public void setMinDuration(String minDuration) {
		this.minDuration = minDuration;
	}
	public String getMaxDuration() {
		return maxDuration;
	}
	public void setMaxDuration(String maxDuration) {
		this.maxDuration = maxDuration;
	}
	public String getYearlyBalance() {
		return yearlyBalance;
	}
	public void setYearlyBalance(String yearlyBalance) {
		this.yearlyBalance = yearlyBalance;
	}
	public String getApprovalDriven() {
		return approvalDriven;
	}
	public void setApprovalDriven(String approvalDriven) {
		this.approvalDriven = approvalDriven;
	}
	public String getFiscalYearId() {
		return fiscalYearId;
	}
	public void setFiscalYearId(String fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}
	public String getLeaveBalance() {
		return leaveBalance;
	}
	public void setLeaveBalance(String leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	public String getCarriedOver() {
		return carriedOver;
	}
	public void setCarriedOver(String carriedOver) {
		this.carriedOver = carriedOver;
	}
	public String getAvailedCount() {
		return availedCount;
	}
	public void setAvailedCount(String availedCount) {
		this.availedCount = availedCount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
		return "LeaveBalance [id=" + id + ", employeeId=" + employeeId + ", leaveTypeId=" + leaveTypeId
				+ ", leavesType=" + leavesType + ", leaveTypeName=" + leaveTypeName + ", leaveTypeCode=" + leaveTypeCode
				+ ", minDuration=" + minDuration + ", maxDuration=" + maxDuration + ", yearlyBalance=" + yearlyBalance
				+ ", approvalDriven=" + approvalDriven + ", fiscalYearId=" + fiscalYearId + ", leaveBalance="
				+ leaveBalance + ", carriedOver=" + carriedOver + ", availedCount=" + availedCount + ", remarks="
				+ remarks + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
