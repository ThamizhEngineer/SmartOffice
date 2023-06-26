package com.ss.smartoffice.soservice.transaction.PayoutProcess;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name = "t_payout_process_hdr")

@Scope("prototype")
public class PayoutProcessHeader extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "pay_cycle_line_id")
	private String payCycleLineId;
	@Column(name = "pay_cycle_line_code")
	private String payCycleLineCode;
	@Column(name = "employee_id")
	private String employeeId;
	@Column(name = "process_status")
	private String processStatus;
	@Column(name = "success_count")
	private Integer successCount;
	@Column(name = "failure_count")
	private Integer failureCount;
	@Column(name = "over_write")
	private String overWrite;
	@Column(name = "is_enabled")
	private String isEnabled;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "start_dt")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDt;
	@Column(name = "end_dt")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDt;
	
	@Column(name = "pay_month")
	private String month;
	@Column(name = "pay_year") 
	private String year;
	
	@Column(name = "created_dt")
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@Column(name = "modified_dt")
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "t_payout_process_hdr_id")
	private List<PayoutProcessLine> payoutProcessLines;
	public PayoutProcessHeader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPayCycleLineId() {
		return payCycleLineId;
	}
	public void setPayCycleLineId(String payCycleLineId) {
		this.payCycleLineId = payCycleLineId;
	}
	public String getPayCycleLineCode() {
		return payCycleLineCode;
	}
	public void setPayCycleLineCode(String payCycleLineCode) {
		this.payCycleLineCode = payCycleLineCode;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public Integer getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(Integer successCount) {
		this.successCount = successCount;
	}
	public Integer getFailureCount() {
		return failureCount;
	}
	public void setFailureCount(Integer failureCount) {
		this.failureCount = failureCount;
	}
	public String getOverWrite() {
		return overWrite;
	}
	public void setOverWrite(String overWrite) {
		this.overWrite = overWrite;
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
	public LocalDateTime getStartDt() {
		return startDt;
	}
	public void setStartDt(LocalDateTime startDt) {
		this.startDt = startDt;
	}
	public LocalDateTime getEndDt() {
		return endDt;
	}
	public void setEndDt(LocalDateTime endDt) {
		this.endDt = endDt;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
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
	public List<PayoutProcessLine> getPayoutProcessLines() {
		return payoutProcessLines;
	}
	public void setPayoutProcessLines(List<PayoutProcessLine> payoutProcessLines) {
		this.payoutProcessLines = payoutProcessLines;
	}
	
	public PayoutProcessHeader(int id, String payCycleLineId, String payCycleLineCode, String employeeId,
			String processStatus, Integer successCount, Integer failureCount, String overWrite, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime startDt, LocalDateTime endDt, String month, String year,
			LocalDateTime createdDt, LocalDateTime modifiedDt, List<PayoutProcessLine> payoutProcessLines) {
		super();
		this.id = id;
		this.payCycleLineId = payCycleLineId;
		this.payCycleLineCode = payCycleLineCode;
		this.employeeId = employeeId;
		this.processStatus = processStatus;
		this.successCount = successCount;
		this.failureCount = failureCount;
		this.overWrite = overWrite;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.startDt = startDt;
		this.endDt = endDt;
		this.month = month;
		this.year = year;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.payoutProcessLines = payoutProcessLines;
	}
	@Override
	public String toString() {
		return "PayoutProcessHeader [id=" + id + ", payCycleLineId=" + payCycleLineId + ", payCycleLineCode="
				+ payCycleLineCode + ", employeeId=" + employeeId + ", processStatus=" + processStatus
				+ ", successCount=" + successCount + ", failureCount=" + failureCount + ", overWrite=" + overWrite
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", startDt="
				+ startDt + ", endDt=" + endDt + ", month=" + month + ", year=" + year + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", payoutProcessLines=" + payoutProcessLines + "]";
	}

	

	

}
