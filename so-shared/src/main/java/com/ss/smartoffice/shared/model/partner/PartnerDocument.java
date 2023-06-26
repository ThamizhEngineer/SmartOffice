package com.ss.smartoffice.shared.model.partner;

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
@Table(name="m_partner_doc")

@Scope("prototype")
public class PartnerDocument {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String docTypeId;
	private String docId;
	private String docTypeName;
	private String docTypeCode;
	private String docNumber;
	private String isAttached;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime attachedDt;
	@Column(name="partner_id")
	private String partnerId;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PartnerDocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PartnerDocument(Integer id, String docTypeId, String docId, String docTypeName, String docTypeCode,
			String docNumber, String isAttached, LocalDateTime attachedDt, String partnerId, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.docTypeId = docTypeId;
		this.docId = docId;
		this.docTypeName = docTypeName;
		this.docTypeCode = docTypeCode;
		this.docNumber = docNumber;
		this.isAttached = isAttached;
		this.attachedDt = attachedDt;
		this.partnerId = partnerId;
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
	public String getDocTypeId() {
		return docTypeId;
	}
	public void setDocTypeId(String docTypeId) {
		this.docTypeId = docTypeId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocTypeName() {
		return docTypeName;
	}
	public void setDocTypeName(String docTypeName) {
		this.docTypeName = docTypeName;
	}
	public String getDocTypeCode() {
		return docTypeCode;
	}
	public void setDocTypeCode(String docTypeCode) {
		this.docTypeCode = docTypeCode;
	}
	public String getDocNumber() {
		return docNumber;
	}
	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}
	public String getIsAttached() {
		return isAttached;
	}
	public void setIsAttached(String isAttached) {
		this.isAttached = isAttached;
	}
	public LocalDateTime getAttachedDt() {
		return attachedDt;
	}
	public void setAttachedDt(LocalDateTime attachedDt) {
		this.attachedDt = attachedDt;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
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
		return "PartnerDocument [id=" + id + ", docTypeId=" + docTypeId + ", docId=" + docId + ", docTypeName="
				+ docTypeName + ", docTypeCode=" + docTypeCode + ", docNumber=" + docNumber + ", isAttached="
				+ isAttached + ", attachedDt=" + attachedDt + ", partnerId=" + partnerId + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
	}


	
}
