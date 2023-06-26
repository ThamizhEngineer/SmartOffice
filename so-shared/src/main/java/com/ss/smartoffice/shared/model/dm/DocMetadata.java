package com.ss.smartoffice.shared.model.dm;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="d_doc_meta_data")
public class DocMetadata {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;
	@Column(name="doc_info_id")
	private Integer docInfoId;
	private String mdCode;
	private String mdValue;
	private String isKey;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDocInfoId() {
		return docInfoId;
	}
	public void setDocInfoId(Integer docInfoId) {
		this.docInfoId = docInfoId;
	}
	public String getMdCode() {
		return mdCode;
	}
	public void setMdCode(String mdCode) {
		this.mdCode = mdCode;
	}
	public String getMdValue() {
		return mdValue;
	}
	public void setMdValue(String mdValue) {
		this.mdValue = mdValue;
	}
	public String getIsKey() {
		return isKey;
	}
	public void setIsKey(String isKey) {
		this.isKey = isKey;
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
	public DocMetadata(Integer id, Integer docInfoId, String mdCode, String mdValue, String isKey, String createdBy,
			String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.docInfoId = docInfoId;
		this.mdCode = mdCode;
		this.mdValue = mdValue;
		this.isKey = isKey;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "DocMetadata [id=" + id + ", docInfoId=" + docInfoId + ", mdCode=" + mdCode + ", mdValue=" + mdValue
				+ ", isKey=" + isKey + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt="
				+ createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	public DocMetadata() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
