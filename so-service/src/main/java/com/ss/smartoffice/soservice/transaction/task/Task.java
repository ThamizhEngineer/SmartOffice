package com.ss.smartoffice.soservice.transaction.task;


import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.Comment.Comment;

@Entity
@Table(name = "t_task")

@Scope("prototype")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String taskName;
	private String taskDesc;
	private String taskStatus;
	private Integer taskListId;
	private String assignedToUserId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime assignedOnDt;
	private String isDelayed;
	private String isBlocked;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime blockedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime delayedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime completedDt;
	private Integer jobId;
	@Formula("(select j.job_code from t_job j where j.id = job_id)")
	private String jobCode;
	private Integer partnerId;
	@Formula("(select c.client_name from m_partner c where c.id = partner_id)")
	private String clientName;
	private Integer endClientId;
	@Formula("(select ec.client_name from m_partner ec where ec.id = end_client_id)")
	private String endClientName;
	private Integer milestoneId;
	@Formula("(select m.milestone_name from t_job_milestone m where m.id=milestone_id)")
	private String milestoneName;
	private Integer taskTypeId;
	
	@Formula("(select t.task_list_name from t_job_task_list t where t.id=task_list_id)")
	private String taskListName;
	@Formula("(select tt.task_type_name from m_task_type tt where tt.id=task_type_id)")
	private String taskType;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	
	@Formula("(select extract(month from t.created_dt) from t_task t where t.id=id)")
	private String month;
	
	@Formula("(select extract(year from t.created_dt) from t_task t where t.id=id)")
	private String year;
	 
	@Transient
	  private List<Comment> comments;
	  

	 @CreationTimestamp
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	//Child Table
	@OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name ="taskId")
	private List<SubTask> subTasks;
	
	//Child Table - Log Task History
	@OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name ="id")
	private List<TaskHistory> taskHistory;

	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Task(Integer id, String taskType, String taskStatus, Integer taskListId, String assignedToUserId,
			String isDelayed, String isBlocked, Integer jobId, Integer endClientId, Integer milestoneId,
			 Integer taskTypeId) {
		super();
		this.id = id;
		this.taskType = taskType;
		this.taskStatus = taskStatus;
		this.taskListId = taskListId;
		this.assignedToUserId = assignedToUserId;
		this.isDelayed = isDelayed;
		this.isBlocked = isBlocked;
		this.jobId = jobId;
		this.endClientId = endClientId;
		this.milestoneId = milestoneId;
		this.taskTypeId = taskTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getTaskListId() {
		return taskListId;
	}

	public void setTaskListId(Integer taskListId) {
		this.taskListId = taskListId;
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

	public String getIsDelayed() {
		return isDelayed;
	}

	public void setIsDelayed(String isDelayed) {
		this.isDelayed = isDelayed;
	}

	public String getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(String isBlocked) {
		this.isBlocked = isBlocked;
	}

	public LocalDateTime getBlockedDt() {
		return blockedDt;
	}

	public void setBlockedDt(LocalDateTime blockedDt) {
		this.blockedDt = blockedDt;
	}

	public LocalDateTime getDelayedDt() {
		return delayedDt;
	}

	public void setDelayedDt(LocalDateTime delayedDt) {
		this.delayedDt = delayedDt;
	}

	public LocalDateTime getCompletedDt() {
		return completedDt;
	}

	public void setCompletedDt(LocalDateTime completedDt) {
		this.completedDt = completedDt;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getEndClientId() {
		return endClientId;
	}

	public void setEndClientId(Integer endClientId) {
		this.endClientId = endClientId;
	}

	public String getEndClientName() {
		return endClientName;
	}

	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
	}

	public Integer getMilestoneId() {
		return milestoneId;
	}

	public void setMilestoneId(Integer milestoneId) {
		this.milestoneId = milestoneId;
	}

	public String getMilestoneName() {
		return milestoneName;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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

	public List<SubTask> getSubTasks() {
		return subTasks;
	}

	public void setSubTasks(List<SubTask> subTasks) {
		this.subTasks = subTasks;
	}

	public List<TaskHistory> getTaskHistory() {
		return taskHistory;
	}

	public void setTaskHistory(List<TaskHistory> taskHistory) {
		this.taskHistory = taskHistory;
	}


	public String getTaskListName() {
		return taskListName;
	}

	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + ", taskDesc=" + taskDesc + ", taskStatus=" + taskStatus
				+ ", taskListId=" + taskListId + ", assignedToUserId=" + assignedToUserId + ", assignedOnDt="
				+ assignedOnDt + ", isDelayed=" + isDelayed + ", isBlocked=" + isBlocked + ", blockedDt=" + blockedDt
				+ ", delayedDt=" + delayedDt + ", completedDt=" + completedDt + ", jobId=" + jobId + ", jobCode="
				+ jobCode + ", partnerId=" + partnerId + ", clientName=" + clientName + ", endClientId=" + endClientId
				+ ", endClientName=" + endClientName + ", milestoneId=" + milestoneId + ", milestoneName="
				+ milestoneName + ", taskTypeId=" + taskTypeId + ", taskListName=" + taskListName + ", taskType="
				+ taskType + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", month=" + month + ", year=" + year + ", comments=" + comments + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", subTasks=" + subTasks + ", taskHistory=" + taskHistory + "]";
	}



	
	
	
	

}
