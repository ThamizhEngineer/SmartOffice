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
@Table(name="t_job_assets")

@Scope("prototype")
public class JobAsset {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private String jobId;
	private String assetId;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate fromDt;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate toDt;
	private String comments;
	private String mEmployeeId;
	@Formula("(select emp.emp_name from m_employee emp where emp.id=m_employee_id)")
	private String employeeName;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@Formula("(select id  from m_material asset where asset.id=id)")
	private String jobMasterAssetId;
	
	@Formula("(select asset.asset_owner from m_material asset where asset.id=asset_id)")
	private String assetOwner;
	
	@Formula("(select asset.material_name from m_material asset where asset.id=asset_id)")
	private String assetName;
	
	
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	@OneToMany(fetch = FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "t_job_assets_id")
	private List<JobAssetDocs> jobAssetDocs;

	public JobAsset() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "JobAsset [id=" + id + ", jobId=" + jobId + ", assetId=" + assetId + ", fromDt=" + fromDt + ", toDt="
				+ toDt + ", comments=" + comments + ", mEmployeeId=" + mEmployeeId + ", employeeName=" + employeeName
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", jobMasterAssetId=" + jobMasterAssetId + ", assetOwner=" + assetOwner + ", assetName=" + assetName
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", jobAssetDocs=" + jobAssetDocs + "]";
	}

	public JobAsset(Integer id, String jobId, String assetId, LocalDate fromDt, LocalDate toDt, String comments,
			String mEmployeeId, String employeeName, String isEnabled, String createdBy, String modifiedBy,
			String jobMasterAssetId, String assetOwner, String assetName, LocalDateTime createdDt,
			LocalDateTime modifiedDt, List<JobAssetDocs> jobAssetDocs) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.assetId = assetId;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.comments = comments;
		this.mEmployeeId = mEmployeeId;
		this.employeeName = employeeName;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.jobMasterAssetId = jobMasterAssetId;
		this.assetOwner = assetOwner;
		this.assetName = assetName;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.jobAssetDocs = jobAssetDocs;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getmEmployeeId() {
		return mEmployeeId;
	}

	public void setmEmployeeId(String mEmployeeId) {
		this.mEmployeeId = mEmployeeId;
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

	public String getJobMasterAssetId() {
		return jobMasterAssetId;
	}

	public void setJobMasterAssetId(String jobMasterAssetId) {
		this.jobMasterAssetId = jobMasterAssetId;
	}

	public String getAssetOwner() {
		return assetOwner;
	}

	public void setAssetOwner(String assetOwner) {
		this.assetOwner = assetOwner;
	}

	public String getAssetName() {
		return assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
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

	public List<JobAssetDocs> getJobAssetDocs() {
		return jobAssetDocs;
	}

	public void setJobAssetDocs(List<JobAssetDocs> jobAssetDocs) {
		this.jobAssetDocs = jobAssetDocs;
	}

	 
}
