package com.ss.smartoffice.sodocumentservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;



public class PurchaseOrderLine {
	private Integer id;
	private Integer purchaseOrderId;
	private String lineDesc;
	private Integer materialId;
	private String materialName;
	private String materialDesc;
	private String materialHsnCode;
	private String isLumpsum;
	private Double unitAmt;
	private Double qty;
	private Double lineGrossAmt;
	private Double lineTaxAmt;
	private Double lineDiscountAmt;
	private String lineStatus;
	private Double receivedQty;
	private Double returnedQty;
	private String ref;
	private Double cgst;
	private Double sgst;
	private Double igst;
	private Double cgstDiscount;
	private Double sgstDiscount;
	private Double igstDiscount;
	private String discountType;
	private Double amtBeforeDiscount;
	private Double amtAfterDiscount;
	@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDate dueOnDt;
	private Double discountPercentage;
	private String isEnabled;
	private String createdBy;
	private String modifiedBy;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDt;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDt;
	public PurchaseOrderLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PurchaseOrderLine(Integer id, Integer purchaseOrderId, String lineDesc, Integer materialId,
			String materialName, String materialDesc, String materialHsnCode, String isLumpsum, Double unitAmt,
			Double qty, Double lineGrossAmt, Double lineTaxAmt, Double lineDiscountAmt, String lineStatus,
			Double receivedQty, Double returnedQty, String ref, Double cgst, Double sgst, Double igst,
			Double cgstDiscount, Double sgstDiscount, Double igstDiscount, String discountType,
			Double amtBeforeDiscount, Double amtAfterDiscount, LocalDate dueOnDt, Double discountPercentage,
			String isEnabled, String createdBy, String modifiedBy, LocalDateTime createdDt, LocalDateTime modifiedDt) {
		super();
		this.id = id;
		this.purchaseOrderId = purchaseOrderId;
		this.lineDesc = lineDesc;
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialDesc = materialDesc;
		this.materialHsnCode = materialHsnCode;
		this.isLumpsum = isLumpsum;
		this.unitAmt = unitAmt;
		this.qty = qty;
		this.lineGrossAmt = lineGrossAmt;
		this.lineTaxAmt = lineTaxAmt;
		this.lineDiscountAmt = lineDiscountAmt;
		this.lineStatus = lineStatus;
		this.receivedQty = receivedQty;
		this.returnedQty = returnedQty;
		this.ref = ref;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.cgstDiscount = cgstDiscount;
		this.sgstDiscount = sgstDiscount;
		this.igstDiscount = igstDiscount;
		this.discountType = discountType;
		this.amtBeforeDiscount = amtBeforeDiscount;
		this.amtAfterDiscount = amtAfterDiscount;
		this.dueOnDt = dueOnDt;
		this.discountPercentage = discountPercentage;
		this.isEnabled = isEnabled;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.createdDt = createdDt;
		this.modifiedDt = modifiedDt;
	}
	@Override
	public String toString() {
		return "PurchaseOrderLine [id=" + id + ", purchaseOrderId=" + purchaseOrderId + ", lineDesc=" + lineDesc
				+ ", materialId=" + materialId + ", materialName=" + materialName + ", materialDesc=" + materialDesc
				+ ", materialHsnCode=" + materialHsnCode + ", isLumpsum=" + isLumpsum + ", unitAmt=" + unitAmt
				+ ", qty=" + qty + ", lineGrossAmt=" + lineGrossAmt + ", lineTaxAmt=" + lineTaxAmt
				+ ", lineDiscountAmt=" + lineDiscountAmt + ", lineStatus=" + lineStatus + ", receivedQty=" + receivedQty
				+ ", returnedQty=" + returnedQty + ", ref=" + ref + ", cgst=" + cgst + ", sgst=" + sgst + ", igst="
				+ igst + ", cgstDiscount=" + cgstDiscount + ", sgstDiscount=" + sgstDiscount + ", igstDiscount="
				+ igstDiscount + ", discountType=" + discountType + ", amtBeforeDiscount=" + amtBeforeDiscount
				+ ", amtAfterDiscount=" + amtAfterDiscount + ", dueOnDt=" + dueOnDt + ", discountPercentage="
				+ discountPercentage + ", isEnabled=" + isEnabled + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", createdDt=" + createdDt + ", modifiedDt=" + modifiedDt + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	public String getLineDesc() {
		return lineDesc;
	}
	public void setLineDesc(String lineDesc) {
		this.lineDesc = lineDesc;
	}
	public Integer getMaterialId() {
		return materialId;
	}
	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	public String getMaterialDesc() {
		return materialDesc;
	}
	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}
	public String getMaterialHsnCode() {
		return materialHsnCode;
	}
	public void setMaterialHsnCode(String materialHsnCode) {
		this.materialHsnCode = materialHsnCode;
	}
	public String getIsLumpsum() {
		return isLumpsum;
	}
	public void setIsLumpsum(String isLumpsum) {
		this.isLumpsum = isLumpsum;
	}
	public Double getUnitAmt() {
		return unitAmt;
	}
	public void setUnitAmt(Double unitAmt) {
		this.unitAmt = unitAmt;
	}
	public Double getQty() {
		return qty;
	}
	public void setQty(Double qty) {
		this.qty = qty;
	}
	public Double getLineGrossAmt() {
		return lineGrossAmt;
	}
	public void setLineGrossAmt(Double lineGrossAmt) {
		this.lineGrossAmt = lineGrossAmt;
	}
	public Double getLineTaxAmt() {
		return lineTaxAmt;
	}
	public void setLineTaxAmt(Double lineTaxAmt) {
		this.lineTaxAmt = lineTaxAmt;
	}
	public Double getLineDiscountAmt() {
		return lineDiscountAmt;
	}
	public void setLineDiscountAmt(Double lineDiscountAmt) {
		this.lineDiscountAmt = lineDiscountAmt;
	}
	public String getLineStatus() {
		return lineStatus;
	}
	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}
	public Double getReceivedQty() {
		return receivedQty;
	}
	public void setReceivedQty(Double receivedQty) {
		this.receivedQty = receivedQty;
	}
	public Double getReturnedQty() {
		return returnedQty;
	}
	public void setReturnedQty(Double returnedQty) {
		this.returnedQty = returnedQty;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
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
	public Double getCgstDiscount() {
		return cgstDiscount;
	}
	public void setCgstDiscount(Double cgstDiscount) {
		this.cgstDiscount = cgstDiscount;
	}
	public Double getSgstDiscount() {
		return sgstDiscount;
	}
	public void setSgstDiscount(Double sgstDiscount) {
		this.sgstDiscount = sgstDiscount;
	}
	public Double getIgstDiscount() {
		return igstDiscount;
	}
	public void setIgstDiscount(Double igstDiscount) {
		this.igstDiscount = igstDiscount;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public Double getAmtBeforeDiscount() {
		return amtBeforeDiscount;
	}
	public void setAmtBeforeDiscount(Double amtBeforeDiscount) {
		this.amtBeforeDiscount = amtBeforeDiscount;
	}
	public Double getAmtAfterDiscount() {
		return amtAfterDiscount;
	}
	public void setAmtAfterDiscount(Double amtAfterDiscount) {
		this.amtAfterDiscount = amtAfterDiscount;
	}
	public LocalDate getDueOnDt() {
		return dueOnDt;
	}
	public void setDueOnDt(LocalDate dueOnDt) {
		this.dueOnDt = dueOnDt;
	}
	public Double getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
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
