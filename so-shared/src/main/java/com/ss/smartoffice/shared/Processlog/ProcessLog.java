package com.ss.smartoffice.shared.Processlog;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_process_log")

public class ProcessLog {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String processName;
	private String processId;
	private String activityName;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime logDt;
	private String userId; // from commonUtils
	private String processStatus;
	private String remarks;
	private String jobId;
	private String employeeId;
	private String partnerId;
	private String endClientId;
	private String jobBayId;
	private String jobEquipmentId;
	private String jobStageId;
	private String attr1;
	private String attr2;
	private String attr3;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public ProcessLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProcessLog(Integer id, String processName, String processId, String activityName, LocalDateTime logDt,
			String userId, String processStatus, String remarks, String jobId, String employeeId, String partnerId,
			String endClientId, String jobBayId, String jobEquipmentId, String jobStageId, String attr1, String attr2,
			String attr3) {
		super();
		this.id = id;
		this.processName = processName;
		this.processId = processId;
		this.activityName = activityName;
		this.logDt = logDt;
		this.userId = userId;
		this.processStatus = processStatus;
		this.remarks = remarks;
		this.jobId = jobId;
		this.employeeId = employeeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.jobBayId = jobBayId;
		this.jobEquipmentId = jobEquipmentId;
		this.jobStageId = jobStageId;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
		
	}
	public ProcessLog(Integer id, String processName, String processId, String activityName, String processStatus,
			String remarks, String jobId, String employeeId, String partnerId, String endClientId, String jobBayId,
			String jobEquipmentId, String jobStageId) {
		super();
		this.id = id;
		this.processName = processName;
		this.processId = processId;
		this.activityName = activityName;
		this.processStatus = processStatus;
		this.remarks = remarks;
		this.jobId = jobId;
		this.employeeId = employeeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.jobBayId = jobBayId;
		this.jobEquipmentId = jobEquipmentId;
		this.jobStageId = jobStageId;
	}
	public ProcessLog(Integer id, String processName, String processId, String activityName, LocalDateTime logDt,
			String userId, String processStatus, String remarks, String jobId, String employeeId, String partnerId,
			String endClientId, String jobBayId, String jobEquipmentId, String jobStageId, String attr1, String attr2,
			String attr3, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.processName = processName;
		this.processId = processId;
		this.activityName = activityName;
		this.logDt = logDt;
		this.userId = userId;
		this.processStatus = processStatus;
		this.remarks = remarks;
		this.jobId = jobId;
		this.employeeId = employeeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.jobBayId = jobBayId;
		this.jobEquipmentId = jobEquipmentId;
		this.jobStageId = jobStageId;
		this.attr1 = attr1;
		this.attr2 = attr2;
		this.attr3 = attr3;
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
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public LocalDateTime getLogDt() {
		return logDt;
	}
	public void setLogDt(LocalDateTime logDt) {
		this.logDt = logDt;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProcessStatus() {
		return processStatus;
	}
	public void setProcessStatus(String processStatus) {
		this.processStatus = processStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getEndClientId() {
		return endClientId;
	}
	public void setEndClientId(String endClientId) {
		this.endClientId = endClientId;
	}
	public String getJobBayId() {
		return jobBayId;
	}
	public void setJobBayId(String jobBayId) {
		this.jobBayId = jobBayId;
	}
	public String getJobEquipmentId() {
		return jobEquipmentId;
	}
	public void setJobEquipmentId(String jobEquipmentId) {
		this.jobEquipmentId = jobEquipmentId;
	}
	public String getJobStageId() {
		return jobStageId;
	}
	public void setJobStageId(String jobStageId) {
		this.jobStageId = jobStageId;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public String getAttr3() {
		return attr3;
	}
	public void setAttr3(String attr3) {
		this.attr3 = attr3;
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
		return "ProcessLog [id=" + id + ", processName=" + processName + ", processId=" + processId + ", activityName="
				+ activityName + ", logDt=" + logDt + ", userId=" + userId + ", processStatus=" + processStatus
				+ ", remarks=" + remarks + ", jobId=" + jobId + ", employeeId=" + employeeId + ", partnerId="
				+ partnerId + ", endClientId=" + endClientId + ", jobBayId=" + jobBayId + ", jobEquipmentId="
				+ jobEquipmentId + ", jobStageId=" + jobStageId + ", attr1=" + attr1 + ", attr2=" + attr2 + ", attr3="
				+ attr3 + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
