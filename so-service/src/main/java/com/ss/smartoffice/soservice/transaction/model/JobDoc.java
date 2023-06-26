package com.ss.smartoffice.soservice.transaction.model;

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
@Table(name="t_job_docs")

@Scope("prototype")
public class JobDoc {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="t_job_id")
	private String jobId;
	private String docId;
	private String docName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime uploadDt;
	private String uploadUserId;
	private String remarks;
	private String docTypeCode;
	private String isSourceOnsite;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public JobDoc() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobDoc(Integer id, String jobId, String docId, String docName, LocalDateTime uploadDt, String uploadUserId,
			String remarks, String docTypeCode, String isSourceOnsite, String isEnabled, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.jobId = jobId;
		this.docId = docId;
		this.docName = docName;
		this.uploadDt = uploadDt;
		this.uploadUserId = uploadUserId;
		this.remarks = remarks;
		this.docTypeCode = docTypeCode;
		this.isSourceOnsite = isSourceOnsite;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}

	@Override
	public String toString() {
		return "JobDoc [id=" + id + ", jobId=" + jobId + ", docId=" + docId + ", docName=" + docName + ", uploadDt="
				+ uploadDt + ", uploadUserId=" + uploadUserId + ", remarks=" + remarks + ", docTypeCode=" + docTypeCode
				+ ", isSourceOnsite=" + isSourceOnsite + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
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

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public LocalDateTime getUploadDt() {
		return uploadDt;
	}

	public void setUploadDt(LocalDateTime uploadDt) {
		this.uploadDt = uploadDt;
	}

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocTypeCode() {
		return docTypeCode;
	}

	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}

	public String getIsSourceOnsite() {
		return isSourceOnsite;
	}

	public void setIsSourceOnsite(String isSourceOnsite) {
		this.isSourceOnsite = isSourceOnsite;
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
