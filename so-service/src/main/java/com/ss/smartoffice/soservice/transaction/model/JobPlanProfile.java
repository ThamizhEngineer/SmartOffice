 package com.ss.smartoffice.soservice.transaction.model;
 
 import java.time.LocalDateTime;
 
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
import com.ss.smartoffice.soservice.master.employeeprofile.Profile;


 
 @Entity
 @Table(name="t_job_plan_profile")
 
 @Scope("prototype")
 public class JobPlanProfile {
 	@Id
 	@GeneratedValue(strategy=GenerationType.IDENTITY)
 	private Integer id;
	@Column(name="t_job_id")
	private Integer tJobId;
// 	private String mJobTypeId;
// 	@Formula("(select jobType.job_type_name from m_job_type jobType where jobType.id=m_job_type_id)")
// 	private String mJobTypeName;
 	private String profileId;
 	@Formula("(select profile.profile_code from m_profile profile where profile.id=profile_id)")
 	private String profileCode;
 	@Formula("(select profile.profile_name from m_profile profile where profile.id=profile_id)")
 	private String profileName;
 	private String headCount;
 	@Formula("(select COUNT(*) from  m_profile profile where profile.id=profile_id)")
 	private String actualCount;
 	private String notes;
 	@Transient
 	private Profile profile;
 	private String isEnabled;
 	private String createdBy;
 	private String modifiedBy;
 	@CreationTimestamp
 	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
 	private LocalDateTime createdDt;
 	@UpdateTimestamp
 	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
 	private LocalDateTime modifiedDt;
	public JobPlanProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobPlanProfile(Integer id, Integer tJobId, String profileId, String profileCode, String profileName,
			String headCount, String actualCount, String notes, Profile profile, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.tJobId = tJobId;
		this.profileId = profileId;
		this.profileCode = profileCode;
		this.profileName = profileName;
		this.headCount = headCount;
		this.actualCount = actualCount;
		this.notes = notes;
		this.profile = profile;
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
	public Integer gettJobId() {
		return tJobId;
	}
	public void settJobId(Integer tJobId) {
		this.tJobId = tJobId;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getProfileCode() {
		return profileCode;
	}
	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public String getHeadCount() {
		return headCount;
	}
	public void setHeadCount(String headCount) {
		this.headCount = headCount;
	}
	public String getActualCount() {
		return actualCount;
	}
	public void setActualCount(String actualCount) {
		this.actualCount = actualCount;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
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
		return "JobPlanProfile [id=" + id + ", tJobId=" + tJobId + ", profileId=" + profileId + ", profileCode="
				+ profileCode + ", profileName=" + profileName + ", headCount=" + headCount + ", actualCount="
				+ actualCount + ", notes=" + notes + ", profile=" + profile + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}
	
	
 	
}