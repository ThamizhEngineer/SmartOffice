package com.ss.smartoffice.soservice.seed.metriccategory;

import java.time.LocalDateTime;

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

@Scope("prototype")
@Table(name="s_metric_category")
public class MetricCategory {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String metricCategoryCode;
	private String metricCategoryName;
	private String metricLevelCode;
	private String descrption;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public MetricCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MetricCategory(int id, String metricCategoryCode, String metricCategoryName, String metricLevelCode,
			String descrption, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.metricCategoryCode = metricCategoryCode;
		this.metricCategoryName = metricCategoryName;
		this.metricLevelCode = metricLevelCode;
		this.descrption = descrption;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMetricCategoryCode() {
		return metricCategoryCode;
	}
	public void setMetricCategoryCode(String metricCategoryCode) {
		this.metricCategoryCode = metricCategoryCode;
	}
	public String getMetricCategoryName() {
		return metricCategoryName;
	}
	public void setMetricCategoryName(String metricCategoryName) {
		this.metricCategoryName = metricCategoryName;
	}
	public String getMetricLevelCode() {
		return metricLevelCode;
	}
	public void setMetricLevelCode(String metricLevelCode) {
		this.metricLevelCode = metricLevelCode;
	}
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
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
		return "MetricCategory [id=" + id + ", metricCategoryCode=" + metricCategoryCode + ", metricCategoryName="
				+ metricCategoryName + ", metricLevelCode=" + metricLevelCode + ", descrption=" + descrption
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	
}
