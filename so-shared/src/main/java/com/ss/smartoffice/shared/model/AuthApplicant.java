package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuthApplicant {

	private Integer authId;
	private Integer applicantId;
	private String applicant;
	private String emailId;
	private String userName;
	private String userStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime loginAccess;
	public AuthApplicant() {
		super();
		// TODO Auto-generated constructor stub
	}	
	public AuthApplicant(Integer authId, Integer applicantId, String applicant, String emailId, String userName,
			String userStatus, LocalDateTime loginAccess) {
		super();
		this.authId = authId;
		this.applicantId = applicantId;
		this.applicant = applicant;
		this.emailId = emailId;
		this.userName = userName;
		this.userStatus = userStatus;
		this.loginAccess = loginAccess;
	}


	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}
	
	public Integer getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Integer applicantId) {
		this.applicantId = applicantId;
	}

	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public LocalDateTime getLoginAccess() {
		return loginAccess;
	}
	public void setLoginAccess(LocalDateTime loginAccess) {
		this.loginAccess = loginAccess;
	}

	@Override
	public String toString() {
		return "AuthApplicant [authId=" + authId + ", applicantId=" + applicantId + ", applicant=" + applicant
				+ ", emailId=" + emailId + ", userName=" + userName + ", userStatus=" + userStatus + ", loginAccess="
				+ loginAccess + "]";
	}
	
	
	
}
