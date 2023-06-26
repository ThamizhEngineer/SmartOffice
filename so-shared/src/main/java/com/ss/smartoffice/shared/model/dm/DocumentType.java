package com.ss.smartoffice.shared.model.dm;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="d_doc_type")

public class DocumentType {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String docTypeCode;
	private String docTypeName;
	private String firstFolderName;
	private String secondFolderName;
	private String thirdFolderName;
	private String fourthFolderName;
	private String defaultUserDocumentName;
	private String docTypeDesc;
	private String isEmp;
	private String templateName;	
	private String docExtension;
	private String isJob;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public DocumentType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DocumentType(Integer id, String docTypeCode, String docTypeName, String firstFolderName,
			String secondFolderName, String thirdFolderName, String fourthFolderName, String defaultUserDocumentName,
			String docTypeDesc, String isEmp, String templateName, String docExtension, String isJob, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.docTypeCode = docTypeCode;
		this.docTypeName = docTypeName;
		this.firstFolderName = firstFolderName;
		this.secondFolderName = secondFolderName;
		this.thirdFolderName = thirdFolderName;
		this.fourthFolderName = fourthFolderName;
		this.defaultUserDocumentName = defaultUserDocumentName;
		this.docTypeDesc = docTypeDesc;
		this.isEmp = isEmp;
		this.templateName = templateName;
		this.docExtension = docExtension;
		this.isJob = isJob;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}
	public String getFirstFolderName() {
		return firstFolderName;
	}
	public void setFirstFolderName(String firstFolderName) {
		this.firstFolderName = firstFolderName;
	}
	public String getSecondFolderName() {
		return secondFolderName;
	}
	public void setSecondFolderName(String secondFolderName) {
		this.secondFolderName = secondFolderName;
	}
	public String getThirdFolderName() {
		return thirdFolderName;
	}
	public void setThirdFolderName(String thirdFolderName) {
		this.thirdFolderName = thirdFolderName;
	}
	public String getFourthFolderName() {
		return fourthFolderName;
	}
	public void setFourthFolderName(String fourthFolderName) {
		this.fourthFolderName = fourthFolderName;
	}
	public String getDefaultUserDocumentName() {
		return defaultUserDocumentName;
	}
	public void setDefaultUserDocumentName(String defaultUserDocumentName) {
		this.defaultUserDocumentName = defaultUserDocumentName;
	}
	public String getDocTypeDesc() {
		return docTypeDesc;
	}
	public void setDocTypeDesc(String docTypeDesc) {
		this.docTypeDesc = docTypeDesc;
	}
	public String getIsEmp() {
		return isEmp;
	}
	public void setIsEmp(String isEmp) {
		this.isEmp = isEmp;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getDocExtension() {
		return docExtension;
	}
	public void setDocExtension(String docExtension) {
		this.docExtension = docExtension;
	}
	public String getIsJob() {
		return isJob;
	}
	public void setIsJob(String isJob) {
		this.isJob = isJob;
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
	@Override
	public String toString() {
		return "DocumentType [id=" + id + ", docTypeCode=" + docTypeCode + ", docTypeName=" + docTypeName
				+ ", firstFolderName=" + firstFolderName + ", secondFolderName=" + secondFolderName
				+ ", thirdFolderName=" + thirdFolderName + ", fourthFolderName=" + fourthFolderName
				+ ", defaultUserDocumentName=" + defaultUserDocumentName + ", docTypeDesc=" + docTypeDesc + ", isEmp="
				+ isEmp + ", templateName=" + templateName + ", docExtension=" + docExtension + ", isJob=" + isJob
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	
	
	
	
	
}
