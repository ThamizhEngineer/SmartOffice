package com.ss.smartoffice.soservice.transaction.model;

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
@Table(name="t_job_material")

@Scope("prototype")
public class JobMaterial {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private String jobId;
	private Integer taskTypeOrder;
	private String materialTypeOrder;
	private String materialQuantity;
	@Formula("(select material.material_name from m_material material where material.material_code=material_type_order)")
	private String materialTypeName;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public JobMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobMaterial(Integer id, String jobId, Integer taskTypeOrder, String materialTypeOrder,
			String materialQuantity, String materialTypeName, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.taskTypeOrder = taskTypeOrder;
		this.materialTypeOrder = materialTypeOrder;
		this.materialQuantity = materialQuantity;
		this.materialTypeName = materialTypeName;
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

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Integer getTaskTypeOrder() {
		return taskTypeOrder;
	}

	public void setTaskTypeOrder(Integer taskTypeOrder) {
		this.taskTypeOrder = taskTypeOrder;
	}

	public String getMaterialTypeOrder() {
		return materialTypeOrder;
	}

	public void setMaterialTypeOrder(String materialTypeOrder) {
		this.materialTypeOrder = materialTypeOrder;
	}

	public String getMaterialQuantity() {
		return materialQuantity;
	}

	public void setMaterialQuantity(String materialQuantity) {
		this.materialQuantity = materialQuantity;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
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
		return "JobMaterial [id=" + id + ", jobId=" + jobId + ", taskTypeOrder=" + taskTypeOrder
				+ ", materialTypeOrder=" + materialTypeOrder + ", materialQuantity=" + materialQuantity
				+ ", materialTypeName=" + materialTypeName + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	
	
}
