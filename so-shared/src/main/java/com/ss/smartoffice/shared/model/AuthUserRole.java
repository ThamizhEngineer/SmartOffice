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
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name ="auth_user_role")

public class AuthUserRole extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="auth_user_id")
	private String authUserId;
	private Integer authRoleId;
	
	@Formula("(select ar.role_code from auth_role ar where ar.id=auth_role_id)")
	private String authRoleCode;
	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public AuthUserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthUserRole(Integer id, String authUserId, Integer authRoleId, String authRoleCode, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.authUserId = authUserId;
		this.authRoleId = authRoleId;
		this.authRoleCode = authRoleCode;
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
	public String getAuthUserId() {
		return authUserId;
	}
	public void setAuthUserId(String authUserId) {
		this.authUserId = authUserId;
	}
	public Integer getAuthRoleId() {
		return authRoleId;
	}
	public void setAuthRoleId(Integer authRoleId) {
		this.authRoleId = authRoleId;
	}
	public String getAuthRoleCode() {
		return authRoleCode;
	}
	public void setAuthRoleCode(String authRoleCode) {
		this.authRoleCode = authRoleCode;
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
		return "AuthUserRole [id=" + id + ", authUserId=" + authUserId + ", authRoleId=" + authRoleId
				+ ", authRoleCode=" + authRoleCode + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}
	



