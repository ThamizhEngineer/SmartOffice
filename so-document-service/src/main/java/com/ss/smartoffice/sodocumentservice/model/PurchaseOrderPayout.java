package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class PurchaseOrderPayout {
	private int id;
	private String isAdvance;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payoutDate;
	private Double payoutAmount;
	private String docId;
	private Integer purchaseOrderId;
	private String payoutRef;
	private String payoutMethod;
	private String receivedBy;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PurchaseOrderPayout() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseOrderPayout(int id, String isAdvance, LocalDateTime payoutDate, Double payoutAmount, String docId,
			Integer purchaseOrderId, String payoutRef, String payoutMethod, String receivedBy, String isEnabled,
			String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.isAdvance = isAdvance;
		this.payoutDate = payoutDate;
		this.payoutAmount = payoutAmount;
		this.docId = docId;
		this.purchaseOrderId = purchaseOrderId;
		this.payoutRef = payoutRef;
		this.payoutMethod = payoutMethod;
		this.receivedBy = receivedBy;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "PurchaseOrderPayout [id=" + id + ", isAdvance=" + isAdvance + ", payoutDate=" + payoutDate
				+ ", payoutAmount=" + payoutAmount + ", docId=" + docId + ", purchaseOrderId=" + purchaseOrderId
				+ ", payoutRef=" + payoutRef + ", payoutMethod=" + payoutMethod + ", receivedBy=" + receivedBy
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIsAdvance() {
		return isAdvance;
	}
	public void setIsAdvance(String isAdvance) {
		this.isAdvance = isAdvance;
	}
	public LocalDateTime getPayoutDate() {
		return payoutDate;
	}
	public void setPayoutDate(LocalDateTime payoutDate) {
		this.payoutDate = payoutDate;
	}
	public Double getPayoutAmount() {
		return payoutAmount;
	}
	public void setPayoutAmount(Double payoutAmount) {
		this.payoutAmount = payoutAmount;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getPayoutRef() {
		return payoutRef;
	}
	public void setPayoutRef(String payoutRef) {
		this.payoutRef = payoutRef;
	}
	public String getPayoutMethod() {
		return payoutMethod;
	}
	public void setPayoutMethod(String payoutMethod) {
		this.payoutMethod = payoutMethod;
	}
	public String getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(String receivedBy) {
		this.receivedBy = receivedBy;
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
