package com.ss.smartoffice.soservice.transaction.model;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name="t_purc_order_payout")

@Scope("prototype")
public class PurchaseOrderPayout extends BaseEntity {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String isAdvance;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime payoutDate;
	private Double payoutAmount;
	private String docId;
	@Column(name="t_purc_order_id")
	private Integer purchaseOrderId;
	private String payoutRef;
	private String remarks;
	private String payoutMethod;
	@Formula("(SELECT  tpo.vendor_id from  t_purc_order tpo left join t_purc_order_payout tpop on tpop.t_purc_order_id=tpo.id where tpop.t_purc_order_id=t_purc_order_id GROUP By tpop.t_purc_order_id)")
	private String vendorId;
	@Formula("(SELECT  tpo.vendor_name from  t_purc_order tpo left join t_purc_order_payout tpop on tpop.t_purc_order_id=tpo.id where tpop.t_purc_order_id=t_purc_order_id GROUP By tpop.t_purc_order_id)")
	private String vendorName;
	private String receivedBy;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	
	public PurchaseOrderPayout() {
		super();
		// TODO Auto-generated constructor stub
	}
	// constructor using all fields

	public PurchaseOrderPayout(int id, String isAdvance, LocalDateTime payoutDate, Double payoutAmount, String docId,
			Integer purchaseOrderId, String payoutRef, String remarks, String payoutMethod, String vendorId,
			String vendorName, String receivedBy, String isEnabled, String createdBy, String modifiedBy,
			LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.isAdvance = isAdvance;
		this.payoutDate = payoutDate;
		this.payoutAmount = payoutAmount;
		this.docId = docId;
		this.purchaseOrderId = purchaseOrderId;
		this.payoutRef = payoutRef;
		this.remarks = remarks;
		this.payoutMethod = payoutMethod;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
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
				+ ", payoutRef=" + payoutRef + ", remarks=" + remarks + ", payoutMethod=" + payoutMethod + ", vendorId="
				+ vendorId + ", vendorName=" + vendorName + ", receivedBy=" + receivedBy + ", isEnabled=" + isEnabled
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + ", createdDt=" + createdDt
				+ ", modifiedDt=" + modifiedDt + "]";
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	
	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
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
