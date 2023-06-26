package com.ss.smartoffice.soauthservice.model.auth;
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
import com.ss.smartoffice.shared.common.BaseEntity;



@Entity
@Table(name ="auth_role_feature")

public class AuthRoleFeature extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="auth_role_id")
	private Integer authRoleId;
	@Formula("(select role.role_name from auth_role role where role.id = auth_role_id)")
	private String roleName;
	@Column(name="auth_feature_id")
	private String authFeatureId;
	@Formula("(select feature.feature_name from auth_feature feature where feature.id = auth_feature_id)")
	private String featureName;
	@Formula("(SELECT CONCAT(af2.feature_name,'-',af.functionality_name) FROM auth_feature af2 left join auth_functionality af on af2.functionality_id = af.id where af2.id=auth_feature_id)")
	private String authFeature;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public AuthRoleFeature() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthRoleFeature(Integer id, Integer authRoleId, String roleName, String authFeatureId, String featureName,
			String authFeature, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.authRoleId = authRoleId;
		this.roleName = roleName;
		this.authFeatureId = authFeatureId;
		this.featureName = featureName;
		this.authFeature = authFeature;
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
	public Integer getAuthRoleId() {
		return authRoleId;
	}
	public void setAuthRoleId(Integer authRoleId) {
		this.authRoleId = authRoleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAuthFeatureId() {
		return authFeatureId;
	}
	public void setAuthFeatureId(String authFeatureId) {
		this.authFeatureId = authFeatureId;
	}
	
	public String getAuthFeature() {
		return authFeature;
	}

	public void setAuthFeature(String authFeature) {
		this.authFeature = authFeature;
	}

	public String getFeatureName() {
		return featureName;
	}
	public void setFeatureName(String featureName) {
		this.featureName = featureName;
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
		return "AuthRoleFeature [id=" + id + ", authRoleId=" + authRoleId + ", roleName=" + roleName
				+ ", authFeatureId=" + authFeatureId + ", featureName=" + featureName + ", authFeature=" + authFeature
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	
}
