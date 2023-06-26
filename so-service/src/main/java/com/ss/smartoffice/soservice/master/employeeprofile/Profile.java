package com.ss.smartoffice.soservice.master.employeeprofile;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="m_profile")
	

@Scope("prototype")
public class Profile extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String profileCode;
	private String profileName;
//	private String desiredSkills;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinColumn(name="profile_id")
    
	private List<ProfileLine> profileLines;
	@Formula("(select COUNT(*) from m_profile_line empDetail where empDetail.profile_id=id)")
	private String lineCount;
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profile(int id, String profileCode, String profileName, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt, List<ProfileLine> profileLines,
			String lineCount) {
		super();
		this.id = id;
		this.profileCode = profileCode;
		this.profileName = profileName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.profileLines = profileLines;
		this.lineCount = lineCount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public List<ProfileLine> getProfileLines() {
		return profileLines;
	}
	public void setProfileLines(List<ProfileLine> profileLines) {
		this.profileLines = profileLines;
	}
	public String getLineCount() {
		return lineCount;
	}
	public void setLineCount(String lineCount) {
		this.lineCount = lineCount;
	}
	@Override
	public String toString() {
		return "Profile [id=" + id + ", profileCode=" + profileCode + ", profileName=" + profileName + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + ", profileLines=" + profileLines + ", lineCount=" + lineCount + "]";
	}
	
	
	
}
