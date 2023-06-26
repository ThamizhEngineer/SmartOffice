package com.ss.smartoffice.soservice.master.JobTypeService;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="m_job_type")

@Scope("prototype")
public class JobType {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String jobTypeCode;
	private String jobTypeName;
	private String jobDesc;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="job_type_id")
	private List<JobTypeProfile>jobTypeProfile;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="job_type_id")
	private List<JobTypeMaterial>jobTypeMaterials;
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="job_type_id")
	private List<JobTypeTaskType>jobTypeTaskTypes;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@Formula("(select COUNT(*) from m_job_type_task_type jobTypeTaskType where jobTypeTaskType.job_type_id=id)")
	private String jobTypeTaskTypeCount;
	@Formula("(select COUNT(*) from m_job_type_material jobMaterial where jobMaterial.job_type_id=id)")
	private String jobTypeMaterialCount;
	@Formula("(select COUNT(*) from m_job_type_profile jobProfiles where jobProfiles.job_type_id=id)")
	private String jobTypeProfileCount;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public JobType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JobType(Integer id, String jobTypeCode, String jobTypeName, String jobTypeTaskTypeCount,
            String jobTypeMaterialCount, String jobTypeProfileCount) {
        super();
        this.id = id;
        this.jobTypeCode = jobTypeCode;
        this.jobTypeName = jobTypeName;
        this.jobTypeTaskTypeCount = jobTypeTaskTypeCount;
        this.jobTypeMaterialCount = jobTypeMaterialCount;
        this.jobTypeProfileCount = jobTypeProfileCount;
    }
	public JobType(Integer id, String jobTypeCode, String jobTypeName, String jobDesc,
			List<JobTypeProfile> jobTypeProfile, List<JobTypeMaterial> jobTypeMaterials,
			List<JobTypeTaskType> jobTypeTaskTypes, String isEnabled, String createdBy, String modifiedBy,
			String jobTypeTaskTypeCount, String jobTypeMaterialCount, String jobTypeProfileCount,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobTypeCode = jobTypeCode;
		this.jobTypeName = jobTypeName;
		this.jobDesc = jobDesc;
		this.jobTypeProfile = jobTypeProfile;
		this.jobTypeMaterials = jobTypeMaterials;
		this.jobTypeTaskTypes = jobTypeTaskTypes;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.jobTypeTaskTypeCount = jobTypeTaskTypeCount;
		this.jobTypeMaterialCount = jobTypeMaterialCount;
		this.jobTypeProfileCount = jobTypeProfileCount;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getJobTypeCode() {
		return jobTypeCode;
	}
	public void setJobTypeCode(String jobTypeCode) {
		this.jobTypeCode = jobTypeCode;
	}
	public String getJobTypeName() {
		return jobTypeName;
	}
	public void setJobTypeName(String jobTypeName) {
		this.jobTypeName = jobTypeName;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public List<JobTypeProfile> getJobTypeProfile() {
		return jobTypeProfile;
	}
	public void setJobTypeProfile(List<JobTypeProfile> jobTypeProfile) {
		this.jobTypeProfile = jobTypeProfile;
	}
	public List<JobTypeMaterial> getJobTypeMaterials() {
		return jobTypeMaterials;
	}
	public void setJobTypeMaterials(List<JobTypeMaterial> jobTypeMaterials) {
		this.jobTypeMaterials = jobTypeMaterials;
	}
	public List<JobTypeTaskType> getJobTypeTaskTypes() {
		return jobTypeTaskTypes;
	}
	public void setJobTypeTaskTypes(List<JobTypeTaskType> jobTypeTaskTypes) {
		this.jobTypeTaskTypes = jobTypeTaskTypes;
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
	public String getJobTypeTaskTypeCount() {
		return jobTypeTaskTypeCount;
	}
	public void setJobTypeTaskTypeCount(String jobTypeTaskTypeCount) {
		this.jobTypeTaskTypeCount = jobTypeTaskTypeCount;
	}
	public String getJobTypeMaterialCount() {
		return jobTypeMaterialCount;
	}
	public void setJobTypeMaterialCount(String jobTypeMaterialCount) {
		this.jobTypeMaterialCount = jobTypeMaterialCount;
	}
	public String getJobTypeProfileCount() {
		return jobTypeProfileCount;
	}
	public void setJobTypeProfileCount(String jobTypeProfileCount) {
		this.jobTypeProfileCount = jobTypeProfileCount;
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
	@Override
	public String toString() {
		return "JobType [id=" + id + ", jobTypeCode=" + jobTypeCode + ", jobTypeName=" + jobTypeName + ", jobDesc="
				+ jobDesc + ", jobTypeProfile=" + jobTypeProfile + ", jobTypeMaterials=" + jobTypeMaterials
				+ ", jobTypeTaskTypes=" + jobTypeTaskTypes + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", jobTypeTaskTypeCount=" + jobTypeTaskTypeCount
				+ ", jobTypeMaterialCount=" + jobTypeMaterialCount + ", jobTypeProfileCount=" + jobTypeProfileCount
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	
}
