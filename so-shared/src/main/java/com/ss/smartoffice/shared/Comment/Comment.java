package com.ss.smartoffice.shared.Comment;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_comment")

public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String commentDesc;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime logDt;
    private String userId; // from commonUtils
    private String jobId;
    private String employeeId;
    @Formula("(select emp.emp_name from m_employee emp where emp.id= employee_id)")
    private String employeeName;
    private String partnerId;
    private String endClientId;
    private String jobMilestoneId;
    private String jobTasklistId;
    private String taskId;
    private String isEnabled;
    private String createdBy;
    private String modifiedBy;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDt;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDt;
    
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Comment(Integer id, String commentDesc, String userId, String jobId, String employeeId, String partnerId,
			String endClientId, String jobMilestoneId, String jobTasklistId, String taskId) {
		super();
		this.id = id;
		this.commentDesc = commentDesc;
		this.userId = userId;
		this.jobId = jobId;
		this.employeeId = employeeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.jobMilestoneId = jobMilestoneId;
		this.jobTasklistId = jobTasklistId;
		this.taskId = taskId;
	}
	
	
	public Comment(Integer id, String commentDesc, LocalDateTime logDt, String userId, String jobId, String employeeId,
			String partnerId, String endClientId, String jobMilestoneId, String jobTasklistId, String taskId,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.commentDesc = commentDesc;
		this.logDt = logDt;
		this.userId = userId;
		this.jobId = jobId;
		this.employeeId = employeeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.jobMilestoneId = jobMilestoneId;
		this.jobTasklistId = jobTasklistId;
		this.taskId = taskId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", commentDesc=" + commentDesc + ", logDt=" + logDt + ", userId=" + userId
				+ ", jobId=" + jobId + ", employeeId=" + employeeId + ", partnerId=" + partnerId + ", endClientId="
				+ endClientId + ", jobMilestoneId=" + jobMilestoneId + ", jobTasklistId=" + jobTasklistId + ", taskId="
				+ taskId + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommentDesc() {
		return commentDesc;
	}

	public void setCommentDesc(String commentDesc) {
		this.commentDesc = commentDesc;
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

	public String getJobMilestoneId() {
		return jobMilestoneId;
	}

	public void setJobMilestoneId(String jobMilestoneId) {
		this.jobMilestoneId = jobMilestoneId;
	}

	public String getJobTasklistId() {
		return jobTasklistId;
	}

	public void setJobTasklistId(String jobTasklistId) {
		this.jobTasklistId = jobTasklistId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
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


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	
    
    
}
