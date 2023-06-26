package com.ss.smartoffice.soservice.transaction.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ss.smartoffice.shared.common.BaseEntity;

@Entity
@Table(name = "t_purc_order")

@Scope("prototype")
public class PurchaseOrder extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer vendorId;
	private String vendorName;
	private String vendorCode;
	private String poCode;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate poDt;
	private Double grossPoAmt;
	private Double totalTaxAmt;
	private Double totalDiscountAmt;
	private Double netPoAmt;
	private Double totalPaidAmt;
	private Double totalDueAmt;
	private String poNote;
	private String poStatus;
	private String poRefNumber;
	private String refType;
	

	private String poName;
	private String billingRemarks;
	private String address;
	private String phoneNumber;
	private String emailId;
	private Double cgst;
	private Double sgst;
	private Double igst;
	private Double totalShippingAmt;
	private Double otherAmt;
	private String supplierRefNumber;
	private String otherRefNumber;
	private String despatchThrough;
	private String destination;
	private String deliveryTerms;
	private String docId;
	private String vendorTinNumber;
	private String vendorPanNumber;
	
	@Transient
	private String emailSubject;
	
	@Transient
	private String emailBody;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "t_purc_order_id")
	private List<PurchaseOrderLine> purchaseOrderLines;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "t_purc_order_id")
	private List<PurchaseOrderPayout> purchaseOrderPayouts;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@UpdateTimestamp
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime paymentDueDt;
	public PurchaseOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseOrder(Integer id, Integer vendorId, String poCode, LocalDate poDt, Double netPoAmt) {
		super();
		this.id = id;
		this.vendorId = vendorId;
		this.poCode = poCode;
		this.poDt = poDt;
		this.netPoAmt = netPoAmt;
	}
	// constructor using all fields
	public PurchaseOrder(Integer id, Integer vendorId, String vendorName, String vendorCode, String poCode,
			LocalDate poDt, Double grossPoAmt, Double totalTaxAmt, Double totalDiscountAmt, Double netPoAmt,
			Double totalPaidAmt, Double totalDueAmt, String poNote, String poStatus, String poRefNumber, String refType,
			String poName, String billingRemarks, String address, String phoneNumber, String emailId, Double cgst,
			Double sgst, Double igst, Double totalShippingAmt, Double otherAmt, String supplierRefNumber,
			String otherRefNumber, String despatchThrough, String destination, String deliveryTerms, String docId,
			String vendorTinNumber, String vendorPanNumber, String emailSubject, String emailBody,
			List<PurchaseOrderLine> purchaseOrderLines, List<PurchaseOrderPayout> purchaseOrderPayouts,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt,
			LocalDateTime paymentDueDt) {
		super();
		this.id = id;
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorCode = vendorCode;
		this.poCode = poCode;
		this.poDt = poDt;
		this.grossPoAmt = grossPoAmt;
		this.totalTaxAmt = totalTaxAmt;
		this.totalDiscountAmt = totalDiscountAmt;
		this.netPoAmt = netPoAmt;
		this.totalPaidAmt = totalPaidAmt;
		this.totalDueAmt = totalDueAmt;
		this.poNote = poNote;
		this.poStatus = poStatus;
		this.poRefNumber = poRefNumber;
		this.refType = refType;
		this.poName = poName;
		this.billingRemarks = billingRemarks;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.totalShippingAmt = totalShippingAmt;
		this.otherAmt = otherAmt;
		this.supplierRefNumber = supplierRefNumber;
		this.otherRefNumber = otherRefNumber;
		this.despatchThrough = despatchThrough;
		this.destination = destination;
		this.deliveryTerms = deliveryTerms;
		this.docId = docId;
		this.vendorTinNumber = vendorTinNumber;
		this.vendorPanNumber = vendorPanNumber;
		this.emailSubject = emailSubject;
		this.emailBody = emailBody;
		this.purchaseOrderLines = purchaseOrderLines;
		this.purchaseOrderPayouts = purchaseOrderPayouts;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
		this.paymentDueDt = paymentDueDt;
	}
	
	@Override
	public String toString() {
		return "PurchaseOrder [id=" + id + ", vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorCode="
				+ vendorCode + ", poCode=" + poCode + ", poDt=" + poDt + ", grossPoAmt=" + grossPoAmt + ", totalTaxAmt="
				+ totalTaxAmt + ", totalDiscountAmt=" + totalDiscountAmt + ", netPoAmt=" + netPoAmt + ", totalPaidAmt="
				+ totalPaidAmt + ", totalDueAmt=" + totalDueAmt + ", poNote=" + poNote + ", poStatus=" + poStatus
				+ ", poRefNumber=" + poRefNumber + ", refType=" + refType + ", poName=" + poName + ", billingRemarks="
				+ billingRemarks + ", address=" + address + ", phoneNumber=" + phoneNumber + ", emailId=" + emailId
				+ ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst + ", totalShippingAmt=" + totalShippingAmt
				+ ", otherAmt=" + otherAmt + ", supplierRefNumber=" + supplierRefNumber + ", otherRefNumber="
				+ otherRefNumber + ", despatchThrough=" + despatchThrough + ", destination=" + destination
				+ ", deliveryTerms=" + deliveryTerms + ", docId=" + docId + ", vendorTinNumber=" + vendorTinNumber
				+ ", vendorPanNumber=" + vendorPanNumber + ", emailSubject=" + emailSubject + ", emailBody=" + emailBody
				+ ", purchaseOrderLines=" + purchaseOrderLines + ", purchaseOrderPayouts=" + purchaseOrderPayouts
				+ ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy
				+ ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + ", paymentDueDt=" + paymentDueDt + "]";
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getVendorId() {
		return vendorId;
	}
	public void setVendorId(Integer vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getPoCode() {
		return poCode;
	}
	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}
	public LocalDate getPoDt() {
		return poDt;
	}
	public void setPoDt(LocalDate poDt) {
		this.poDt = poDt;
	}
	public Double getGrossPoAmt() {
		return grossPoAmt;
	}
	public void setGrossPoAmt(Double grossPoAmt) {
		this.grossPoAmt = grossPoAmt;
	}
	public Double getTotalTaxAmt() {
		return totalTaxAmt;
	}
	public void setTotalTaxAmt(Double totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}
	public Double getTotalDiscountAmt() {
		return totalDiscountAmt;
	}
	public void setTotalDiscountAmt(Double totalDiscountAmt) {
		this.totalDiscountAmt = totalDiscountAmt;
	}
	public Double getNetPoAmt() {
		return netPoAmt;
	}
	public void setNetPoAmt(Double netPoAmt) {
		this.netPoAmt = netPoAmt;
	}
	public Double getTotalPaidAmt() {
		return totalPaidAmt;
	}
	public void setTotalPaidAmt(Double totalPaidAmt) {
		this.totalPaidAmt = totalPaidAmt;
	}
	public Double getTotalDueAmt() {
		return totalDueAmt;
	}
	public void setTotalDueAmt(Double totalDueAmt) {
		this.totalDueAmt = totalDueAmt;
	}
	public String getPoNote() {
		return poNote;
	}
	public void setPoNote(String poNote) {
		this.poNote = poNote;
	}
	public String getPoStatus() {
		return poStatus;
	}
	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}
	public String getPoRefNumber() {
		return poRefNumber;
	}
	public void setPoRefNumber(String poRefNumber) {
		this.poRefNumber = poRefNumber;
	}
	public String getRefType() {
		return refType;
	}
	public void setRefType(String refType) {
		this.refType = refType;
	}
	public String getPoName() {
		return poName;
	}
	public void setPoName(String poName) {
		this.poName = poName;
	}
	public String getBillingRemarks() {
		return billingRemarks;
	}
	public void setBillingRemarks(String billingRemarks) {
		this.billingRemarks = billingRemarks;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Double getCgst() {
		return cgst;
	}
	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}
	public Double getSgst() {
		return sgst;
	}
	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}
	public Double getIgst() {
		return igst;
	}
	public void setIgst(Double igst) {
		this.igst = igst;
	}
	public Double getTotalShippingAmt() {
		return totalShippingAmt;
	}
	public void setTotalShippingAmt(Double totalShippingAmt) {
		this.totalShippingAmt = totalShippingAmt;
	}
	public Double getOtherAmt() {
		return otherAmt;
	}
	public void setOtherAmt(Double otherAmt) {
		this.otherAmt = otherAmt;
	}
	public String getSupplierRefNumber() {
		return supplierRefNumber;
	}
	public void setSupplierRefNumber(String supplierRefNumber) {
		this.supplierRefNumber = supplierRefNumber;
	}
	public String getOtherRefNumber() {
		return otherRefNumber;
	}
	public void setOtherRefNumber(String otherRefNumber) {
		this.otherRefNumber = otherRefNumber;
	}
	public String getDespatchThrough() {
		return despatchThrough;
	}
	public void setDespatchThrough(String despatchThrough) {
		this.despatchThrough = despatchThrough;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getDeliveryTerms() {
		return deliveryTerms;
	}
	public void setDeliveryTerms(String deliveryTerms) {
		this.deliveryTerms = deliveryTerms;
	}
	public LocalDateTime getPaymentDueDt() {
		return paymentDueDt;
	}
	public void setPaymentDueDt(LocalDateTime paymentDueDt) {
		this.paymentDueDt = paymentDueDt;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getVendorTinNumber() {
		return vendorTinNumber;
	}
	public void setVendorTinNumber(String vendorTinNumber) {
		this.vendorTinNumber = vendorTinNumber;
	}
	public String getVendorPanNumber() {
		return vendorPanNumber;
	}
	public void setVendorPanNumber(String vendorPanNumber) {
		this.vendorPanNumber = vendorPanNumber;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public List<PurchaseOrderLine> getPurchaseOrderLines() {
		return purchaseOrderLines;
	}
	public void setPurchaseOrderLines(List<PurchaseOrderLine> purchaseOrderLines) {
		this.purchaseOrderLines = purchaseOrderLines;
	}
	public List<PurchaseOrderPayout> getPurchaseOrderPayouts() {
		return purchaseOrderPayouts;
	}
	public void setPurchaseOrderPayouts(List<PurchaseOrderPayout> purchaseOrderPayouts) {
		this.purchaseOrderPayouts = purchaseOrderPayouts;
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
