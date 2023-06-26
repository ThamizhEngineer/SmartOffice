package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_job_plan_emp")

@Scope("prototype")
public class JobPlanEmp {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private Integer jobId;
	private String profileId;
	@Formula("(select profile.profile_name from m_profile profile where profile.id=profile_id)")
	private String profileName;
	private String employeeId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=employee_id)")
	private String employeeName;
	private String comptabilityScore;
	private String distanceToLocation;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate startDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate endDt;
	@Formula("(select job.job_name from t_job job where job.id=t_job_id)")
	private String jobName;
	@Formula("(select job.job_code from t_job job where job.id=t_job_id)")
	private String jobCode;	
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)	
	@JoinColumn(name="t_job_plan_emp_id")
	private List<JobPlanEmpSkill> jobPlanEmpSkills;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)	
	@JoinColumn(name="t_job_plan_emp_id")
	private List<JobPlanEmpCommitment> jobPlanEmpCommitments;

	public JobPlanEmp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobPlanEmp(Integer id, Integer jobId, String profileId, String profileName, String employeeId,
			String employeeName, String comptabilityScore, String distanceToLocation, LocalDate startDt,
			LocalDate endDt, String jobName, String jobCode, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt, List<JobPlanEmpSkill> jobPlanEmpSkills,
			List<JobPlanEmpCommitment> jobPlanEmpCommitments) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.profileId = profileId;
		this.profileName = profileName;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.comptabilityScore = comptabilityScore;
		this.distanceToLocation = distanceToLocation;
		this.startDt = startDt;
		this.endDt = endDt;
		this.jobName = jobName;
		this.jobCode = jobCode;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.jobPlanEmpSkills = jobPlanEmpSkills;
		this.jobPlanEmpCommitments = jobPlanEmpCommitments;
	}

	@Override
	public String toString() {
		return "JobPlanEmp [id=" + id + ", jobId=" + jobId + ", profileId=" + profileId + ", profileName=" + profileName
				+ ", employeeId=" + employeeId + ", employeeName=" + employeeName + ", comptabilityScore="
				+ comptabilityScore + ", distanceToLocation=" + distanceToLocation + ", startDt=" + startDt + ", endDt="
				+ endDt + ", jobName=" + jobName + ", jobCode=" + jobCode + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", jobPlanEmpSkills=" + jobPlanEmpSkills + ", jobPlanEmpCommitments=" + jobPlanEmpCommitments + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getComptabilityScore() {
		return comptabilityScore;
	}

	public void setComptabilityScore(String comptabilityScore) {
		this.comptabilityScore = comptabilityScore;
	}

	public String getDistanceToLocation() {
		return distanceToLocation;
	}

	public void setDistanceToLocation(String distanceToLocation) {
		this.distanceToLocation = distanceToLocation;
	}

	public LocalDate getStartDt() {
		return startDt;
	}

	public void setStartDt(LocalDate startDt) {
		this.startDt = startDt;
	}

	public LocalDate getEndDt() {
		return endDt;
	}

	public void setEndDt(LocalDate endDt) {
		this.endDt = endDt;
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

	public List<JobPlanEmpSkill> getJobPlanEmpSkills() {
		return jobPlanEmpSkills;
	}

	public void setJobPlanEmpSkills(List<JobPlanEmpSkill> jobPlanEmpSkills) {
		this.jobPlanEmpSkills = jobPlanEmpSkills;
	}

	public List<JobPlanEmpCommitment> getJobPlanEmpCommitments() {
		return jobPlanEmpCommitments;
	}

	public void setJobPlanEmpCommitments(List<JobPlanEmpCommitment> jobPlanEmpCommitments) {
		this.jobPlanEmpCommitments = jobPlanEmpCommitments;
	}


	
	
	
	

	
}
