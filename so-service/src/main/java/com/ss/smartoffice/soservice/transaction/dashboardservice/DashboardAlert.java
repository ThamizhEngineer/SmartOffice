package com.ss.smartoffice.soservice.transaction.dashboardservice;

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
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name="t_dashboard_alert")
@Component
public class DashboardAlert {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "m_employee_id")
	private String employeeId;
	@Column(name = "auth_feature_id")
	private String featureId;
	@Formula("(select feature.feature_code from auth_feature feature where feature.id=auth_feature_id )")
	private String functionalityCode;
	@Formula("(select feature.feature_code from auth_feature feature where feature.id=auth_feature_id)")
	private String featureCode;
	@Formula("(select feature.display_text from auth_feature feature where feature.id=auth_feature_id)")
	private String displayText;
	
	private String dashCount;
	private String targetRoute;
	private String isEnabled;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getFeatureId() {
		return featureId;
	}
	public void setFeatureId(String featureId) {
		this.featureId = featureId;
	}
	public String getFunctionalityCode() {
		return functionalityCode;
	}
	public void setFunctionalityCode(String functionalityCode) {
		this.functionalityCode = functionalityCode;
	}
	public String getFeatureCode() {
		return featureCode;
	}
	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
	public String getDashCount() {
		return dashCount;
	}
	public void setDashCount(String dashCount) {
		this.dashCount = dashCount;
	}
	public String getTargetRoute() {
		return targetRoute;
	}
	public void setTargetRoute(String targetRoute) {
		this.targetRoute = targetRoute;
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
	public DashboardAlert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DashboardAlert(Integer id, String employeeId, String featureId, String functionalityCode, String featureCode,
			String displayText, String dashCount, String targetRoute, String isEnabled, String createdBy,
			LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.featureId = featureId;
		this.functionalityCode = functionalityCode;
		this.featureCode = featureCode;
		this.displayText = displayText;
		this.dashCount = dashCount;
		this.targetRoute = targetRoute;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "DashboardAlert [id=" + id + ", employeeId=" + employeeId + ", featureId=" + featureId
				+ ", functionalityCode=" + functionalityCode + ", featureCode=" + featureCode + ", displayText="
				+ displayText + ", dashCount=" + dashCount + ", targetRoute=" + targetRoute + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	


	
}
