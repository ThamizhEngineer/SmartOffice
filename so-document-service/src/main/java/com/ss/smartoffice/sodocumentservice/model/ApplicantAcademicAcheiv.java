package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApplicantAcademicAcheiv {


	private Integer id;
	private int applicantId;
	private String organization;
	private String trainDetails;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public ApplicantAcademicAcheiv() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplicantAcademicAcheiv(Integer id, int applicantId, String organization, String trainDetails,
			LocalDate fromDt, LocalDate toDt, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.applicantId = applicantId;
		this.organization = organization;
		this.trainDetails = trainDetails;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "ApplicantAcademicAcheiv [id=" + id + ", applicantId=" + applicantId + ", organization=" + organization
				+ ", trainDetails=" + trainDetails + ", fromDt=" + fromDt + ", toDt=" + toDt + ", isEnabled="
				+ isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(int applicantId) {
		this.applicantId = applicantId;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getTrainDetails() {
		return trainDetails;
	}

	public void setTrainDetails(String trainDetails) {
		this.trainDetails = trainDetails;
	}

	public LocalDate getFromDt() {
		return fromDt;
	}

	public void setFromDt(LocalDate fromDt) {
		this.fromDt = fromDt;
	}

	public LocalDate getToDt() {
		return toDt;
	}

	public void setToDt(LocalDate toDt) {
		this.toDt = toDt;
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
	
}
