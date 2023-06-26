package com.ss.smartoffice.soservice.transaction.model;

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
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="t_upload_payslip_hdr")
@Scope("prototype")
public class UploadPayslipHdr {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	
	private String  upload_code ;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime upload_datetime;
	private Integer payMonth;
	private String payYear ;
	private String  process_status ;
	private String  records_uploaded ;
	private Integer success_count ;
	private String doc_id ;
	private String processId;
	private String createdBy;
	private String modifiedBy;
	private Integer compPayCycleLineId;
	private String isOverwriteFlag;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String remarks;
	
	@OneToMany(fetch=FetchType.EAGER,cascade= CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name="upload_payslip_hdr_id")
	private List<UploadPayslipLine> uploadPayslipLines;
	
	public UploadPayslipHdr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UploadPayslipHdr(Integer id, String upload_code, LocalDateTime upload_datetime, Integer payMonth,
			String payYear, String process_status, String records_uploaded, Integer success_count, String doc_id,
			String processId, String createdBy, String modifiedBy, Integer compPayCycleLineId, String isOverwriteFlag,
			LocalDateTime createdDt, LocalDateTime modifiedDt, String remarks,
			List<UploadPayslipLine> uploadPayslipLines) {
		super();
		this.id = id;
		this.upload_code = upload_code;
		this.upload_datetime = upload_datetime;
		this.payMonth = payMonth;
		this.payYear = payYear;
		this.process_status = process_status;
		this.records_uploaded = records_uploaded;
		this.success_count = success_count;
		this.doc_id = doc_id;
		this.processId = processId;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.compPayCycleLineId = compPayCycleLineId;
		this.isOverwriteFlag = isOverwriteFlag;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.remarks = remarks;
		this.uploadPayslipLines = uploadPayslipLines;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUpload_code() {
		return upload_code;
	}

	public void setUpload_code(String upload_code) {
		this.upload_code = upload_code;
	}

	public LocalDateTime getUpload_datetime() {
		return upload_datetime;
	}

	public void setUpload_datetime(LocalDateTime upload_datetime) {
		this.upload_datetime = upload_datetime;
	}

	public Integer getPayMonth() {
		return payMonth;
	}

	public void setPayMonth(Integer payMonth) {
		this.payMonth = payMonth;
	}

	public String getPayYear() {
		return payYear;
	}

	public void setPayYear(String payYear) {
		this.payYear = payYear;
	}

	public String getProcess_status() {
		return process_status;
	}

	public void setProcess_status(String process_status) {
		this.process_status = process_status;
	}

	public String getRecords_uploaded() {
		return records_uploaded;
	}

	public void setRecords_uploaded(String records_uploaded) {
		this.records_uploaded = records_uploaded;
	}

	public Integer getSuccess_count() {
		return success_count;
	}

	public void setSuccess_count(Integer success_count) {
		this.success_count = success_count;
	}

	public String getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
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

	public Integer getCompPayCycleLineId() {
		return compPayCycleLineId;
	}

	public void setCompPayCycleLineId(Integer compPayCycleLineId) {
		this.compPayCycleLineId = compPayCycleLineId;
	}

	public String getIsOverwriteFlag() {
		return isOverwriteFlag;
	}

	public void setIsOverwriteFlag(String isOverwriteFlag) {
		this.isOverwriteFlag = isOverwriteFlag;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<UploadPayslipLine> getUploadPayslipLines() {
		return uploadPayslipLines;
	}

	public void setUploadPayslipLines(List<UploadPayslipLine> uploadPayslipLines) {
		this.uploadPayslipLines = uploadPayslipLines;
	}

	@Override
	public String toString() {
		return "UploadPayslipHdr [id=" + id + ", upload_code=" + upload_code + ", upload_datetime=" + upload_datetime
				+ ", payMonth=" + payMonth + ", payYear=" + payYear + ", process_status=" + process_status
				+ ", records_uploaded=" + records_uploaded + ", success_count=" + success_count + ", doc_id=" + doc_id
				+ ", processId=" + processId + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", compPayCycleLineId=" + compPayCycleLineId + ", isOverwriteFlag=" + isOverwriteFlag + ", createdDt="
				+ createdDt + ", modifiedDt=" + modifiedDt + ", remarks=" + remarks + ", uploadPayslipLines="
				+ uploadPayslipLines + "]";
	}

	


	
}
