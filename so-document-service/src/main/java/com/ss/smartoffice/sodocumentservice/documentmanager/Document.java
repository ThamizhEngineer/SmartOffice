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
@Table(name = "d_document")

public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	@Column(columnDefinition = "CHAR")
	private String readOnly;
	private String type;
	private String location;
	private String docId;
	@Column(name = "doc_type_id")
	private String docTypeid;
	@Column(name = "doc_type_name")
	private String docTypeName;
	private String extension;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@Column(name = "d_doc_header_id")
	private Integer docHeaderId;
	@Column(name = "d_doc_folder_id")
	private Integer docFolderId;
	private String systemGivenName;
	
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Document(Integer id, String name, String readOnly, String type, String location, String docId,
			String docTypeid, String docTypeName, String extension, String createdBy, LocalDateTime createdDt,
			String modifiedBy, LocalDateTime modifiedDt, Integer docHeaderId, Integer docFolderId,
			String systemGivenName) {
		super();
		this.id = id;
		this.name = name;
		this.readOnly = readOnly;
		this.type = type;
		this.location = location;
		this.docId = docId;
		this.docTypeid = docTypeid;
		this.docTypeName = docTypeName;
		this.extension = extension;
		this.createdBy = createdBy;
		this.createdDt = createdDt;
		this.modifiedBy = modifiedBy;
		this.modifiedDt = modifiedDt;
		this.docHeaderId = docHeaderId;
		this.docFolderId = docFolderId;
		this.systemGivenName = systemGivenName;
	}

	@Override
	public String toString() {
		return "Document [id=" + id + ", name=" + name + ", readOnly=" + readOnly + ", type=" + type + ", location="
				+ location + ", docId=" + docId + ", docTypeid=" + docTypeid + ", docTypeName=" + docTypeName
				+ ", extension=" + extension + ", createdBy=" + createdBy + ", createdDt=" + createdDt + ", modifiedBy="
				+ modifiedBy + ", modifiedDt=" + modifiedDt + ", docHeaderId=" + docHeaderId + ", docFolderId="
				+ docFolderId + ", systemGivenName=" + systemGivenName + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getDocTypeid() {
		return docTypeid;
	}

	public void setDocTypeid(String docTypeid) {
		this.docTypeid = docTypeid;
	}

	public String getDocTypeName() {
		return docTypeName;
	}

	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
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

	public String getSystemGivenName() {
		return systemGivenName;
	}

	public void setSystemGivenName(String systemGivenName) {
		this.systemGivenName = systemGivenName;
	}

	
	
}
