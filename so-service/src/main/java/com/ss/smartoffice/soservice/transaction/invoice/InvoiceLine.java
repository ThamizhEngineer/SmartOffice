package com.ss.smartoffice.soservice.transaction.invoice;

import java.math.BigDecimal;
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

@Table(name = "t_invoice_line")
public class InvoiceLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String invoiceHdrId;
	private String itemNo;
	private String itemId;
	private String itemHsnOrSac;
	private String itemType;
	private String itemName;
	private String itemDescription;
	private String itemNotes;
	private Integer itemQty;
	private Float itemRate;
	private String itemPer;
	private String isDiscount;
	private String discountPercentage;
	private Float igst;
	private Float cgst;
	private Float sgst;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal igstTaxAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal cgstTaxAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal sgstTaxAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal lineDiscountAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemTotal;
	private String iInvoiceHdrId;
	private String iInvoiceLineId;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemTaxAmount;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal itemBeforeTaxAmount;
	
	public InvoiceLine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvoiceLine(Integer id, String invoiceHdrId, String itemNo, String itemId, String itemHsnOrSac,
			String itemType, String itemName, String itemDescription, String itemNotes, Integer itemQty, Float itemRate,
			String itemPer, String isDiscount, String discountPercentage, Float igst, Float cgst, Float sgst,
			BigDecimal igstTaxAmt, BigDecimal cgstTaxAmt, BigDecimal sgstTaxAmt, BigDecimal lineDiscountAmt,
			BigDecimal itemTotal, String iInvoiceHdrId, String iInvoiceLineId, String createdBy,
			LocalDateTime createdDate, String modifiedBy, LocalDateTime modifiedDate, BigDecimal itemTaxAmount,
			BigDecimal itemBeforeTaxAmount) {
		super();
		this.id = id;
		this.invoiceHdrId = invoiceHdrId;
		this.itemNo = itemNo;
		this.itemId = itemId;
		this.itemHsnOrSac = itemHsnOrSac;
		this.itemType = itemType;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemNotes = itemNotes;
		this.itemQty = itemQty;
		this.itemRate = itemRate;
		this.itemPer = itemPer;
		this.isDiscount = isDiscount;
		this.discountPercentage = discountPercentage;
		this.igst = igst;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igstTaxAmt = igstTaxAmt;
		this.cgstTaxAmt = cgstTaxAmt;
		this.sgstTaxAmt = sgstTaxAmt;
		this.lineDiscountAmt = lineDiscountAmt;
		this.itemTotal = itemTotal;
		this.iInvoiceHdrId = iInvoiceHdrId;
		this.iInvoiceLineId = iInvoiceLineId;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.itemTaxAmount = itemTaxAmount;
		this.itemBeforeTaxAmount = itemBeforeTaxAmount;
	}

	@Override
	public String toString() {
		return "InvoiceLine [id=" + id + ", invoiceHdrId=" + invoiceHdrId + ", itemNo=" + itemNo + ", itemId=" + itemId
				+ ", itemHsnOrSac=" + itemHsnOrSac + ", itemType=" + itemType + ", itemName=" + itemName
				+ ", itemDescription=" + itemDescription + ", itemNotes=" + itemNotes + ", itemQty=" + itemQty
				+ ", itemRate=" + itemRate + ", itemPer=" + itemPer + ", isDiscount=" + isDiscount
				+ ", discountPercentage=" + discountPercentage + ", igst=" + igst + ", cgst=" + cgst + ", sgst=" + sgst
				+ ", igstTaxAmt=" + igstTaxAmt + ", cgstTaxAmt=" + cgstTaxAmt + ", sgstTaxAmt=" + sgstTaxAmt
				+ ", lineDiscountAmt=" + lineDiscountAmt + ", itemTotal=" + itemTotal + ", iInvoiceHdrId="
				+ iInvoiceHdrId + ", iInvoiceLineId=" + iInvoiceLineId + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", itemTaxAmount="
				+ itemTaxAmount + ", itemBeforeTaxAmount=" + itemBeforeTaxAmount + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceHdrId() {
		return invoiceHdrId;
	}

	public void setInvoiceHdrId(String invoiceHdrId) {
		this.invoiceHdrId = invoiceHdrId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemHsnOrSac() {
		return itemHsnOrSac;
	}

	public void setItemHsnOrSac(String itemHsnOrSac) {
		this.itemHsnOrSac = itemHsnOrSac;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemNotes() {
		return itemNotes;
	}

	public void setItemNotes(String itemNotes) {
		this.itemNotes = itemNotes;
	}

	public Integer getItemQty() {
		return itemQty;
	}

	public void setItemQty(Integer itemQty) {
		this.itemQty = itemQty;
	}

	public Float getItemRate() {
		return itemRate;
	}

	public void setItemRate(Float itemRate) {
		this.itemRate = itemRate;
	}

	public String getItemPer() {
		return itemPer;
	}

	public void setItemPer(String itemPer) {
		this.itemPer = itemPer;
	}

	public String getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(String isDiscount) {
		this.isDiscount = isDiscount;
	}

	public String getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public Float getIgst() {
		return igst;
	}

	public void setIgst(Float igst) {
		this.igst = igst;
	}

	public Float getCgst() {
		return cgst;
	}

	public void setCgst(Float cgst) {
		this.cgst = cgst;
	}

	public Float getSgst() {
		return sgst;
	}

	public void setSgst(Float sgst) {
		this.sgst = sgst;
	}

	public BigDecimal getIgstTaxAmt() {
		return igstTaxAmt;
	}

	public void setIgstTaxAmt(BigDecimal igstTaxAmt) {
		this.igstTaxAmt = igstTaxAmt;
	}

	public BigDecimal getCgstTaxAmt() {
		return cgstTaxAmt;
	}

	public void setCgstTaxAmt(BigDecimal cgstTaxAmt) {
		this.cgstTaxAmt = cgstTaxAmt;
	}

	public BigDecimal getSgstTaxAmt() {
		return sgstTaxAmt;
	}

	public void setSgstTaxAmt(BigDecimal sgstTaxAmt) {
		this.sgstTaxAmt = sgstTaxAmt;
	}

	public BigDecimal getLineDiscountAmt() {
		return lineDiscountAmt;
	}

	public void setLineDiscountAmt(BigDecimal lineDiscountAmt) {
		this.lineDiscountAmt = lineDiscountAmt;
	}

	public BigDecimal getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getiInvoiceHdrId() {
		return iInvoiceHdrId;
	}

	public void setiInvoiceHdrId(String iInvoiceHdrId) {
		this.iInvoiceHdrId = iInvoiceHdrId;
	}

	public String getiInvoiceLineId() {
		return iInvoiceLineId;
	}

	public void setiInvoiceLineId(String iInvoiceLineId) {
		this.iInvoiceLineId = iInvoiceLineId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public BigDecimal getItemTaxAmount() {
		return itemTaxAmount;
	}

	public void setItemTaxAmount(BigDecimal itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
	}

	public BigDecimal getItemBeforeTaxAmount() {
		return itemBeforeTaxAmount;
	}

	public void setItemBeforeTaxAmount(BigDecimal itemBeforeTaxAmount) {
		this.itemBeforeTaxAmount = itemBeforeTaxAmount;
	}

		
}
