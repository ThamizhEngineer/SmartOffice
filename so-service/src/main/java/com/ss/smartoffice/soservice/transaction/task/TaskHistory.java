package com.ss.smartoffice.soservice.transaction.task;

import java.time.LocalDateTime;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name = "t_task_history")

@Scope("prototype")
public class TaskHistory extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer taskId;
	private String activity;
	private Integer	subTaskId;
	private String taskName;
	private String taskDesc;
	private String assignedToUserId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime logDt;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	private String userId;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public TaskHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TaskHistory(Integer id, Integer taskId, String activity, Integer subTaskId, String taskName, String taskDesc,
			String assignedToUserId, LocalDateTime logDt, String isEnabled, String createdBy, String modifiedBy,
			String userId, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.activity = activity;
		this.subTaskId = subTaskId;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
		this.assignedToUserId = assignedToUserId;
		this.logDt = logDt;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.userId = userId;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public Integer getSubTaskId() {
		return subTaskId;
	}
	public void setSubTaskId(Integer subTaskId) {
		this.subTaskId = subTaskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDesc() {
		return taskDesc;
	}
	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	public String getAssignedToUserId() {
		return assignedToUserId;
	}
	public void setAssignedToUserId(String assignedToUserId) {
		this.assignedToUserId = assignedToUserId;
	}
	public LocalDateTime getLogDt() {
		return logDt;
	}
	public void setLogDt(LocalDateTime logDt) {
		this.logDt = logDt;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
		return "TaskHistory [id=" + id + ", taskId=" + taskId + ", activity=" + activity + ", subTaskId=" + subTaskId
				+ ", taskName=" + taskName + ", taskDesc=" + taskDesc + ", assignedToUserId=" + assignedToUserId
				+ ", logDt=" + logDt + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", userId=" + userId + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	

}
