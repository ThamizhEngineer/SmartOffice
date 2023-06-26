package com.ss.smartoffice.soservice.transaction.task;

import java.time.LocalDateTime;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "t_subtask")

@Scope("prototype")
public class SubTask {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private Integer taskId;
    private String subtaskName;
    private String subTaskDesc;
    private String taskStatus;
    private String assignedToUserId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime assignedOnDt;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime completedDt;
    private String isEnabled;
    private String createdBy;
    private String modifiedBy;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDt;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDt;
	public SubTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SubTask(Integer id, Integer taskId, String subtaskName, String subTaskDesc, String taskStatus,
			String assignedToUserId, LocalDateTime assignedOnDt, LocalDateTime completedDt, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.taskId = taskId;
		this.subtaskName = subtaskName;
		this.subTaskDesc = subTaskDesc;
		this.taskStatus = taskStatus;
		this.assignedToUserId = assignedToUserId;
		this.assignedOnDt = assignedOnDt;
		this.completedDt = completedDt;
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
	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	public String getSubtaskName() {
		return subtaskName;
	}
	public void setSubtaskName(String subtaskName) {
		this.subtaskName = subtaskName;
	}
	public String getSubTaskDesc() {
		return subTaskDesc;
	}
	public void setSubTaskDesc(String subTaskDesc) {
		this.subTaskDesc = subTaskDesc;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getAssignedToUserId() {
		return assignedToUserId;
	}
	public void setAssignedToUserId(String assignedToUserId) {
		this.assignedToUserId = assignedToUserId;
	}
	public LocalDateTime getAssignedOnDt() {
		return assignedOnDt;
	}
	public void setAssignedOnDt(LocalDateTime assignedOnDt) {
		this.assignedOnDt = assignedOnDt;
	}
	public LocalDateTime getCompletedDt() {
		return completedDt;
	}
	public void setCompletedDt(LocalDateTime completedDt) {
		this.completedDt = completedDt;
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
		return "SubTask [id=" + id + ", taskId=" + taskId + ", subtaskName=" + subtaskName + ", subTaskDesc="
				+ subTaskDesc + ", taskStatus=" + taskStatus + ", assignedToUserId=" + assignedToUserId
				+ ", assignedOnDt=" + assignedOnDt + ", completedDt=" + completedDt + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	public Object getSubtask() {
		// TODO Auto-generated method stub
		return null;
	}
	
    

}
