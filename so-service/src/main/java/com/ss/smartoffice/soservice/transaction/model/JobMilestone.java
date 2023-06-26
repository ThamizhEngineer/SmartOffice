package com.ss.smartoffice.soservice.transaction.model;

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
@Table(name="t_job_milestone")

@Scope("prototype")
public class JobMilestone {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private Integer jobId;
	
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
	private String milestoneName;
	private String milestoneOrder;
	private String milestoneDesc;
	private String milestoneStatus;
	private Integer progress;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	@OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL,orphanRemoval=true)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_milestone_id")
	private List<JobTaskList> jobTaskList;
	
	public JobMilestone() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobMilestone(Integer id, Integer jobId, String jobCode, String mJobTypeId, String partnerId, String endClientId,
			String clientName, String jobTypeName, String mapLocationId, String jobName, String endClientName) {
		super();
		this.id = id;
		this.jobId = jobId;
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

	public JobMilestone(Integer id, Integer jobId, String jobCode, String mJobTypeId, String partnerId,
			String endClientId, String clientName, String jobTypeName, String mapLocationId, String jobName,
			String endClientName, String milestoneName, String milestoneOrder, String milestoneDesc,
			String milestoneStatus, Integer progress, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt, List<JobTaskList> jobTaskList) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.jobCode = jobCode;
		this.mJobTypeId = mJobTypeId;
		this.partnerId = partnerId;
		this.endClientId = endClientId;
		this.clientName = clientName;
		this.jobTypeName = jobTypeName;
		this.mapLocationId = mapLocationId;
		this.jobName = jobName;
		this.endClientName = endClientName;
		this.milestoneName = milestoneName;
		this.milestoneOrder = milestoneOrder;
		this.milestoneDesc = milestoneDesc;
		this.milestoneStatus = milestoneStatus;
		this.progress = progress;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.jobTaskList = jobTaskList;
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

	public String getMilestoneName() {
		return milestoneName;
	}

	public void setMilestoneName(String milestoneName) {
		this.milestoneName = milestoneName;
	}

	public String getMilestoneOrder() {
		return milestoneOrder;
	}

	public void setMilestoneOrder(String milestoneOrder) {
		this.milestoneOrder = milestoneOrder;
	}

	public String getMilestoneDesc() {
		return milestoneDesc;
	}

	public void setMilestoneDesc(String milestoneDesc) {
		this.milestoneDesc = milestoneDesc;
	}

	public String getMilestoneStatus() {
		return milestoneStatus;
	}

	public void setMilestoneStatus(String milestoneStatus) {
		this.milestoneStatus = milestoneStatus;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
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

	public List<JobTaskList> getJobTaskList() {
		return jobTaskList;
	}

	public void setJobTaskList(List<JobTaskList> jobTaskList) {
		this.jobTaskList = jobTaskList;
	}

	@Override
	public String toString() {
		return "JobMilestone [id=" + id + ", jobId=" + jobId + ", jobCode=" + jobCode + ", mJobTypeId=" + mJobTypeId
				+ ", partnerId=" + partnerId + ", endClientId=" + endClientId + ", clientName=" + clientName
				+ ", jobTypeName=" + jobTypeName + ", mapLocationId=" + mapLocationId + ", jobName=" + jobName
				+ ", endClientName=" + endClientName + ", milestoneName=" + milestoneName + ", milestoneOrder="
				+ milestoneOrder + ", milestoneDesc=" + milestoneDesc + ", milestoneStatus=" + milestoneStatus
				+ ", progress=" + progress + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", jobTaskList="
				+ jobTaskList + "]";
	}

	
	
	
	

}
