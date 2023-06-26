package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="m_user_group_emp")

@Scope("prototype")
public class UserGroupEmployeeMapping {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String userGroupId ;
	@Formula("(SELECT usergroup.user_group_name FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String userGroupName;
	@Formula("(SELECT usergroup.is_hr_l1 FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isHrL1;
	@Formula("(SELECT usergroup.is_hr_l2 FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isHrL2;
	
	@Formula("(SELECT usergroup.is_acct_l1 FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isAcctL1;
	@Formula("(SELECT usergroup.is_acct_l2 FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isAcctL2;
	@Formula("(SELECT usergroup.is_dir FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isDir;
	@Formula("(SELECT usergroup.is_admin FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isAdmin;
	@Column(name="employee_id")
	private String employeeId;
	@Formula("(SELECT emp.emp_name FROM m_employee emp WHERE emp.id=employee_id)")
	private String employeeName;
	@Formula("(SELECT usergroup.is_mgnt FROM s_user_group usergroup WHERE usergroup.id=user_group_id)")
	private String isMgnt; 
	// Getting whole auth user object mapping it to AuthUserSummary id by mapping it to 
	//employee id relationship hence added it as a seperate colmn

	@Column(name="auth_user_id")
	private String authUserId;
	
	@Transient
	private AuthUserSummary authUser;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	public UserGroupEmployeeMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserGroupEmployeeMapping(int id, String userGroupId, String userGroupName, String isHrL1, String isHrL2,
			String isAcctL1, String isAcctL2, String isDir, String isAdmin, String employeeId, String employeeName,
			String isMgnt, String authUserId, AuthUserSummary authUser, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.userGroupId = userGroupId;
		this.userGroupName = userGroupName;
		this.isHrL1 = isHrL1;
		this.isHrL2 = isHrL2;
		this.isAcctL1 = isAcctL1;
		this.isAcctL2 = isAcctL2;
		this.isDir = isDir;
		this.isAdmin = isAdmin;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.isMgnt = isMgnt;
		this.authUserId = authUserId;
		this.authUser = authUser;
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

	public String getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(String userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getIsHrL1() {
		return isHrL1;
	}

	public void setIsHrL1(String isHrL1) {
		this.isHrL1 = isHrL1;
	}

	public String getIsHrL2() {
		return isHrL2;
	}

	public void setIsHrL2(String isHrL2) {
		this.isHrL2 = isHrL2;
	}

	public String getIsAcctL1() {
		return isAcctL1;
	}

	public void setIsAcctL1(String isAcctL1) {
		this.isAcctL1 = isAcctL1;
	}

	public String getIsAcctL2() {
		return isAcctL2;
	}

	public void setIsAcctL2(String isAcctL2) {
		this.isAcctL2 = isAcctL2;
	}

	public String getIsDir() {
		return isDir;
	}

	public void setIsDir(String isDir) {
		this.isDir = isDir;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getIsMgnt() {
		return isMgnt;
	}

	public void setIsMgnt(String isMgnt) {
		this.isMgnt = isMgnt;
	}

	public String getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(String authUserId) {
		this.authUserId = authUserId;
	}

	public AuthUserSummary getAuthUser() {
		return authUser;
	}

	public void setAuthUser(AuthUserSummary authUser) {
		this.authUser = authUser;
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
		return "UserGroupEmployeeMapping [id=" + id + ", userGroupId=" + userGroupId + ", userGroupName="
				+ userGroupName + ", isHrL1=" + isHrL1 + ", isHrL2=" + isHrL2 + ", isAcctL1=" + isAcctL1 + ", isAcctL2="
				+ isAcctL2 + ", isDir=" + isDir + ", isAdmin=" + isAdmin + ", employeeId=" + employeeId
				+ ", employeeName=" + employeeName + ", isMgnt=" + isMgnt + ", authUserId=" + authUserId + ", authUser="
				+ authUser + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
}
