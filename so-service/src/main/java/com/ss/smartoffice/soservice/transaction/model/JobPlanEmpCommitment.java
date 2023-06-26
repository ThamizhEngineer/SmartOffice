package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name=("t_job_plan_emp_commitment"))

@Scope("prototype")
public class JobPlanEmpCommitment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_plan_emp_id")
	private String jobPlanEmpId;
	private String commitmentType;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	private String leaveName;
	private String holidayName;
	private String lats;
	private String longs;
	private String distanceToLocation;

	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public JobPlanEmpCommitment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPlanEmpCommitment(Integer id, String jobPlanEmpId, String commitmentType, LocalDate fromDt,
			LocalDate toDt, String leaveName, String holidayName, String lats, String longs, String distanceToLocation,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobPlanEmpId = jobPlanEmpId;
		this.commitmentType = commitmentType;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.leaveName = leaveName;
		this.holidayName = holidayName;
		this.lats = lats;
		this.longs = longs;
		this.distanceToLocation = distanceToLocation;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "JobPlanEmpCommitment [id=" + id + ", jobPlanEmpId=" + jobPlanEmpId + ", commitmentType="
				+ commitmentType + ", fromDt=" + fromDt + ", toDt=" + toDt + ", leaveName=" + leaveName
				+ ", holidayName=" + holidayName + ", lats=" + lats + ", longs=" + longs + ", distanceToLocation="
				+ distanceToLocation + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobPlanEmpId() {
		return jobPlanEmpId;
	}

	public void setJobPlanEmpId(String jobPlanEmpId) {
		this.jobPlanEmpId = jobPlanEmpId;
	}

	public String getCommitmentType() {
		return commitmentType;
	}

	public void setCommitmentType(String commitmentType) {
		this.commitmentType = commitmentType;
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

	public String getLeaveName() {
		return leaveName;
	}

	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}

	public String getHolidayName() {
		return holidayName;
	}

	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}

	public String getLats() {
		return lats;
	}

	public void setLats(String lats) {
		this.lats = lats;
	}

	public String getLongs() {
		return longs;
	}

	public void setLongs(String longs) {
		this.longs = longs;
	}

	public String getDistanceToLocation() {
		return distanceToLocation;
	}

	public void setDistanceToLocation(String distanceToLocation) {
		this.distanceToLocation = distanceToLocation;
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
