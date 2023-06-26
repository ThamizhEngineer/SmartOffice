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
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="auth_user")

public class AuthUserSummary {
	
//TBD -- why is there summary with so many fields in it. need to trim this
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		private String employeeId;
		private String applicantId;
		private String partnerId;
		private String firstName;
		private String lastName;
		private String userName;
		private String password;
		private String emailId;
		private String groupId;
		private String appToken;
		private String mobileNumber;
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
		private String userStatus;
		
		private String latitude;
		private String locationStatus;
		private String longitude;
		private String proxyEmpId;
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime lastLoggedDt;
		
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime lastCheckInDt;
		@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
		private LocalDateTime lastCheckOutDt;

		private String acceptedAgmt;
		private String isCheckedIn;
		public AuthUserSummary() {
			super();
			// TODO Auto-generated constructor stub
		}
		public AuthUserSummary(Integer id, String employeeId, String applicantId, String partnerId, String firstName,
				String lastName, String userName, String password, String emailId, String groupId, String appToken,
				String mobileNumber, String appClientId, LocalDateTime tokenValidityDt, LocalDateTime userAccessDt,
				String userType, String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt,
				String duration, String empCode, String empDesignation, String userStatus, String latitude,
				String locationStatus, String longitude, String proxyEmpId, LocalDateTime lastLoggedDt,
				LocalDateTime lastCheckInDt, LocalDateTime lastCheckOutDt, String acceptedAgmt, String isCheckedIn) {
			super();
			this.id = id;
			this.employeeId = employeeId;
			this.applicantId = applicantId;
			this.partnerId = partnerId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.userName = userName;
			this.password = password;
			this.emailId = emailId;
			this.groupId = groupId;
			this.appToken = appToken;
			this.mobileNumber = mobileNumber;
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
			this.userStatus = userStatus;
			this.latitude = latitude;
			this.locationStatus = locationStatus;
			this.longitude = longitude;
			this.proxyEmpId = proxyEmpId;
			this.lastLoggedDt = lastLoggedDt;
			this.lastCheckInDt = lastCheckInDt;
			this.lastCheckOutDt = lastCheckOutDt;
			this.acceptedAgmt = acceptedAgmt;
			this.isCheckedIn = isCheckedIn;
		}

		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		public String getApplicantId() {
			return applicantId;
		}
		public void setApplicantId(String applicantId) {
			this.applicantId = applicantId;
		}
		public String getPartnerId() {
			return partnerId;
		}
		public void setPartnerId(String partnerId) {
			this.partnerId = partnerId;
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
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
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
		public String getUserStatus() {
			return userStatus;
		}
		public void setUserStatus(String userStatus) {
			this.userStatus = userStatus;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLocationStatus() {
			return locationStatus;
		}
		public void setLocationStatus(String locationStatus) {
			this.locationStatus = locationStatus;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
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
		public String getAcceptedAgmt() {
			return acceptedAgmt;
		}
		public void setAcceptedAgmt(String acceptedAgmt) {
			this.acceptedAgmt = acceptedAgmt;
		}
		public String getIsCheckedIn() {
			return isCheckedIn;
		}
		public void setIsCheckedIn(String isCheckedIn) {
			this.isCheckedIn = isCheckedIn;
		}
		@Override
		public String toString() {
			return "AuthUserSummary [id=" + id + ", employeeId=" + employeeId + ", applicantId=" + applicantId
					+ ", partnerId=" + partnerId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
					+ userName + ", password=" + password + ", emailId=" + emailId + ", groupId=" + groupId
					+ ", appToken=" + appToken + ", mobileNumber=" + mobileNumber + ", appClientId=" + appClientId
					+ ", tokenValidityDt=" + tokenValidityDt + ", userAccessDt=" + userAccessDt + ", userType="
					+ userType + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy
					+ ", modifiedDt=" + modifiedDt + ", duration=" + duration + ", empCode=" + empCode
					+ ", empDesignation=" + empDesignation + ", userStatus=" + userStatus + ", latitude=" + latitude
					+ ", locationStatus=" + locationStatus + ", longitude=" + longitude + ", proxyEmpId=" + proxyEmpId
					+ ", lastLoggedDt=" + lastLoggedDt + ", lastCheckInDt=" + lastCheckInDt + ", lastCheckOutDt="
					+ lastCheckOutDt + ", acceptedAgmt=" + acceptedAgmt + ", isCheckedIn=" + isCheckedIn + "]";
		}
		
		

		
}
