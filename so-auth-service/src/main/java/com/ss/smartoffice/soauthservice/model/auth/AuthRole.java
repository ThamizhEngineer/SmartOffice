package com.ss.smartoffice.soauthservice.model.auth;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;


@Entity
@Table(name ="auth_role")


public class AuthRole extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String roleCode;
	private String roleName;
	private String remarks;
	private String systemMapping;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="auth_role_id")
	private List<AuthRoleFeature> authRoleFeatures;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public AuthRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthRole(String id, String roleCode, String roleName, String remarks, String systemMapping,
			List<AuthRoleFeature> authRoleFeatures, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.remarks = remarks;
		this.systemMapping = systemMapping;
		this.authRoleFeatures = authRoleFeatures;
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
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getSystemMapping() {
		return systemMapping;
	}
	public void setSystemMapping(String systemMapping) {
		this.systemMapping = systemMapping;
	}
	public List<AuthRoleFeature> getAuthRoleFeatures() {
		return authRoleFeatures;
	}
	public void setAuthRoleFeatures(List<AuthRoleFeature> authRoleFeatures) {
		this.authRoleFeatures = authRoleFeatures;
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
		return "AuthRole [id=" + id + ", roleCode=" + roleCode + ", roleName=" + roleName + ", remarks=" + remarks
				+ ", systemMapping=" + systemMapping + ", authRoleFeatures=" + authRoleFeatures + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	

}
