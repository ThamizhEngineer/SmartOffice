package com.ss.smartoffice.sodocumentservice.documentmanager;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "d_doc_folder")

public class DocFolder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name;
	@Column(name = "d_doc_header_id")
	private Integer docHeaderId;
	@Column(name = "d_doc_folder_id")
	private Integer docFolderId;
	@Column(columnDefinition = "CHAR")
	private String readOnly;
	private String type;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	private String location;
	
	public DocFolder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DocFolder(Integer id, String code, String name, Integer docHeaderId, Integer docFolderId, String readOnly,
			String type, String createdBy, LocalDateTime createdDt, String modifiedBy, LocalDateTime modifiedDt,
			String location) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.docHeaderId = docHeaderId;
		this.docFolderId = docFolderId;
		this.readOnly = readOnly;
		this.type = type;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.location = location;
	}

	@Override
	public String toString() {
		return "DocFolder [id=" + id + ", code=" + code + ", name=" + name + ", docHeaderId=" + docHeaderId
				+ ", docFolderId=" + docFolderId + ", readOnly=" + readOnly + ", type=" + type + ", createdBy="
				+ createdBy + ", createdDt=" + createdDt + ", modifiedBy=" + modifiedBy + ", modifiedDt=" + modifiedDt
				+ ", location=" + location + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDocHeaderId() {
		return docHeaderId;
	}

	public void setDocHeaderId(Integer docHeaderId) {
		this.docHeaderId = docHeaderId;
	}

	public Integer getDocFolderId() {
		return docFolderId;
	}

	public void setDocFolderId(Integer docFolderId) {
		this.docFolderId = docFolderId;
	}

	public String getReadOnly() {
		return readOnly;
	}

	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(LocalDateTime createdDt) {
		this.createdDt = createdDt;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDt() {
		return modifiedDt;
	}

	public void setModifiedDt(LocalDateTime modifiedDt) {
		this.modifiedDt = modifiedDt;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
