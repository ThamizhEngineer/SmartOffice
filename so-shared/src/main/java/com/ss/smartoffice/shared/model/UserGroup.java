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
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "s_user_group")

@Scope("prototype")
public class UserGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "is_hr_l1")
	private String isHrL1;
	@Column(name = "is_hr_l2")
	private String isHrL2;
	@Column(name = "is_acct_l1")
	private String isAcctL1;
	@Column(name = "is_acct_l2")
	private String isAcctL2;
	@Column(name = "is_dir")
	private String isDir;
	@Column(name = "is_admin")
	private String isAdmin;
	private String userGroupCode;
	private String userGroupName;
	private String description;
	private String officeId;
	@Formula("(select offi.office_name from m_office offi where offi.id=office_id)")
	private String officeName;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Column(name = "is_mgnt")
	private String isMgnt;

	public UserGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserGroup(int id, String isHrL1, String isHrL2, String isAcctL1, String isAcctL2, String isDir,
			String isAdmin, String userGroupCode, String userGroupName, String description, String officeId,
			String officeName, String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt,
			LocalDateTime modifiedDt, String isMgnt) {
		super();
		this.id = id;
		this.isHrL1 = isHrL1;
		this.isHrL2 = isHrL2;
		this.isAcctL1 = isAcctL1;
		this.isAcctL2 = isAcctL2;
		this.isDir = isDir;
		this.isAdmin = isAdmin;
		this.userGroupCode = userGroupCode;
		this.userGroupName = userGroupName;
		this.description = description;
		this.officeId = officeId;
		this.officeName = officeName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.isMgnt = isMgnt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUserGroupCode() {
		return userGroupCode;
	}

	public void setUserGroupCode(String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}

	public String getUserGroupName() {
		return userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
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

	public String getIsMgnt() {
		return isMgnt;
	}

	public void setIsMgnt(String isMgnt) {
		this.isMgnt = isMgnt;
	}

	@Override
	public String toString() {
		return "UserGroup [id=" + id + ", isHrL1=" + isHrL1 + ", isHrL2=" + isHrL2 + ", isAcctL1=" + isAcctL1
				+ ", isAcctL2=" + isAcctL2 + ", isDir=" + isDir + ", isAdmin=" + isAdmin + ", userGroupCode="
				+ userGroupCode + ", userGroupName=" + userGroupName + ", description=" + description + ", officeId="
				+ officeId + ", officeName=" + officeName + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", isMgnt="
				+ isMgnt + "]";
	}

}
