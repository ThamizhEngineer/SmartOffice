package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.soservice.transaction.task.Task;

@Entity
@Table(name="t_job_task_list")

@Scope("prototype")
public class JobTaskList {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_milestone_id")
	private Integer milestoneId;
	private String taskListName;
	private String taskListDesc;
	private String status;
	private Integer progress;
	private Integer taskListOrder;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "taskListId")
	private List<Task> tasks;
	public JobTaskList() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobTaskList(Integer id, Integer milestoneId, String taskListName, String taskListDesc, String status,
			Integer progress, Integer taskListOrder, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.milestoneId = milestoneId;
		this.taskListName = taskListName;
		this.taskListDesc = taskListDesc;
		this.status = status;
		this.progress = progress;
		this.taskListOrder = taskListOrder;
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
	public Integer getMilestoneId() {
		return milestoneId;
	}
	public void setMilestoneId(Integer milestoneId) {
		this.milestoneId = milestoneId;
	}
	public String getTaskListName() {
		return taskListName;
	}
	public void setTaskListName(String taskListName) {
		this.taskListName = taskListName;
	}
	public String getTaskListDesc() {
		return taskListDesc;
	}
	public void setTaskListDesc(String taskListDesc) {
		this.taskListDesc = taskListDesc;
	}
	public String TaskListOrder() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public Integer getTaskListOrder() {
		return taskListOrder;
	}
	public void setTaskListOrder(Integer taskListOrder) {
		this.taskListOrder = taskListOrder;
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
		return "JobTaskList [id=" + id + ", milestoneId=" + milestoneId + ", taskListName=" + taskListName
				+ ", taskListDesc=" + taskListDesc + ", status=" + status + ", progress=" + progress
				+ ", taskListOrder=" + taskListOrder + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	public void add(JobTaskList jobTaskList2) {
		// TODO Auto-generated method stub
		
	}
	public void setJobTaskList(List<JobTaskList> jobTaskLists) {
		// TODO Auto-generated method stub
		
	}
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	
	
	
	
	
	
	
	
}
