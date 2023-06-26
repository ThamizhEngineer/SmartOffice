package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_job_emp_comp_matrix")

@Scope("prototype")
public class JobEmpCompMatrix {
 	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
 	private Integer id;
 	@Column(name="t_job_emp_id")
 	private String tJobEmpId;
 	private String isMatched;
 	private String mServiceId;
 	private String mProductId;
 	private String skillLevel;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public JobEmpCompMatrix() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobEmpCompMatrix(Integer id, String tJobEmpId, String isMatched, String mServiceId, String mProductId,
			String skillLevel, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tJobEmpId = tJobEmpId;
		this.isMatched = isMatched;
		this.mServiceId = mServiceId;
		this.mProductId = mProductId;
		this.skillLevel = skillLevel;
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
	public String gettJobEmpId() {
		return tJobEmpId;
	}
	public void settJobEmpId(String tJobEmpId) {
		this.tJobEmpId = tJobEmpId;
	}
	public String getIsMatched() {
		return isMatched;
	}
	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}
	public String getmServiceId() {
		return mServiceId;
	}
	public void setmServiceId(String mServiceId) {
		this.mServiceId = mServiceId;
	}
	public String getmProductId() {
		return mProductId;
	}
	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
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
		return "JobEmpCompMatrix [id=" + id + ", tJobEmpId=" + tJobEmpId + ", isMatched=" + isMatched + ", mServiceId="
				+ mServiceId + ", mProductId=" + mProductId + ", skillLevel=" + skillLevel + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

}
