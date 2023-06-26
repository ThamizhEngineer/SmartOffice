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
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="t_emp_payout_line")

@Scope("prototype")
public class EmployeePayoutLine extends BaseEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="m_emp_payout_id")
	private String employeePayoutId;
	private String empCode;
	private Integer lineOrder;
	private String isAllowance;
	private String dPayTypeCode;
	private String ytdAmt;
	private String lineAmt;
	private String dPayTypeName;
	private String periodAmt;
	private String arrearAmt;
	private String grouping;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String sPayoutTypeId;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public EmployeePayoutLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeePayoutLine(int id, String employeePayoutId, String empCode, Integer lineOrder, String isAllowance,
			String dPayTypeCode, String ytdAmt, String lineAmt, String dPayTypeName, String periodAmt, String arrearAmt,
			String grouping, String isEnabled, String createdBy, String modifiedBy, String sPayoutTypeId,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeePayoutId = employeePayoutId;
		this.empCode = empCode;
		this.lineOrder = lineOrder;
		this.isAllowance = isAllowance;
		this.dPayTypeCode = dPayTypeCode;
		this.ytdAmt = ytdAmt;
		this.lineAmt = lineAmt;
		this.dPayTypeName = dPayTypeName;
		this.periodAmt = periodAmt;
		this.arrearAmt = arrearAmt;
		this.grouping = grouping;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.sPayoutTypeId = sPayoutTypeId;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeePayoutId() {
		return employeePayoutId;
	}

	public void setEmployeePayoutId(String employeePayoutId) {
		this.employeePayoutId = employeePayoutId;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Integer getLineOrder() {
		return lineOrder;
	}

	public void setLineOrder(Integer lineOrder) {
		this.lineOrder = lineOrder;
	}

	public String getIsAllowance() {
		return isAllowance;
	}

	public void setIsAllowance(String isAllowance) {
		this.isAllowance = isAllowance;
	}

	public String getdPayTypeCode() {
		return dPayTypeCode;
	}

	public void setdPayTypeCode(String dPayTypeCode) {
		this.dPayTypeCode = dPayTypeCode;
	}

	public String getYtdAmt() {
		return ytdAmt;
	}

	public void setYtdAmt(String ytdAmt) {
		this.ytdAmt = ytdAmt;
	}

	public String getLineAmt() {
		return lineAmt;
	}

	public void setLineAmt(String lineAmt) {
		this.lineAmt = lineAmt;
	}

	public String getdPayTypeName() {
		return dPayTypeName;
	}

	public void setdPayTypeName(String dPayTypeName) {
		this.dPayTypeName = dPayTypeName;
	}

	public String getPeriodAmt() {
		return periodAmt;
	}

	public void setPeriodAmt(String periodAmt) {
		this.periodAmt = periodAmt;
	}

	public String getArrearAmt() {
		return arrearAmt;
	}

	public void setArrearAmt(String arrearAmt) {
		this.arrearAmt = arrearAmt;
	}

	public String getGrouping() {
		return grouping;
	}

	public void setGrouping(String grouping) {
		this.grouping = grouping;
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

	public String getsPayoutTypeId() {
		return sPayoutTypeId;
	}

	public void setsPayoutTypeId(String sPayoutTypeId) {
		this.sPayoutTypeId = sPayoutTypeId;
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
		return "EmployeePayoutLine [id=" + id + ", employeePayoutId=" + employeePayoutId + ", empCode=" + empCode
				+ ", lineOrder=" + lineOrder + ", isAllowance=" + isAllowance + ", dPayTypeCode=" + dPayTypeCode
				+ ", ytdAmt=" + ytdAmt + ", lineAmt=" + lineAmt + ", dPayTypeName=" + dPayTypeName + ", periodAmt="
				+ periodAmt + ", arrearAmt=" + arrearAmt + ", grouping=" + grouping + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", sPayoutTypeId=" + sPayoutTypeId
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	
	
}
