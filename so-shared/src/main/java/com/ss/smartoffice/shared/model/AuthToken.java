package com.ss.smartoffice.shared.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;




@Entity
@Table(name = "auth_token",indexes = @Index(name = "idx_auth_token_userId_code",columnList = "app_token,auth_user_id,application_code"))

public class AuthToken {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "app_token")
	private String appToken;
	private String employeeId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dateTime;
	@Column(name = "application_code")
	private String applicationCode;
	@Column(name = "auth_user_id")
	private Integer authUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime tokenValidityDt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastLoggedDt;
	private String duration;


	public AuthToken() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "AuthToken [id=" + id + ", appToken=" + appToken + ", employeeId=" + employeeId + ", dateTime="
				+ dateTime + ", applicationCode=" + applicationCode + ", authUserId=" + authUserId
				+ ", tokenValidityDt=" + tokenValidityDt + ", lastLoggedDt=" + lastLoggedDt + ", duration=" + duration
				+ "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getAppToken() {
		return appToken;
	}


	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}


	public String getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}


	public LocalDateTime getDateTime() {
		return dateTime;
	}


	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}


	public String getApplicationCode() {
		return applicationCode;
	}


	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}


	public Integer getAuthUserId() {
		return authUserId;
	}


	public void setAuthUserId(Integer authUserId) {
		this.authUserId = authUserId;
	}


	public LocalDateTime getTokenValidityDt() {
		return tokenValidityDt;
	}


	public void setTokenValidityDt(LocalDateTime tokenValidityDt) {
		this.tokenValidityDt = tokenValidityDt;
	}


	public LocalDateTime getLastLoggedDt() {
		return lastLoggedDt;
	}


	public void setLastLoggedDt(LocalDateTime lastLoggedDt) {
		this.lastLoggedDt = lastLoggedDt;
	}


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public AuthToken(Integer id, String appToken, String employeeId, LocalDateTime dateTime, String applicationCode,
			Integer authUserId, LocalDateTime tokenValidityDt, LocalDateTime lastLoggedDt, String duration) {
		super();
		this.id = id;
		this.appToken = appToken;
		this.employeeId = employeeId;
		this.dateTime = dateTime;
		this.applicationCode = applicationCode;
		this.authUserId = authUserId;
		this.tokenValidityDt = tokenValidityDt;
		this.lastLoggedDt = lastLoggedDt;
		this.duration = duration;
	}

	

}
