package com.ss.smartoffice.soauthservice.model.auth;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;



public class JobSummary extends BaseEntity{

	private int id;
	private String jobCode;
	private String jobLocation;
	private String jobType;
	private String jobStatus;
	private String siteName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDate,endDate;
	private int userId;
	
	public JobSummary() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobSummary(int id, String jobCode, String jobLocation, String jobType, String jobStatus, String siteName,
			LocalDateTime startDate, LocalDateTime endDate, int userId) {
		super();
		this.id = id;
		this.jobCode = jobCode;
		this.jobLocation = jobLocation;
		this.jobType = jobType;
		this.jobStatus = jobStatus;
		this.siteName = siteName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getJobLocation() {
		return jobLocation;
	}

	public void setJobLocation(String jobLocation) {
		this.jobLocation = jobLocation;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "JobSummary [id=" + id + ", jobCode=" + jobCode + ", jobLocation=" + jobLocation + ", jobType=" + jobType
				+ ", jobStatus=" + jobStatus + ", siteName=" + siteName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", userId=" + userId + "]";
	}

	
	
	

	
	
	
	
}
