package com.ss.smartoffice.shared.model.dm;

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
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "d_doc_info")
public class DocInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String docLocation;
	@Transient
	private String newDocLocation;
	private String docExtension;
	private String docNameFromUser;
	private String docName;
	private String docId;
	private String docTypeId;
	private String docSize;
	private String createdBy;
	private String modifiedBy;
	@Transient
	private String csvHdrId;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "doc_info_id")
	private List<DocMetadata> metadataList;

	public DocInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNewDocLocation() {
		return newDocLocation;
	}

	public void setNewDocLocation(String newDocLocation) {
		this.newDocLocation = newDocLocation;
	}

	public String getDocLocation() {
		return docLocation;
	}

	public void setDocLocation(String docLocation) {
		this.docLocation = docLocation;
	}

	public String getDocExtension() {
		return docExtension;
	}

	public void setDocExtension(String docExtension) {
		this.docExtension = docExtension;
	}

	public String getDocNameFromUser() {
		return docNameFromUser;
	}

	public void setDocNameFromUser(String docNameFromUser) {
		this.docNameFromUser = docNameFromUser;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getCsvHdrId() {
		return csvHdrId;
	}

	public void setCsvHdrId(String csvHdrId) {
		this.csvHdrId = csvHdrId;
	}

	public String getDocTypeId() {
		return docTypeId;
	}

	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
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

	public List<DocMetadata> getMetadataList() {
		return metadataList;
	}

	public void setMetadataList(List<DocMetadata> metadataList) {
		this.metadataList = metadataList;
	}

	@Override
	public String toString() {
		return "DocInfo [id=" + id + ", docLocation=" + docLocation + ", newDocLocation=" + newDocLocation
				+ ", docExtension=" + docExtension + ", docNameFromUser=" + docNameFromUser + ", docName=" + docName
				+ ", docId=" + docId + ", docTypeId=" + docTypeId + ", docSize=" + docSize + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", csvHdrId=" + csvHdrId + ", createdDt=" + createdDt + ", modifiedDt="
				+ modifiedDt + ", metadataList=" + metadataList + "]";
	}

	public DocInfo(Integer id, String docLocation, String newDocLocation, String docExtension, String docNameFromUser,
			String docName, String docId, String docTypeId, String docSize, String createdBy, String modifiedBy,
			String csvHdrId, LocalDateTime createdDt, LocalDateTime modifiedDt, List<DocMetadata> metadataList) {
		super();
		this.id = id;
		this.docLocation = docLocation;
		this.newDocLocation = newDocLocation;
		this.docExtension = docExtension;
		this.docNameFromUser = docNameFromUser;
		this.docName = docName;
		this.docId = docId;
		this.docTypeId = docTypeId;
		this.docSize = docSize;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.csvHdrId = csvHdrId;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.metadataList = metadataList;
	}

}
