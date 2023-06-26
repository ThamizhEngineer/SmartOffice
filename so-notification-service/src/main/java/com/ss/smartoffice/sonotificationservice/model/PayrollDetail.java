package com.ss.smartoffice.sonotificationservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayrollDetail {
   
	private int id;  

	private int payrollHeaderId;

	private String entryType;
    
	private String entryCode;
    
	private String entryName;
    
	private String statusCode;
    
	private BigDecimal ytd;
    
	private BigDecimal entryAmount;
    
	private String isEnabled;
    
	private String createdBy;
    
	private String modifiedBy;
    
	private String remarks;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
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


