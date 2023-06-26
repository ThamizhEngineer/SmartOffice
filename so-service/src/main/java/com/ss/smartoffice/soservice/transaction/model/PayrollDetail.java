package com.ss.smartoffice.soservice.transaction.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="t_payroll_dtl")

public class PayrollDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
	private int id;  
    @Column(name="t_payroll_hdr_id")
	private int payrollHeaderId;
    @Column(name="entry_type")
	private String entryType;
    @Column(name="entry_code")
	private String entryCode;
    @Column(name="entry_name")
	private String entryName;
    @Column(name="status_code")
	private String statusCode;
    @Column(name="ytd")
	private BigDecimal ytd;
    @Column(name="entry_amount")
	private BigDecimal entryAmount;
    @Column(name="is_enabled")
	private String isEnabled;
    @Column(name="created_by")
	private String createdBy;
    @Column(name="modified_by")
	private String modifiedBy;
    @Column(name="remarks")
	private String remarks;
    @Column(name="created_dt")
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
    @Column(name="modified_dt")
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
   
	public PayrollDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PayrollDetail(int id, int payrollHeaderId, String entryType, String entryCode, String entryName,
			String statusCode, BigDecimal ytd, BigDecimal entryAmount, String isEnabled, String createdBy,
			String modifiedBy, String remarks, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.payrollHeaderId = payrollHeaderId;
		this.entryType = entryType;
		this.entryCode = entryCode;
		this.entryName = entryName;
		this.statusCode = statusCode;
		this.ytd = ytd;
		this.entryAmount = entryAmount;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.remarks = remarks;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "PayrollDetail [id=" + id + ", payrollHeaderId=" + payrollHeaderId + ", entryType=" + entryType
				+ ", entryCode=" + entryCode + ", entryName=" + entryName + ", statusCode=" + statusCode + ", ytd="
				+ ytd + ", entryAmount=" + entryAmount + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", remarks=" + remarks + ", createdDt=" + createdDt + ", modifiedDt="
				+ modifiedDt + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPayrollHeaderId() {
		return payrollHeaderId;
	}

	public void setPayrollHeaderId(int payrollHeaderId) {
		this.payrollHeaderId = payrollHeaderId;
	}

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public BigDecimal getYtd() {
		return ytd;
	}

	public void setYtd(BigDecimal ytd) {
		this.ytd = ytd;
	}

	public BigDecimal getEntryAmount() {
		return entryAmount;
	}

	public void setEntryAmount(BigDecimal entryAmount) {
		this.entryAmount = entryAmount;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	
	

	

	
	
	
	
}


