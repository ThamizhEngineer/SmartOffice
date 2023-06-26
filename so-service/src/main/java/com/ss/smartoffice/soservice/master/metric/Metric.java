package com.ss.smartoffice.soservice.master.metric;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Scope("prototype")
@Table(name="m_metric")
public class Metric {
	@Id

	private String id;
	
	@Column(name="metric_name")
	private String metricName;
	
	@Column(name="unit_type_id")
	private String unitTypeId;
	
	@Formula("(select unit.disp_symbol from s_units unit where unit.unit_code =unit_type_id)")
	private String unitTypeSymbol;
	
	
	
	@Column(name="better")
	private String better;
	
	@Column(name="descp")
	private String descp;
	
	@Column(name="metric_category_id")
	private String smetricCategoryId;
	
	
	@Formula("(SELECT metric.metric_category_name from s_metric_category metric where metric.id= metric_category_id)")
	private String metricCatName;
	
	@Formula("(SELECT metric.metric_level_code from s_metric_category metric where metric.id= metric_category_id)")
	private String metricCatCode;
	
	@Column(name="is_enabled")
	private String isEnabled;
	
	@Column(name="created_by")
	private String createdBy;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="created_dt")
	private LocalDateTime createdDt;
	
	@Column(name="modified_by")
	private String modifiedBy;
	
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(name="modified_dt")
	private LocalDateTime modifiedDt;
	
	private String threshold;
	
	public Metric() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Metric(String id, String metricName, String unitTypeId, String unitTypeSymbol, String better, String descp,
			String smetricCategoryId, String metricCatName, String metricCatCode, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt, String threshold) {
		super();
		this.id = id;
		this.metricName = metricName;
		this.unitTypeId = unitTypeId;
		this.unitTypeSymbol = unitTypeSymbol;
		this.better = better;
		this.descp = descp;
		this.smetricCategoryId = smetricCategoryId;
		this.metricCatName = metricCatName;
		this.metricCatCode = metricCatCode;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.threshold = threshold;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

	public String getUnitTypeId() {
		return unitTypeId;
	}

	public void setUnitTypeId(String unitTypeId) {
		this.unitTypeId = unitTypeId;
	}

	public String getUnitTypeSymbol() {
		return unitTypeSymbol;
	}

	public void setUnitTypeSymbol(String unitTypeSymbol) {
		this.unitTypeSymbol = unitTypeSymbol;
	}

	public String getBetter() {
		return better;
	}

	public void setBetter(String better) {
		this.better = better;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getSmetricCategoryId() {
		return smetricCategoryId;
	}

	public void setSmetricCategoryId(String smetricCategoryId) {
		this.smetricCategoryId = smetricCategoryId;
	}

	public String getMetricCatName() {
		return metricCatName;
	}

	public void setMetricCatName(String metricCatName) {
		this.metricCatName = metricCatName;
	}

	public String getMetricCatCode() {
		return metricCatCode;
	}

	public void setMetricCatCode(String metricCatCode) {
		this.metricCatCode = metricCatCode;
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

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	@Override
	public String toString() {
		return "Metric [id=" + id + ", metricName=" + metricName + ", unitTypeId=" + unitTypeId + ", unitTypeSymbol="
				+ unitTypeSymbol + ", better=" + better + ", descp=" + descp + ", smetricCategoryId="
				+ smetricCategoryId + ", metricCatName=" + metricCatName + ", metricCatCode=" + metricCatCode
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + ", threshold=" + threshold + "]";
	}


	
}