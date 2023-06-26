package com.ss.smartoffice.soservice.master.JobTypeService;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_job_type_task_type")

@Scope("prototype")
public class JobTypeTaskType {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="job_type_id")
	private Integer jobtypeId;
	private Integer taskTypeOrder;
	@Column(name="t_job_plan_id")
	private String tjobPlanId;
	private Integer taskTypeId;
	@Formula("(select taskType.task_type_name from m_task_type taskType where taskType.id=task_type_id)")
	private String taskTypeName;
	private float weightage;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public JobTypeTaskType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobTypeTaskType(Integer id, Integer jobtypeId, Integer taskTypeOrder, String tjobPlanId, Integer taskTypeId,
			String taskTypeName, float weightage, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobtypeId = jobtypeId;
		this.taskTypeOrder = taskTypeOrder;
		this.tjobPlanId = tjobPlanId;
		this.taskTypeId = taskTypeId;
		this.taskTypeName = taskTypeName;
		this.weightage = weightage;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "JobTypeTaskType [id=" + id + ", jobtypeId=" + jobtypeId + ", taskTypeOrder=" + taskTypeOrder
				+ ", tjobPlanId=" + tjobPlanId + ", taskTypeId=" + taskTypeId + ", taskTypeName=" + taskTypeName
				+ ", weightage=" + weightage + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJobtypeId() {
		return jobtypeId;
	}

	public void setJobtypeId(Integer jobtypeId) {
		this.jobtypeId = jobtypeId;
	}

	public Integer getTaskTypeOrder() {
		return taskTypeOrder;
	}

	public void setTaskTypeOrder(Integer taskTypeOrder) {
		this.taskTypeOrder = taskTypeOrder;
	}

	public String getTjobPlanId() {
		return tjobPlanId;
	}

	public void setTjobPlanId(String tjobPlanId) {
		this.tjobPlanId = tjobPlanId;
	}

	public Integer getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(Integer taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public String getTaskTypeName() {
		return taskTypeName;
	}

	public void setTaskTypeName(String taskTypeName) {
		this.taskTypeName = taskTypeName;
	}

	public float getWeightage() {
		return weightage;
	}

	public void setWeightage(float weightage) {
		this.weightage = weightage;
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

	
	
	
}
