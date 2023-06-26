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
import com.ss.smartoffice.shared.common.BaseEntity;

import lombok.Data;
@Entity
@Table(name="t_job_emp")

@Scope("prototype")
public class JobEmployee extends BaseEntity{
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private String tJobId;
	private int employeeId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate expStartDt ;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate  expEndDt ;
	private Integer tJobProfileId;
	@Formula("(select profile.profile_name from m_profile profile where profile.id= t_job_profile_id )")
	private String profileName;
	private String hasReported;
	private String hasDeparted;
	private String isJobTravelComplete;
	private String isJobAccComplete;
	private String reportedRemarks;
	private String departedRemarks;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime reportedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime departedDt;
	private Integer clientFeedbackScore;
	private String clientFeedbackRemarks;
	
	@Formula("(select job.job_code from t_job job where job.id=t_job_id)")
	private String jobCode;
	@Formula("(select job.m_job_type_id from t_job job where job.id=t_job_id)")
	private String mJobTypeId;
	
	@Formula("(select job.partner_id from t_job job where job.id=t_job_id)")
	private String partnerId;
	@Formula("(select job.end_client_id from t_job job where job.id=t_job_id)")
	private String endClientId;
	
	@Formula("(select partner.client_name from m_partner partner left JOIN t_job job on job.partner_id = partner.id where job.id=t_job_id)")
	private String clientName;
	@Formula("(select jobType.job_type_name from m_job_type jobType left JOIN t_job job on job.m_job_type_id = jobType.id where job.id=t_job_id)")
	private String jobTypeName;
	
	@Formula("(select job.map_location_id from t_job job where job.id=t_job_id)")
	private String mapLocationId;
	@Formula("(select job.job_name from t_job job where job.id=t_job_id)")
	private String jobName;
	
	@Formula("(select partner.client_name from m_partner partner left JOIN t_job job on job.end_client_id = partner.id where job.id=t_job_id)")
	private String endClientName;
	
	@Formula("(select profile.profile_name from m_profile profile where profile.id=t_job_profile_id)")
	private String jobProfileName;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=employee_id )")
	private String employeeName;
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
	@JoinColumn(name="t_job_emp_id")
	private List<JobEmpCompMatrix> jobEmpCompMatrixs;

	public JobEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public JobEmployee(Integer id, String tJobId, String jobCode, String mJobTypeId, String partnerId, String endClientId,
			String clientName, String jobTypeName, String mapLocationId, String jobName, String endClientName) {
		super();
		this.id = id;
		this.tJobId = tJobId;
		this.jobCode = jobCode;
		this.mJobTypeId = mJobTypeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.clientName = clientName;
		this.jobTypeName = jobTypeName;
		this.mapLocationId = mapLocationId;
		this.jobName = jobName;
		this.endClientName = endClientName;
	}


	public JobEmployee(Integer id, String tJobId, int employeeId, LocalDate expStartDt, LocalDate expEndDt,
			Integer tJobProfileId, String hasReported, String hasDeparted, String isJobTravelComplete,
			String isJobAccComplete, String reportedRemarks, String departedRemarks, LocalDateTime reportedDt,
			LocalDateTime departedDt, Integer clientFeedbackScore, String clientFeedbackRemarks, String jobCode,
			String mJobTypeId, String partnerId, String endClientId, String clientName, String jobTypeName,
			String mapLocationId, String jobName, String endClientName, String jobProfileName, String employeeName,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			List<JobEmpCompMatrix> jobEmpCompMatrixs) {
		super();
		this.id = id;
		this.tJobId = tJobId;
		this.employeeId = employeeId;
		this.expStartDt = expStartDt;
		this.expEndDt = expEndDt;
		this.tJobProfileId = tJobProfileId;
		this.hasReported = hasReported;
		this.hasDeparted = hasDeparted;
		this.isJobTravelComplete = isJobTravelComplete;
		this.isJobAccComplete = isJobAccComplete;
		this.reportedRemarks = reportedRemarks;
		this.departedRemarks = departedRemarks;
		this.reportedDt = reportedDt;
		this.departedDt = departedDt;
		this.clientFeedbackScore = clientFeedbackScore;
		this.clientFeedbackRemarks = clientFeedbackRemarks;
		this.jobCode = jobCode;
		this.mJobTypeId = mJobTypeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.clientName = clientName;
		this.jobTypeName = jobTypeName;
		this.mapLocationId = mapLocationId;
		this.jobName = jobName;
		this.endClientName = endClientName;
		this.jobProfileName = jobProfileName;
		this.employeeName = employeeName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.jobEmpCompMatrixs = jobEmpCompMatrixs;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String gettJobId() {
		return tJobId;
	}


	public void settJobId(String tJobId) {
		this.tJobId = tJobId;
	}


	public int getEmployeeId() {
		return employeeId;
	}


	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}


	public LocalDate getExpStartDt() {
		return expStartDt;
	}


	public void setExpStartDt(LocalDate expStartDt) {
		this.expStartDt = expStartDt;
	}


	public LocalDate getExpEndDt() {
		return expEndDt;
	}


	public void setExpEndDt(LocalDate expEndDt) {
		this.expEndDt = expEndDt;
	}


	public Integer gettJobProfileId() {
		return tJobProfileId;
	}


	public void settJobProfileId(Integer tJobProfileId) {
		this.tJobProfileId = tJobProfileId;
	}


	public String getHasReported() {
		return hasReported;
	}


	public void setHasReported(String hasReported) {
		this.hasReported = hasReported;
	}


	public String getHasDeparted() {
		return hasDeparted;
	}


	public void setHasDeparted(String hasDeparted) {
		this.hasDeparted = hasDeparted;
	}


	public String getIsJobTravelComplete() {
		return isJobTravelComplete;
	}


	public void setIsJobTravelComplete(String isJobTravelComplete) {
		this.isJobTravelComplete = isJobTravelComplete;
	}


	public String getIsJobAccComplete() {
		return isJobAccComplete;
	}


	public void setIsJobAccComplete(String isJobAccComplete) {
		this.isJobAccComplete = isJobAccComplete;
	}


	public String getReportedRemarks() {
		return reportedRemarks;
	}


	public void setReportedRemarks(String reportedRemarks) {
		this.reportedRemarks = reportedRemarks;
	}


	public String getDepartedRemarks() {
		return departedRemarks;
	}


	public void setDepartedRemarks(String departedRemarks) {
		this.departedRemarks = departedRemarks;
	}


	public LocalDateTime getReportedDt() {
		return reportedDt;
	}


	public void setReportedDt(LocalDateTime reportedDt) {
		this.reportedDt = reportedDt;
	}


	public LocalDateTime getDepartedDt() {
		return departedDt;
	}


	public void setDepartedDt(LocalDateTime departedDt) {
		this.departedDt = departedDt;
	}


	public Integer getClientFeedbackScore() {
		return clientFeedbackScore;
	}


	public void setClientFeedbackScore(Integer clientFeedbackScore) {
		this.clientFeedbackScore = clientFeedbackScore;
	}


	public String getClientFeedbackRemarks() {
		return clientFeedbackRemarks;
	}


	public void setClientFeedbackRemarks(String clientFeedbackRemarks) {
		this.clientFeedbackRemarks = clientFeedbackRemarks;
	}


	public String getJobCode() {
		return jobCode;
	}


	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}


	public String getmJobTypeId() {
		return mJobTypeId;
	}


	public void setmJobTypeId(String mJobTypeId) {
		this.mJobTypeId = mJobTypeId;
	}


	public String getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}


	public String getEndClientId() {
		return endClientId;
	}


	public void setEndClientId(String endClientId) {
		this.endClientId = endClientId;
	}


	public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getJobTypeName() {
		return jobTypeName;
	}


	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}


	public String getMapLocationId() {
		return mapLocationId;
	}


	public void setMapLocationId(String mapLocationId) {
		this.mapLocationId = mapLocationId;
	}


	public String getJobName() {
		return jobName;
	}


	public void setJobName(String jobName) {
		this.jobName = jobName;
	}


	public String getEndClientName() {
		return endClientName;
	}


	public void setEndClientName(String endClientName) {
		this.endClientName = endClientName;
	}


	public String getJobProfileName() {
		return jobProfileName;
	}


	public void setJobProfileName(String jobProfileName) {
		this.jobProfileName = jobProfileName;
	}


	public String getEmployeeName() {
		return employeeName;
	}


	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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


	public List<JobEmpCompMatrix> getJobEmpCompMatrixs() {
		return jobEmpCompMatrixs;
	}


	public void setJobEmpCompMatrixs(List<JobEmpCompMatrix> jobEmpCompMatrixs) {
		this.jobEmpCompMatrixs = jobEmpCompMatrixs;
	}


	@Override
	public String toString() {
		return "JobEmployee [id=" + id + ", tJobId=" + tJobId + ", employeeId=" + employeeId + ", expStartDt="
				+ expStartDt + ", expEndDt=" + expEndDt + ", tJobProfileId=" + tJobProfileId + ", hasReported="
				+ hasReported + ", hasDeparted=" + hasDeparted + ", isJobTravelComplete=" + isJobTravelComplete
				+ ", isJobAccComplete=" + isJobAccComplete + ", reportedRemarks=" + reportedRemarks
				+ ", departedRemarks=" + departedRemarks + ", reportedDt=" + reportedDt + ", departedDt=" + departedDt
				+ ", clientFeedbackScore=" + clientFeedbackScore + ", clientFeedbackRemarks=" + clientFeedbackRemarks
				+ ", jobCode=" + jobCode + ", mJobTypeId=" + mJobTypeId + ", partnerId=" + partnerId + ", endClientId="
				+ endClientId + ", clientName=" + clientName + ", jobTypeName=" + jobTypeName + ", mapLocationId="
				+ mapLocationId + ", jobName=" + jobName + ", endClientName=" + endClientName + ", jobProfileName="
				+ jobProfileName + ", employeeName=" + employeeName + ", isEnabled=" + isEnabled + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt
				+ ", jobEmpCompMatrixs=" + jobEmpCompMatrixs + "]";
	}


	

	

}
