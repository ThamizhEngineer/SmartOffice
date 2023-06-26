package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "auth_user_type")

public class AuthUserType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String UserTypeCode;
	private String UserTypeName;
	private String soWebapp;
	private String soMobile;
	private String recruitmentWebapp;
	private String isEnabled;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public AuthUserType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AuthUserType(Integer id, String userTypeCode, String userTypeName, String soWebapp, String soMobile,
			String recruitmentWebapp, String isEnabled, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		UserTypeCode = userTypeCode;
		UserTypeName = userTypeName;
		this.soWebapp = soWebapp;
		this.soMobile = soMobile;
		this.recruitmentWebapp = recruitmentWebapp;
		this.isEnabled = isEnabled;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserTypeCode() {
		return UserTypeCode;
	}
	public void setUserTypeCode(String userTypeCode) {
		UserTypeCode = userTypeCode;
	}
	public String getUserTypeName() {
		return UserTypeName;
	}
	public void setUserTypeName(String userTypeName) {
		UserTypeName = userTypeName;
	}
	public String getSoWebapp() {
		return soWebapp;
	}
	public void setSoWebapp(String soWebapp) {
		this.soWebapp = soWebapp;
	}
	public String getSoMobile() {
		return soMobile;
	}
	public void setSoMobile(String soMobile) {
		this.soMobile = soMobile;
	}
	public String getRecruitmentWebapp() {
		return recruitmentWebapp;
	}
	public void setRecruitmentWebapp(String recruitmentWebapp) {
		this.recruitmentWebapp = recruitmentWebapp;
	}
	public String getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
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
		return "AuthUserType [id=" + id + ", UserTypeCode=" + UserTypeCode + ", UserTypeName=" + UserTypeName
				+ ", soWebapp=" + soWebapp + ", soMobile=" + soMobile + ", recruitmentWebapp=" + recruitmentWebapp
				+ ", isEnabled=" + isEnabled + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
}
