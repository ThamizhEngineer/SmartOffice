package com.ss.smartoffice.shared.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.ss.smartoffice.shared.common.BaseEntity;
@Entity
@Table(name="t_emp_location")
public class Location extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	private String appToken;
	private String appClientId;
	private String employeeId;
	private String employeeCode;
	private String employeeFirstName;
	private String employeeLastName;
	private String dateTime;
	private String city;
	private String state;
	private String country;
	private String latitude;
	private String longitude;
	private String locationStatus;
	private String remarks;
	
	private String proxyEmployeeId;
	private String proxyEmployeeCode;
	private String proxyEmployeeFirstName;
	private String proxyEmployeeLastName;
	private String processingStatus;
	private String processingRemarks;
	private String attendanceStatus;
	private String logType;
	
	public Location() {
		super();
		// TODO Auto-generated constructor
	}
	@Override
	public String toString() {
		return "Location [id=" + id + ", appToken=" + appToken + ", appClientId=" + appClientId + ", employeeId="
				+ employeeId + ", employeeCode=" + employeeCode + ", employeeFirstName=" + employeeFirstName
				+ ", employeeLastName=" + employeeLastName + ", dateTime=" + dateTime + ", city=" + city + ", state="
				+ state + ", country=" + country + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", locationStatus=" + locationStatus + ", remarks=" + remarks + ", proxyEmployeeId=" + proxyEmployeeId
				+ ", proxyEmployeeCode=" + proxyEmployeeCode + ", proxyEmployeeFirstName=" + proxyEmployeeFirstName
				+ ", proxyEmployeeLastName=" + proxyEmployeeLastName + ", processingStatus=" + processingStatus
				+ ", processingRemarks=" + processingRemarks + ", attendanceStatus=" + attendanceStatus + ", logType="
				+ logType + "]";
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
	public String getAppClientId() {
		return appClientId;
	}
	public void setAppClientId(String appClientId) {
		this.appClientId = appClientId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeFirstName() {
		return employeeFirstName;
	}
	public void setEmployeeFirstName(String employeeFirstName) {
		this.employeeFirstName = employeeFirstName;
	}
	public String getEmployeeLastName() {
		return employeeLastName;
	}
	public void setEmployeeLastName(String employeeLastName) {
		this.employeeLastName = employeeLastName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getProxyEmployeeId() {
		return proxyEmployeeId;
	}
	public void setProxyEmployeeId(String proxyEmployeeId) {
		this.proxyEmployeeId = proxyEmployeeId;
	}
	public String getProxyEmployeeCode() {
		return proxyEmployeeCode;
	}
	public void setProxyEmployeeCode(String proxyEmployeeCode) {
		this.proxyEmployeeCode = proxyEmployeeCode;
	}
	public String getProxyEmployeeFirstName() {
		return proxyEmployeeFirstName;
	}
	public void setProxyEmployeeFirstName(String proxyEmployeeFirstName) {
		this.proxyEmployeeFirstName = proxyEmployeeFirstName;
	}
	public String getProxyEmployeeLastName() {
		return proxyEmployeeLastName;
	}
	public void setProxyEmployeeLastName(String proxyEmployeeLastName) {
		this.proxyEmployeeLastName = proxyEmployeeLastName;
	}
	public String getProcessingStatus() {
		return processingStatus;
	}
	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}
	public String getProcessingRemarks() {
		return processingRemarks;
	}
	public void setProcessingRemarks(String processingRemarks) {
		this.processingRemarks = processingRemarks;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public Location(Integer id, String appToken, String appClientId, String employeeId, String employeeCode,
			String employeeFirstName, String employeeLastName, String dateTime, String city, String state,
			String country, String latitude, String longitude, String locationStatus, String remarks,
			String proxyEmployeeId, String proxyEmployeeCode, String proxyEmployeeFirstName,
			String proxyEmployeeLastName, String processingStatus, String processingRemarks, String attendanceStatus,
			String logType) {
		super();
		this.id = id;
		this.appToken = appToken;
		this.appClientId = appClientId;
		this.employeeId = employeeId;
		this.employeeCode = employeeCode;
		this.employeeFirstName = employeeFirstName;
		this.employeeLastName = employeeLastName;
		this.dateTime = dateTime;
		this.city = city;
		this.state = state;
		this.country = country;
		this.latitude = latitude;
		this.longitude = longitude;
		this.locationStatus = locationStatus;
		this.remarks = remarks;
		this.proxyEmployeeId = proxyEmployeeId;
		this.proxyEmployeeCode = proxyEmployeeCode;
		this.proxyEmployeeFirstName = proxyEmployeeFirstName;
		this.proxyEmployeeLastName = proxyEmployeeLastName;
		this.processingStatus = processingStatus;
		this.processingRemarks = processingRemarks;
		this.attendanceStatus = attendanceStatus;
		this.logType = logType;
	}
	
	
	
}
