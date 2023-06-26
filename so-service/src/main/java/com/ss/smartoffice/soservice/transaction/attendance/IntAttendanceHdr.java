package com.ss.smartoffice.soservice.transaction.attendance;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity

@Table(name = "i_attendance_hdr")
public class IntAttendanceHdr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String uploadDocId;
	private LocalDateTime uploadDate;
	private LocalDateTime processDate;
	private String overWritten;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String isError;
	private String errorMessage;
	private String status;
	
	public IntAttendanceHdr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IntAttendanceHdr(Integer id, String uploadDocId, LocalDateTime uploadDate, LocalDateTime processDate,
			String overWritten, String createdBy, LocalDateTime createdDate, String modifiedBy,
			LocalDateTime modifiedDate, String isError, String errorMessage, String status) {
		super();
		this.id = id;
		this.uploadDocId = uploadDocId;
		this.uploadDate = uploadDate;
		this.processDate = processDate;
		this.overWritten = overWritten;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isError = isError;
		this.errorMessage = errorMessage;
		this.status = status;
	}

	@Override
	public String toString() {
		return "IntAttendanceHdr [id=" + id + ", uploadDocId=" + uploadDocId + ", uploadDate=" + uploadDate
				+ ", processDate=" + processDate + ", overWritten=" + overWritten + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", isError=" + isError + ", errorMessage=" + errorMessage + ", status=" + status + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUploadDocId() {
		return uploadDocId;
	}

	public void setUploadDocId(String uploadDocId) {
		this.uploadDocId = uploadDocId;
	}

	public LocalDateTime getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(LocalDateTime uploadDate) {
		this.uploadDate = uploadDate;
	}

	public LocalDateTime getProcessDate() {
		return processDate;
	}

	public void setProcessDate(LocalDateTime processDate) {
		this.processDate = processDate;
	}

	public String getOverWritten() {
		return overWritten;
	}

	public void setOverWritten(String overWritten) {
		this.overWritten = overWritten;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	

}

