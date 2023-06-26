package com.ss.smartoffice.soservice.seed.LeaveType;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="s_leave_type")

@Scope("prototype")
public class LeaveType extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String leaveTypeCode;
	private String leaveTypeName;
	private String approvalDriven;
	private String minDuration;
	private String maxDuration;
	private String yearlyBalance;
	private String accumulation;
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
	public LeaveType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveType(Integer id, String leaveTypeCode, String leaveTypeName, String approvalDriven, String minDuration,
			String maxDuration, String yearlyBalance, String accumulation, String remarks, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.leaveTypeCode = leaveTypeCode;
		this.leaveTypeName = leaveTypeName;
		this.approvalDriven = approvalDriven;
		this.minDuration = minDuration;
		this.maxDuration = maxDuration;
		this.yearlyBalance = yearlyBalance;
		this.accumulation = accumulation;
		this.remarks = remarks;
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
	public String getLeaveTypeCode() {
		return leaveTypeCode;
	}
	public void setLeaveTypeCode(String leaveTypeCode) {
		this.leaveTypeCode = leaveTypeCode;
	}
	public String getLeaveTypeName() {
		return leaveTypeName;
	}
	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}
	public String getApprovalDriven() {
		return approvalDriven;
	}
	public void setApprovalDriven(String approvalDriven) {
		this.approvalDriven = approvalDriven;
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
	public String getAccumulation() {
		return accumulation;
	}
	public void setAccumulation(String accumulation) {
		this.accumulation = accumulation;
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
		return "LeaveType [id=" + id + ", leaveTypeCode=" + leaveTypeCode + ", leaveTypeName=" + leaveTypeName
				+ ", approvalDriven=" + approvalDriven + ", minDuration=" + minDuration + ", maxDuration=" + maxDuration
				+ ", yearlyBalance=" + yearlyBalance + ", accumulation=" + accumulation + ", remarks=" + remarks
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	
}
