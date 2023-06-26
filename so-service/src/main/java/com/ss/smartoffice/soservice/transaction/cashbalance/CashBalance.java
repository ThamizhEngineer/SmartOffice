package com.ss.smartoffice.soservice.transaction.cashbalance;

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

@Entity
@Table(name="t_cash_balance_import")
@Scope("prototype")
public class CashBalance {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String importDt;
	private String importCode;
	private String importStatus;
	private String successCount;
	private String errorCount;
	private String errorCode;
	private String errorDesc;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String remarks;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public CashBalance() {
		super();
		}
	public CashBalance(Integer id, String importDt, String importCode, String importStatus, String successCount,
			String errorCount, String errorCode, String errorDesc, String isEnabled, String createdBy,
			String modifiedBy, String remarks, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.importDt = importDt;
		this.importCode = importCode;
		this.importStatus = importStatus;
		this.successCount = successCount;
		this.errorCount = errorCount;
		this.errorCode = errorCode;
		this.errorDesc = errorDesc;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.remarks = remarks;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImportDt() {
		return importDt;
	}
	public void setImportDt(String importDt) {
		this.importDt = importDt;
	}
	public String getImportCode() {
		return importCode;
	}
	public void setImportCode(String importCode) {
		this.importCode = importCode;
	}
	public String getImportStatus() {
		return importStatus;
	}
	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}
	public String getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}
	public String getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
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
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "CashBalance [id=" + id + ", importDt=" + importDt + ", importCode=" + importCode + ", importStatus="
				+ importStatus + ", successCount=" + successCount + ", errorCount=" + errorCount + ", errorCode="
				+ errorCode + ", errorDesc=" + errorDesc + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", remarks=" + remarks + ", createdDt=" + createdDt + ", modifiedDt="
				+ modifiedDt + "]";
	}
  
	
	
	
}
