package com.ss.smartoffice.shared.model;

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

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name="t_org_obj_line")
public class OrgLine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "t_org_obj_header_id")
	private String orgObjHeaderId;
	private String metricId;
	@Formula("(SELECT metric.metric_name from m_metric metric where metric.id=metric_id)")
	private String metricName;
	
	@Formula("(select unit.disp_symbol from m_metric metric LEFT join s_units unit on unit.unit_code=metric.unit_type_id where metric.id=metric_id)")
	private String unitTypeSymbol;
	private String metricSummary;
	private String operator;
	private String metricValue;
	@Column(name = "metric_category_id")
	private String metricCategoryId;
	@Formula("(select metricCategory.metric_category_name from s_metric_category metricCategory where metricCategory.id=metric_category_id)")
	private String metricCategoryName;
	@Formula("(select metricCategory.metric_level_code	from s_metric_category metricCategory where metricCategory.id=metric_category_id)")
	private String metricLevelCode;
	private String threshold;
	private String descp;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public OrgLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public OrgLine(Integer id, String orgObjHeaderId, String metricId, String metricName, String unitTypeSymbol,
			String metricSummary, String operator, String metricValue, String metricCategoryId,
			String metricCategoryName, String metricLevelCode, String threshold, String descp, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.orgObjHeaderId = orgObjHeaderId;
		this.metricId = metricId;
		this.metricName = metricName;
		this.unitTypeSymbol = unitTypeSymbol;
		this.metricSummary = metricSummary;
		this.operator = operator;
		this.metricValue = metricValue;
		this.metricCategoryId = metricCategoryId;
		this.metricCategoryName = metricCategoryName;
		this.metricLevelCode = metricLevelCode;
		this.threshold = threshold;
		this.descp = descp;
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
	public String getOrgObjHeaderId() {
		return orgObjHeaderId;
	}
	public void setOrgObjHeaderId(String orgObjHeaderId) {
		this.orgObjHeaderId = orgObjHeaderId;
	}
	public String getMetricId() {
		return metricId;
	}
	public void setMetricId(String metricId) {
		this.metricId = metricId;
	}
	public String getMetricName() {
		return metricName;
	}
	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}
	public String getUnitTypeSymbol() {
		return unitTypeSymbol;
	}
	public void setUnitTypeSymbol(String unitTypeSymbol) {
		this.unitTypeSymbol = unitTypeSymbol;
	}
	public String getMetricSummary() {
		return metricSummary;
	}
	public void setMetricSummary(String metricSummary) {
		this.metricSummary = metricSummary;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getMetricValue() {
		return metricValue;
	}
	public void setMetricValue(String metricValue) {
		this.metricValue = metricValue;
	}
	public String getMetricCategoryId() {
		return metricCategoryId;
	}
	public void setMetricCategoryId(String metricCategoryId) {
		this.metricCategoryId = metricCategoryId;
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
	public String getThreshold() {
		return threshold;
	}
	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}
	public String getDescp() {
		return descp;
	}
	public void setDescp(String descp) {
		this.descp = descp;
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
		return "OrgLine [id=" + id + ", orgObjHeaderId=" + orgObjHeaderId + ", metricId=" + metricId + ", metricName="
				+ metricName + ", unitTypeSymbol=" + unitTypeSymbol + ", metricSummary=" + metricSummary + ", operator="
				+ operator + ", metricValue=" + metricValue + ", metricCategoryId=" + metricCategoryId
				+ ", metricCategoryName=" + metricCategoryName + ", metricLevelCode=" + metricLevelCode + ", threshold="
				+ threshold + ", descp=" + descp + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}
