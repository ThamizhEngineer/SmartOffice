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
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_job_plan_material")

@Component
@Scope("prototype")
public class JobPlanMaterial {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private String jobId;
	private String materialId;
	@Formula("(select material.material_name from m_material material where material.id=material_id)")
	private String materialName;
	
	@Formula("(select material.material_code from m_material material where material.id=material_id)")
	private String materialCode; 
	private Integer taskTypeOrder;
	private String materialTypeOrder;
	private String materialQuantity;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public JobPlanMaterial() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPlanMaterial(Integer id, String jobId, String materialId, String materialName, String materialCode,
			Integer taskTypeOrder, String materialTypeOrder, String materialQuantity, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialCode = materialCode;
		this.taskTypeOrder = taskTypeOrder;
		this.materialTypeOrder = materialTypeOrder;
		this.materialQuantity = materialQuantity;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "JobPlanMaterial [id=" + id + ", jobId=" + jobId + ", materialId=" + materialId + ", materialName="
				+ materialName + ", materialCode=" + materialCode + ", taskTypeOrder=" + taskTypeOrder
				+ ", materialTypeOrder=" + materialTypeOrder + ", materialQuantity=" + materialQuantity + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
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

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
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
