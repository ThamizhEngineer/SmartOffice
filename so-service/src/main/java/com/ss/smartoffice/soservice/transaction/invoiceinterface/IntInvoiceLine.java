package com.ss.smartoffice.soservice.transaction.invoiceinterface;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity

@Table(name = "i_invoice_line")
public class IntInvoiceLine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String iInvoiceHdrId;
	private String invoiceNo;
	private String invoiceDate;
	private String saleOrderId;
	private String saleOrderCode;
	private String deliveryNote;
	private String deliveryNoteDate;
	private String termsOfPayment;
	private String clientRef;
	private String clientId;
	private String otherReference;
	private String buyerOrderNo;
	private String dated;
	private String pmtDueDt;
	private String despatchDocumentNo;
	private String despatchedThrough;
	private String destination;
	private String country;
	private String termsOfDelivery;
	private String buyerName;
	private String buyerEmail;
	private String buyerGstinOrUin;
	private String buyerPlaceOfSupply;
	private String buyerState;
	private String buyerStateCode;
	private String buyerAddress;
	private String buyerContact;
	private String consigneeName;
	private String consigneeGstinOrUin;
	private String consigneePlaceOfSupply;
	private String consigneeState;
	private String consigneeStateCode;
	private String consigneeAddress;
	private String consigneeContact;
	private String igstTaxAmt;
	private String cgstTaxAmt;
	private String sgstTaxAmt;
	private String totalTaxAmt;
	private String totalQty;
	private String subTotal;
	private String amtSymbol;
	private String amtAbbri;
	private String amountChargeable;
	private String companyPanOrIecCode;
	private String buyerVatTin;
	private String buyerServiceTaxNo;
	private String totalDiscountPercentage;
	private String totalDiscountAmt;
	private String shippingCharges;
	private String declaration;
	private String itemNo;
	private String itemId;
	private String itemType;
	private String itemName;
	private String itemDescription;
	private String itemQty;
	private String itemRate;
	private String itemUnit;
	private String itemIsDiscount;
	private String itemDiscountPercentage;
	private String itemLineDiscountAmt;
	private String itemIgst;
	private String itemCgst;
	private String itemSgst;
	private String itemIgstTaxAmt;
	private String itemCgstTaxAmt;
	private String itemSgstTaxAmt;
	private String itemTotal;
	private String itemHsnOrSac;
	private String createdBy;
	private LocalDateTime createdDate;
	private String modifiedBy;
	private LocalDateTime modifiedDate;
	private String isError;
	private String errorMessage;
	private String isValid;
	private String status;
	
	public IntInvoiceLine() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IntInvoiceLine(Integer id, String iInvoiceHdrId, String invoiceNo, String invoiceDate, String saleOrderId,
			String saleOrderCode, String deliveryNote, String deliveryNoteDate, String termsOfPayment, String clientRef,
			String clientId, String otherReference, String buyerOrderNo, String dated, String pmtDueDt,
			String despatchDocumentNo, String despatchedThrough, String destination, String country,
			String termsOfDelivery, String buyerName, String buyerEmail, String buyerGstinOrUin,
			String buyerPlaceOfSupply, String buyerState, String buyerStateCode, String buyerAddress,
			String buyerContact, String consigneeName, String consigneeGstinOrUin, String consigneePlaceOfSupply,
			String consigneeState, String consigneeStateCode, String consigneeAddress, String consigneeContact,
			String igstTaxAmt, String cgstTaxAmt, String sgstTaxAmt, String totalTaxAmt, String totalQty,
			String subTotal, String amtSymbol, String amtAbbri, String amountChargeable, String companyPanOrIecCode,
			String buyerVatTin, String buyerServiceTaxNo, String totalDiscountPercentage, String totalDiscountAmt,
			String shippingCharges, String declaration, String itemNo, String itemId, String itemType, String itemName,
			String itemDescription, String itemQty, String itemRate, String itemUnit, String itemIsDiscount,
			String itemDiscountPercentage, String itemLineDiscountAmt, String itemIgst, String itemCgst,
			String itemSgst, String itemIgstTaxAmt, String itemCgstTaxAmt, String itemSgstTaxAmt, String itemTotal,
			String itemHsnOrSac, String createdBy, LocalDateTime createdDate, String modifiedBy,
			LocalDateTime modifiedDate, String isError, String errorMessage, String isValid, String status) {
		super();
		this.id = id;
		this.iInvoiceHdrId = iInvoiceHdrId;
		this.invoiceNo = invoiceNo;
		this.invoiceDate = invoiceDate;
		this.saleOrderId = saleOrderId;
		this.saleOrderCode = saleOrderCode;
		this.deliveryNote = deliveryNote;
		this.deliveryNoteDate = deliveryNoteDate;
		this.termsOfPayment = termsOfPayment;
		this.clientRef = clientRef;
		this.clientId = clientId;
		this.otherReference = otherReference;
		this.buyerOrderNo = buyerOrderNo;
		this.dated = dated;
		this.pmtDueDt = pmtDueDt;
		this.despatchDocumentNo = despatchDocumentNo;
		this.despatchedThrough = despatchedThrough;
		this.destination = destination;
		this.country = country;
		this.termsOfDelivery = termsOfDelivery;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.buyerGstinOrUin = buyerGstinOrUin;
		this.buyerPlaceOfSupply = buyerPlaceOfSupply;
		this.buyerState = buyerState;
		this.buyerStateCode = buyerStateCode;
		this.buyerAddress = buyerAddress;
		this.buyerContact = buyerContact;
		this.consigneeName = consigneeName;
		this.consigneeGstinOrUin = consigneeGstinOrUin;
		this.consigneePlaceOfSupply = consigneePlaceOfSupply;
		this.consigneeState = consigneeState;
		this.consigneeStateCode = consigneeStateCode;
		this.consigneeAddress = consigneeAddress;
		this.consigneeContact = consigneeContact;
		this.igstTaxAmt = igstTaxAmt;
		this.cgstTaxAmt = cgstTaxAmt;
		this.sgstTaxAmt = sgstTaxAmt;
		this.totalTaxAmt = totalTaxAmt;
		this.totalQty = totalQty;
		this.subTotal = subTotal;
		this.amtSymbol = amtSymbol;
		this.amtAbbri = amtAbbri;
		this.amountChargeable = amountChargeable;
		this.companyPanOrIecCode = companyPanOrIecCode;
		this.buyerVatTin = buyerVatTin;
		this.buyerServiceTaxNo = buyerServiceTaxNo;
		this.totalDiscountPercentage = totalDiscountPercentage;
		this.totalDiscountAmt = totalDiscountAmt;
		this.shippingCharges = shippingCharges;
		this.declaration = declaration;
		this.itemNo = itemNo;
		this.itemId = itemId;
		this.itemType = itemType;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
		this.itemQty = itemQty;
		this.itemRate = itemRate;
		this.itemUnit = itemUnit;
		this.itemIsDiscount = itemIsDiscount;
		this.itemDiscountPercentage = itemDiscountPercentage;
		this.itemLineDiscountAmt = itemLineDiscountAmt;
		this.itemIgst = itemIgst;
		this.itemCgst = itemCgst;
		this.itemSgst = itemSgst;
		this.itemIgstTaxAmt = itemIgstTaxAmt;
		this.itemCgstTaxAmt = itemCgstTaxAmt;
		this.itemSgstTaxAmt = itemSgstTaxAmt;
		this.itemTotal = itemTotal;
		this.itemHsnOrSac = itemHsnOrSac;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isError = isError;
		this.errorMessage = errorMessage;
		this.isValid = isValid;
		this.status = status;
	}

	@Override
	public String toString() {
		return "IntInvoiceLine [id=" + id + ", iInvoiceHdrId=" + iInvoiceHdrId + ", invoiceNo=" + invoiceNo
				+ ", invoiceDate=" + invoiceDate + ", saleOrderId=" + saleOrderId + ", saleOrderCode=" + saleOrderCode
				+ ", deliveryNote=" + deliveryNote + ", deliveryNoteDate=" + deliveryNoteDate + ", termsOfPayment="
				+ termsOfPayment + ", clientRef=" + clientRef + ", clientId=" + clientId + ", otherReference="
				+ otherReference + ", buyerOrderNo=" + buyerOrderNo + ", dated=" + dated + ", pmtDueDt=" + pmtDueDt
				+ ", despatchDocumentNo=" + despatchDocumentNo + ", despatchedThrough=" + despatchedThrough
				+ ", destination=" + destination + ", country=" + country + ", termsOfDelivery=" + termsOfDelivery
				+ ", buyerName=" + buyerName + ", buyerEmail=" + buyerEmail + ", buyerGstinOrUin=" + buyerGstinOrUin
				+ ", buyerPlaceOfSupply=" + buyerPlaceOfSupply + ", buyerState=" + buyerState + ", buyerStateCode="
				+ buyerStateCode + ", buyerAddress=" + buyerAddress + ", buyerContact=" + buyerContact
				+ ", consigneeName=" + consigneeName + ", consigneeGstinOrUin=" + consigneeGstinOrUin
				+ ", consigneePlaceOfSupply=" + consigneePlaceOfSupply + ", consigneeState=" + consigneeState
				+ ", consigneeStateCode=" + consigneeStateCode + ", consigneeAddress=" + consigneeAddress
				+ ", consigneeContact=" + consigneeContact + ", igstTaxAmt=" + igstTaxAmt + ", cgstTaxAmt=" + cgstTaxAmt
				+ ", sgstTaxAmt=" + sgstTaxAmt + ", totalTaxAmt=" + totalTaxAmt + ", totalQty=" + totalQty
				+ ", subTotal=" + subTotal + ", amtSymbol=" + amtSymbol + ", amtAbbri=" + amtAbbri
				+ ", amountChargeable=" + amountChargeable + ", companyPanOrIecCode=" + companyPanOrIecCode
				+ ", buyerVatTin=" + buyerVatTin + ", buyerServiceTaxNo=" + buyerServiceTaxNo
				+ ", totalDiscountPercentage=" + totalDiscountPercentage + ", totalDiscountAmt=" + totalDiscountAmt
				+ ", shippingCharges=" + shippingCharges + ", declaration=" + declaration + ", itemNo=" + itemNo
				+ ", itemId=" + itemId + ", itemType=" + itemType + ", itemName=" + itemName + ", itemDescription="
				+ itemDescription + ", itemQty=" + itemQty + ", itemRate=" + itemRate + ", itemUnit=" + itemUnit
				+ ", itemIsDiscount=" + itemIsDiscount + ", itemDiscountPercentage=" + itemDiscountPercentage
				+ ", itemLineDiscountAmt=" + itemLineDiscountAmt + ", itemIgst=" + itemIgst + ", itemCgst=" + itemCgst
				+ ", itemSgst=" + itemSgst + ", itemIgstTaxAmt=" + itemIgstTaxAmt + ", itemCgstTaxAmt=" + itemCgstTaxAmt
				+ ", itemSgstTaxAmt=" + itemSgstTaxAmt + ", itemTotal=" + itemTotal + ", itemHsnOrSac=" + itemHsnOrSac
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy
				+ ", modifiedDate=" + modifiedDate + ", isError=" + isError + ", errorMessage=" + errorMessage
				+ ", isValid=" + isValid + ", status=" + status + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getiInvoiceHdrId() {
		return iInvoiceHdrId;
	}

	public void setiInvoiceHdrId(String iInvoiceHdrId) {
		this.iInvoiceHdrId = iInvoiceHdrId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getSaleOrderId() {
		return saleOrderId;
	}

	public void setSaleOrderId(String saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public String getDeliveryNoteDate() {
		return deliveryNoteDate;
	}

	public void setDeliveryNoteDate(String deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
	}

	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
	}
	
	public String getPmtDueDt() {
		return pmtDueDt;
	}

	public void setPmtDueDt(String pmtDueDt) {
		this.pmtDueDt = pmtDueDt;
	}

	public String getClientRef() {
		return clientRef;
	}

	public void setClientRef(String clientRef) {
		this.clientRef = clientRef;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getOtherReference() {
		return otherReference;
	}

	public void setOtherReference(String otherReference) {
		this.otherReference = otherReference;
	}

	public String getBuyerOrderNo() {
		return buyerOrderNo;
	}

	public void setBuyerOrderNo(String buyerOrderNo) {
		this.buyerOrderNo = buyerOrderNo;
	}

	public String getDated() {
		return dated;
	}

	public void setDated(String dated) {
		this.dated = dated;
	}

	public String getDespatchDocumentNo() {
		return despatchDocumentNo;
	}

	public void setDespatchDocumentNo(String despatchDocumentNo) {
		this.despatchDocumentNo = despatchDocumentNo;
	}

	public String getDespatchedThrough() {
		return despatchedThrough;
	}

	public void setDespatchedThrough(String despatchedThrough) {
		this.despatchedThrough = despatchedThrough;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getTermsOfDelivery() {
		return termsOfDelivery;
	}

	public void setTermsOfDelivery(String termsOfDelivery) {
		this.termsOfDelivery = termsOfDelivery;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getBuyerGstinOrUin() {
		return buyerGstinOrUin;
	}

	public void setBuyerGstinOrUin(String buyerGstinOrUin) {
		this.buyerGstinOrUin = buyerGstinOrUin;
	}

	public String getBuyerPlaceOfSupply() {
		return buyerPlaceOfSupply;
	}

	public void setBuyerPlaceOfSupply(String buyerPlaceOfSupply) {
		this.buyerPlaceOfSupply = buyerPlaceOfSupply;
	}

	public String getBuyerState() {
		return buyerState;
	}

	public void setBuyerState(String buyerState) {
		this.buyerState = buyerState;
	}

	public String getBuyerStateCode() {
		return buyerStateCode;
	}

	public void setBuyerStateCode(String buyerStateCode) {
		this.buyerStateCode = buyerStateCode;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public String getBuyerContact() {
		return buyerContact;
	}

	public void setBuyerContact(String buyerContact) {
		this.buyerContact = buyerContact;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeGstinOrUin() {
		return consigneeGstinOrUin;
	}

	public void setConsigneeGstinOrUin(String consigneeGstinOrUin) {
		this.consigneeGstinOrUin = consigneeGstinOrUin;
	}

	public String getConsigneePlaceOfSupply() {
		return consigneePlaceOfSupply;
	}

	public void setConsigneePlaceOfSupply(String consigneePlaceOfSupply) {
		this.consigneePlaceOfSupply = consigneePlaceOfSupply;
	}

	public String getConsigneeState() {
		return consigneeState;
	}

	public void setConsigneeState(String consigneeState) {
		this.consigneeState = consigneeState;
	}

	public String getConsigneeStateCode() {
		return consigneeStateCode;
	}

	public void setConsigneeStateCode(String consigneeStateCode) {
		this.consigneeStateCode = consigneeStateCode;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeContact() {
		return consigneeContact;
	}

	public void setConsigneeContact(String consigneeContact) {
		this.consigneeContact = consigneeContact;
	}

	public String getIgstTaxAmt() {
		return igstTaxAmt;
	}

	public void setIgstTaxAmt(String igstTaxAmt) {
		this.igstTaxAmt = igstTaxAmt;
	}

	public String getCgstTaxAmt() {
		return cgstTaxAmt;
	}

	public void setCgstTaxAmt(String cgstTaxAmt) {
		this.cgstTaxAmt = cgstTaxAmt;
	}

	public String getSgstTaxAmt() {
		return sgstTaxAmt;
	}

	public void setSgstTaxAmt(String sgstTaxAmt) {
		this.sgstTaxAmt = sgstTaxAmt;
	}

	public String getTotalTaxAmt() {
		return totalTaxAmt;
	}

	public void setTotalTaxAmt(String totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}

	public String getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(String totalQty) {
		this.totalQty = totalQty;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getAmtSymbol() {
		return amtSymbol;
	}

	public void setAmtSymbol(String amtSymbol) {
		this.amtSymbol = amtSymbol;
	}

	public String getAmtAbbri() {
		return amtAbbri;
	}

	public void setAmtAbbri(String amtAbbri) {
		this.amtAbbri = amtAbbri;
	}

	public String getAmountChargeable() {
		return amountChargeable;
	}

	public void setAmountChargeable(String amountChargeable) {
		this.amountChargeable = amountChargeable;
	}

	public String getCompanyPanOrIecCode() {
		return companyPanOrIecCode;
	}

	public void setCompanyPanOrIecCode(String companyPanOrIecCode) {
		this.companyPanOrIecCode = companyPanOrIecCode;
	}

	public String getBuyerVatTin() {
		return buyerVatTin;
	}

	public void setBuyerVatTin(String buyerVatTin) {
		this.buyerVatTin = buyerVatTin;
	}

	public String getBuyerServiceTaxNo() {
		return buyerServiceTaxNo;
	}

	public void setBuyerServiceTaxNo(String buyerServiceTaxNo) {
		this.buyerServiceTaxNo = buyerServiceTaxNo;
	}

	public String getTotalDiscountPercentage() {
		return totalDiscountPercentage;
	}

	public void setTotalDiscountPercentage(String totalDiscountPercentage) {
		this.totalDiscountPercentage = totalDiscountPercentage;
	}

	public String getTotalDiscountAmt() {
		return totalDiscountAmt;
	}

	public void setTotalDiscountAmt(String totalDiscountAmt) {
		this.totalDiscountAmt = totalDiscountAmt;
	}

	public String getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(String shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
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

	public String getItemQty() {
		return itemQty;
	}

	public void setItemQty(String itemQty) {
		this.itemQty = itemQty;
	}

	public String getItemRate() {
		return itemRate;
	}

	public void setItemRate(String itemRate) {
		this.itemRate = itemRate;
	}

	public String getItemUnit() {
		return itemUnit;
	}

	public void setItemUnit(String itemUnit) {
		this.itemUnit = itemUnit;
	}

	public String getItemIsDiscount() {
		return itemIsDiscount;
	}

	public void setItemIsDiscount(String itemIsDiscount) {
		this.itemIsDiscount = itemIsDiscount;
	}

	public String getItemDiscountPercentage() {
		return itemDiscountPercentage;
	}

	public void setItemDiscountPercentage(String itemDiscountPercentage) {
		this.itemDiscountPercentage = itemDiscountPercentage;
	}

	public String getItemLineDiscountAmt() {
		return itemLineDiscountAmt;
	}

	public void setItemLineDiscountAmt(String itemLineDiscountAmt) {
		this.itemLineDiscountAmt = itemLineDiscountAmt;
	}

	public String getItemIgst() {
		return itemIgst;
	}

	public void setItemIgst(String itemIgst) {
		this.itemIgst = itemIgst;
	}

	public String getItemCgst() {
		return itemCgst;
	}

	public void setItemCgst(String itemCgst) {
		this.itemCgst = itemCgst;
	}

	public String getItemSgst() {
		return itemSgst;
	}

	public void setItemSgst(String itemSgst) {
		this.itemSgst = itemSgst;
	}

	public String getItemIgstTaxAmt() {
		return itemIgstTaxAmt;
	}

	public void setItemIgstTaxAmt(String itemIgstTaxAmt) {
		this.itemIgstTaxAmt = itemIgstTaxAmt;
	}

	public String getItemCgstTaxAmt() {
		return itemCgstTaxAmt;
	}

	public void setItemCgstTaxAmt(String itemCgstTaxAmt) {
		this.itemCgstTaxAmt = itemCgstTaxAmt;
	}

	public String getItemSgstTaxAmt() {
		return itemSgstTaxAmt;
	}

	public void setItemSgstTaxAmt(String itemSgstTaxAmt) {
		this.itemSgstTaxAmt = itemSgstTaxAmt;
	}

	public String getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(String itemTotal) {
		this.itemTotal = itemTotal;
	}

	public String getItemHsnOrSac() {
		return itemHsnOrSac;
	}

	public void setItemHsnOrSac(String itemHsnOrSac) {
		this.itemHsnOrSac = itemHsnOrSac;
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

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

		
}
