package com.ss.smartoffice.sonotificationservice.dashboardalert;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuthRoleFeature {
	private Integer id;
	private Integer authRoleId;
	private String authFeatureId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public AuthRoleFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthRoleFeature(Integer id, Integer authRoleId, String authFeatureId, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.authRoleId = authRoleId;
		this.authFeatureId = authFeatureId;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "AuthRoleFeature [id=" + id + ", authRoleId=" + authRoleId + ", authFeatureId=" + authFeatureId
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAuthRoleId() {
		return authRoleId;
	}

	public void setAuthRoleId(Integer authRoleId) {
		this.authRoleId = authRoleId;
	}

	public String getAuthFeatureId() {
		return authFeatureId;
	}

	public void setAuthFeatureId(String authFeatureId) {
		this.authFeatureId = authFeatureId;
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
