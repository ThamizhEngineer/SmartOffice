package com.ss.smartoffice.sonotificationservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;




public class User {
	    private String id;
	    private String employeeId;

	    private String firstName;
	    private String lastName;
	    private String userName;
	    private String password;
	    private String emailId;
	    private String groupId;
	    private String appToken;
	    private String appClientId;
	    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime tokenValidityDt;
	    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime userAccessDt;
	    private String userType;
	    private String createdBy;


	    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime createdDt;
	    private String modifiedBy;
	    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime modifiedDt;
	    
	    private String duration;
	   
	    
	    private String empCode;

	    private String empDesignation;
	    
	    
	    private String latitude,longitude,locationStatus;
	    
	    private String proxyEmpId;
	    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime lastLoggedDt,lastCheckInDt,lastCheckOutDt;
	    
	    private String isCheckedIn  ;
   
	public User() {
			super();
			// TODO Auto-generated constructor stub
		}

	public User(String id, String employeeId, String firstName, String lastName, String userName, String password,
			String emailId, String groupId, String appToken, String appClientId, LocalDateTime tokenValidityDt,
			LocalDateTime userAccessDt, String userType, String createdBy, LocalDateTime createdDt, String modifiedBy,
			LocalDateTime modifiedDt, String duration, String empCode, String empDesignation, String latitude,
			String longitude, String locationStatus, String proxyEmpId, LocalDateTime lastLoggedDt,
			LocalDateTime lastCheckInDt, LocalDateTime lastCheckOutDt, String isCheckedIn) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.emailId = emailId;
		this.groupId = groupId;
		this.appToken = appToken;
		this.appClientId = appClientId;
		this.tokenValidityDt = tokenValidityDt;
		this.userAccessDt = userAccessDt;
		this.userType = userType;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.duration = duration;
		this.empCode = empCode;
		this.empDesignation = empDesignation;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationStatus = locationStatus;
		this.proxyEmpId = proxyEmpId;
		this.lastLoggedDt = lastLoggedDt;
		this.lastCheckInDt = lastCheckInDt;
		this.lastCheckOutDt = lastCheckOutDt;
		this.isCheckedIn = isCheckedIn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getAppClientId() {
		return appClientId;
	}

	public void setAppClientId(String appClientId) {
		this.appClientId = appClientId;
	}

	public LocalDateTime getTokenValidityDt() {
		return tokenValidityDt;
	}

	public void setTokenValidityDt(LocalDateTime tokenValidityDt) {
		this.tokenValidityDt = tokenValidityDt;
	}

	public LocalDateTime getUserAccessDt() {
		return userAccessDt;
	}

	public void setUserAccessDt(LocalDateTime userAccessDt) {
		this.userAccessDt = userAccessDt;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLocationStatus() {
		return locationStatus;
	}

	public void setLocationStatus(String locationStatus) {
		this.locationStatus = locationStatus;
	}

	public String getProxyEmpId() {
		return proxyEmpId;
	}

	public void setProxyEmpId(String proxyEmpId) {
		this.proxyEmpId = proxyEmpId;
	}

	public LocalDateTime getLastLoggedDt() {
		return lastLoggedDt;
	}

	public void setLastLoggedDt(LocalDateTime lastLoggedDt) {
		this.lastLoggedDt = lastLoggedDt;
	}

	public LocalDateTime getLastCheckInDt() {
		return lastCheckInDt;
	}

	public void setLastCheckInDt(LocalDateTime lastCheckInDt) {
		this.lastCheckInDt = lastCheckInDt;
	}

	public LocalDateTime getLastCheckOutDt() {
		return lastCheckOutDt;
	}

	public void setLastCheckOutDt(LocalDateTime lastCheckOutDt) {
		this.lastCheckOutDt = lastCheckOutDt;
	}

	public String getIsCheckedIn() {
		return isCheckedIn;
	}

	public void setIsCheckedIn(String isCheckedIn) {
		this.isCheckedIn = isCheckedIn;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", userName=" + userName + ", password=" + password + ", emailId=" + emailId + ", groupId=" + groupId
				+ ", appToken=" + appToken + ", appClientId=" + appClientId + ", tokenValidityDt=" + tokenValidityDt
				+ ", userAccessDt=" + userAccessDt + ", userType=" + userType + ", createdBy=" + createdBy
				+ ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", duration=" + duration + ", empCode=" + empCode + ", empDesignation=" + empDesignation
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", locationStatus=" + locationStatus
				+ ", proxyEmpId=" + proxyEmpId + ", lastLoggedDt=" + lastLoggedDt + ", lastCheckInDt=" + lastCheckInDt
				+ ", lastCheckOutDt=" + lastCheckOutDt + ", isCheckedIn=" + isCheckedIn + "]";
	}
	
	
	

}
