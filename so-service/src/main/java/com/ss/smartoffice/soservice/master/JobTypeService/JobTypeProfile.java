package com.ss.smartoffice.soservice.master.JobTypeService;

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
@Table(name="m_job_type_profile")

@Scope("prototype")
public class JobTypeProfile {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="job_type_id")
	private Integer jobtypeId;
	private Integer taskTypeOrder;
	private String skillId;
	private String skillLevelCode;
	private String mProfileId;
 	@Formula("(select profile.profile_code from m_profile profile where profile.id=m_profile_id)")
 	private String mProfileCode;
 	@Formula("(select profile.profile_name from m_profile profile where profile.id=m_profile_id)")
 	private String profileName;
	private Integer profilesInvolved;
	private String profileDescription;
	private String headCount;
	private String notes;
	private String isRemote;
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
	
	public JobTypeProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJobtypeId() {
		return jobtypeId;
	}

	public void setJobtypeId(Integer jobtypeId) {
		this.jobtypeId = jobtypeId;
	}

	public Integer getTaskTypeOrder() {
		return taskTypeOrder;
	}

	public void setTaskTypeOrder(Integer taskTypeOrder) {
		this.taskTypeOrder = taskTypeOrder;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getSkillLevelCode() {
		return skillLevelCode;
	}

	public void setSkillLevelCode(String skillLevelCode) {
		this.skillLevelCode = skillLevelCode;
	}

	public String getmProfileId() {
		return mProfileId;
	}

	public void setmProfileId(String mProfileId) {
		this.mProfileId = mProfileId;
	}

	public String getmProfileCode() {
		return mProfileCode;
	}

	public void setmProfileCode(String mProfileCode) {
		this.mProfileCode = mProfileCode;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Integer getProfilesInvolved() {
		return profilesInvolved;
	}

	public void setProfilesInvolved(Integer profilesInvolved) {
		this.profilesInvolved = profilesInvolved;
	}

	public String getProfileDescription() {
		return profileDescription;
	}

	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	public String getHeadCount() {
		return headCount;
	}

	public void setHeadCount(String headCount) {
		this.headCount = headCount;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getIsRemote() {
		return isRemote;
	}

	public void setIsRemote(String isRemote) {
		this.isRemote = isRemote;
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

	public JobTypeProfile(Integer id, Integer jobtypeId, Integer taskTypeOrder, String skillId, String skillLevelCode,
			String mProfileId, String mProfileCode, String profileName, Integer profilesInvolved,
			String profileDescription, String headCount, String notes, String isRemote, Profile profile,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobtypeId = jobtypeId;
		this.taskTypeOrder = taskTypeOrder;
		this.skillId = skillId;
		this.skillLevelCode = skillLevelCode;
		this.mProfileId = mProfileId;
		this.mProfileCode = mProfileCode;
		this.profileName = profileName;
		this.profilesInvolved = profilesInvolved;
		this.profileDescription = profileDescription;
		this.headCount = headCount;
		this.notes = notes;
		this.isRemote = isRemote;
		this.profile = profile;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "JobTypeProfile [id=" + id + ", jobtypeId=" + jobtypeId + ", taskTypeOrder=" + taskTypeOrder
				+ ", skillId=" + skillId + ", skillLevelCode=" + skillLevelCode + ", mProfileId=" + mProfileId
				+ ", mProfileCode=" + mProfileCode + ", profileName=" + profileName + ", profilesInvolved="
				+ profilesInvolved + ", profileDescription=" + profileDescription + ", headCount=" + headCount
				+ ", notes=" + notes + ", isRemote=" + isRemote + ", profile=" + profile + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

	
	
	
}
