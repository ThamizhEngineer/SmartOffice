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
@Table(name ="auth_sub_menu")

public class AuthSubMenu extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@Column(name="auth_menu_id")
	private String authMenuId;
	private String name;
	private String state;
	private String subState;
	private String type;
	
	private String authFeatureId;
	@Formula("(SELECT CONCAT(af2.feature_name,'-',af.functionality_name) FROM auth_feature af2 left join auth_functionality af on af2.functionality_id = af.id where af2.id=auth_feature_id)")
	private String authFeature;
	private String purpose;
	private Integer menuOrder;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public AuthSubMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthSubMenu(String id, String authMenuId, String name, String state, String subState, String type,
			String authFeatureId, String authFeature, String purpose, Integer menuOrder, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.authMenuId = authMenuId;
		this.name = name;
		this.state = state;
		this.subState = subState;
		this.type = type;
		this.authFeatureId = authFeatureId;
		this.authFeature = authFeature;
		this.purpose = purpose;
		this.menuOrder = menuOrder;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAuthMenuId() {
		return authMenuId;
	}
	public void setAuthMenuId(String authMenuId) {
		this.authMenuId = authMenuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSubState() {
		return subState;
	}
	public void setSubState(String subState) {
		this.subState = subState;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
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
		return "AuthSubMenu [id=" + id + ", authMenuId=" + authMenuId + ", name=" + name + ", state=" + state
				+ ", subState=" + subState + ", type=" + type + ", authFeatureId=" + authFeatureId + ", authFeature="
				+ authFeature + ", purpose=" + purpose + ", menuOrder=" + menuOrder + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
