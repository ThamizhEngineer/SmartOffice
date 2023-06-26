package com.ss.smartoffice.soauthservice.model.auth;

import java.time.LocalDateTime;
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
@Table(name ="auth_feature")

public class AuthFeature extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String functionalityId;
//	@Formula("(select pro.proj_name from t_project pro where pro.id=proj_id)")
	@Formula("(select fun.functionality_code from auth_functionality fun where fun.id=functionality_id)")
	private String functionalityCode;
	@Formula("(select fun.functionality_name from auth_functionality fun where fun.id=functionality_id)")
	private String functionalityName;
	private String featureCode;
	private String featureName;
	private String description;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public AuthFeature() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthFeature(Integer id, String functionalityCode, String featureCode, String featureName, String description,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.functionalityCode = functionalityCode;
		this.featureCode = featureCode;
		this.featureName = featureName;
		this.description = description;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	

	public AuthFeature(Integer id, String functionalityId, String functionalityCode, String functionalityName,
			String featureCode, String featureName, String description, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.functionalityId = functionalityId;
		this.functionalityCode = functionalityCode;
		this.functionalityName = functionalityName;
		this.featureCode = featureCode;
		this.featureName = featureName;
		this.description = description;
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

	public String getFunctionalityId() {
		return functionalityId;
	}

	public void setFunctionalityId(String functionalityId) {
		this.functionalityId = functionalityId;
	}

	public String getFunctionalityCode() {
		return functionalityCode;
	}

	public void setFunctionalityCode(String functionalityCode) {
		this.functionalityCode = functionalityCode;
	}

	public String getFunctionalityName() {
		return functionalityName;
	}

	public void setFunctionalityName(String functionalityName) {
		this.functionalityName = functionalityName;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return "AuthFeature [id=" + id + ", functionalityId=" + functionalityId + ", functionalityCode="
				+ functionalityCode + ", functionalityName=" + functionalityName + ", featureCode=" + featureCode
				+ ", featureName=" + featureName + ", description=" + description + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}



	
}
