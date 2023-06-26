package com.ss.smartoffice.shared.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuthClient {
	
	private Integer authId;
	private Integer partnerId;
	private Integer partnerEmpId;
	private String partnerEmpName;
	private String client;
	private String emailId;
	private String userName;
	private String userStatus;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime loginAccess;
	public AuthClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthClient(Integer authId, Integer partnerId, Integer partnerEmpId, String partnerEmpName, String client,
			String emailId, String userName, String userStatus, LocalDateTime loginAccess) {
		super();
		this.authId = authId;
		this.partnerId = partnerId;
		this.partnerEmpId = partnerEmpId;
		this.partnerEmpName = partnerEmpName;
		this.client = client;
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

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getPartnerEmpId() {
		return partnerEmpId;
	}

	public void setPartnerEmpId(Integer partnerEmpId) {
		this.partnerEmpId = partnerEmpId;
	}

	public String getPartnerEmpName() {
		return partnerEmpName;
	}

	public void setPartnerEmpName(String partnerEmpName) {
		this.partnerEmpName = partnerEmpName;
	}

	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
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
		return "AuthClient [authId=" + authId + ", partnerId=" + partnerId + ", partnerEmpId=" + partnerEmpId
				+ ", partnerEmpName=" + partnerEmpName + ", client=" + client + ", emailId=" + emailId + ", userName="
				+ userName + ", userStatus=" + userStatus + ", loginAccess=" + loginAccess + "]";
	}
	
}
