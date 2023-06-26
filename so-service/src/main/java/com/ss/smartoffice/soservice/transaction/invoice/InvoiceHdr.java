package com.ss.smartoffice.soservice.transaction.invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity

@Table(name = "t_invoice_hdr")
public class InvoiceHdr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String invoiceCode;
	private String saleOrderId;
	private String saleOrderCode;
	private String refInvoiceNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invoiceDate;
	private String deliveryNote;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime deliveryNoteDate;
	private String termsOfPayment;
	private String clientRef;
	private String clientId;
	private String otherReference;
	private String buyerOrderNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dated;
	private String despatchDocumentNo;
	private String invoiceNotes;
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
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal beforeTaxSubTotalAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal igstTaxAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal cgstTaxAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal sgstTaxAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal totalTaxAmt;
	private Integer totalQty;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal afterTaxSubTotalAmt;
	private String totalDiscountPercentage;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal totalDiscountAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal shippingAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal finalPayableAmt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime pmtDueDt;
	private String companyPanOrIecCode;
	private String buyerVatTin;
	private String buyerServiceTaxNo;
	private String buyerHasGst;
	private String buyerHasOverseas="N";
	private String declaration;
	@Column(name = "i_invoice_hdr_id")
	private String iInvoiceHdrId;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal paidAmt;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal balanceAmt;
	private String createdBy;
	@CreationTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;
	private String modifiedBy;
	@UpdateTimestamp
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;
	@Transient
	private List<InvoiceLine> invoiceLines;
	private String pdfDocId;
	private String docId;
	
	private String invoiceStatus;
	private String invoiceCreatedBy;
	private String invoiceCreatedByEmpName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime invoiceCreatedDt;
	private String dirGroupId;
	private String dirGroupName;
	private String approvedBy;
	private String approvedByEmpName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime approvedDt;
	private String approvalComments;
	private String voidedBy;
	private String voidedByEmpName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime voidedDt;
	
	
	private String companyName;
	private String companyAddress;
	private String companyGstNo;
	private String companyStateName;
	private String companyCode;
	private String companyCin;
	private String companyLogo;
	private String companyPhoneNo;

	private String companyAccountNo;
	private String companyIfscCode;
	private String companyAccountHolderName;
	private String companySwiftCode;
	private String companyBankName;
	private String companyBankBranch;
	private float exchangeRate =1;
    @Column(columnDefinition="decimal", precision=20, scale=4)
	private BigDecimal invoiceWithoutExAmt;
	private String transactionCurrId;
	private String transactionCurrCode;
	private String transactionCurrName;
	private String transactionCurrSymbol; 
	@Formula("(SELECT sc.curr_decimal from s_currency sc where id =transaction_curr_id)")
	private String transactionCurrDecimal;
	
    private String companyCurrId;
	private String companyCurrSymbol;
	private String companyCurrCode;
	private String companyCurrName;
	@Formula("(SELECT sc.curr_decimal from s_currency sc where id =company_curr_id)")
	private String companyCurrDecimal;
	
	private String isHandledGst;
	
	public InvoiceHdr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public InvoiceHdr(Integer id, String invoiceCode, String saleOrderId, String saleOrderCode, String refInvoiceNo,
			LocalDateTime invoiceDate, String deliveryNote, LocalDateTime deliveryNoteDate, String termsOfPayment,
			String clientRef, String clientId, String otherReference, String buyerOrderNo, LocalDateTime dated,
			String despatchDocumentNo, String invoiceNotes, String despatchedThrough, String destination,
			String country, String termsOfDelivery, String buyerName, String buyerEmail, String buyerGstinOrUin,
			String buyerPlaceOfSupply, String buyerState, String buyerStateCode, String buyerAddress,
			String buyerContact, String consigneeName, String consigneeGstinOrUin, String consigneePlaceOfSupply,
			String consigneeState, String consigneeStateCode, String consigneeAddress, String consigneeContact,
			BigDecimal beforeTaxSubTotalAmt, BigDecimal igstTaxAmt, BigDecimal cgstTaxAmt, BigDecimal sgstTaxAmt,
			BigDecimal totalTaxAmt, Integer totalQty, BigDecimal afterTaxSubTotalAmt, String totalDiscountPercentage,
			BigDecimal totalDiscountAmt, BigDecimal shippingAmt, BigDecimal finalPayableAmt, LocalDateTime pmtDueDt,
			String companyPanOrIecCode, String buyerVatTin, String buyerServiceTaxNo, String buyerHasGst,
			String buyerHasOverseas, String declaration, String iInvoiceHdrId, BigDecimal paidAmt,
			BigDecimal balanceAmt, String createdBy, LocalDateTime createdDate, String modifiedBy,
			LocalDateTime modifiedDate, List<InvoiceLine> invoiceLines, String pdfDocId, String docId,
			String invoiceStatus, String invoiceCreatedBy, String invoiceCreatedByEmpName,
			LocalDateTime invoiceCreatedDt, String dirGroupId, String dirGroupName, String approvedBy,
			String approvedByEmpName, LocalDateTime approvedDt, String approvalComments, String voidedBy,
			String voidedByEmpName, LocalDateTime voidedDt, String companyName, String companyAddress,
			String companyGstNo, String companyStateName, String companyCode, String companyCin, String companyLogo,
			String companyPhoneNo, String companyAccountNo, String companyIfscCode, String companyAccountHolderName,
			String companySwiftCode, String companyBankName, String companyBankBranch, float exchangeRate,
			BigDecimal invoiceWithoutExAmt, String transactionCurrId, String transactionCurrCode,
			String transactionCurrName, String transactionCurrSymbol, String transactionCurrDecimal,
			String companyCurrId, String companyCurrSymbol, String companyCurrCode, String companyCurrName,
			String companyCurrDecimal, String isHandledGst) {
		super();
		this.id = id;
		this.invoiceCode = invoiceCode;
		this.saleOrderId = saleOrderId;
		this.saleOrderCode = saleOrderCode;
		this.refInvoiceNo = refInvoiceNo;
		this.invoiceDate = invoiceDate;
		this.deliveryNote = deliveryNote;
		this.deliveryNoteDate = deliveryNoteDate;
		this.termsOfPayment = termsOfPayment;
		this.clientRef = clientRef;
		this.clientId = clientId;
		this.otherReference = otherReference;
		this.buyerOrderNo = buyerOrderNo;
		this.dated = dated;
		this.despatchDocumentNo = despatchDocumentNo;
		this.invoiceNotes = invoiceNotes;
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
		this.beforeTaxSubTotalAmt = beforeTaxSubTotalAmt;
		this.igstTaxAmt = igstTaxAmt;
		this.cgstTaxAmt = cgstTaxAmt;
		this.sgstTaxAmt = sgstTaxAmt;
		this.totalTaxAmt = totalTaxAmt;
		this.totalQty = totalQty;
		this.afterTaxSubTotalAmt = afterTaxSubTotalAmt;
		this.totalDiscountPercentage = totalDiscountPercentage;
		this.totalDiscountAmt = totalDiscountAmt;
		this.shippingAmt = shippingAmt;
		this.finalPayableAmt = finalPayableAmt;
		this.pmtDueDt = pmtDueDt;
		this.companyPanOrIecCode = companyPanOrIecCode;
		this.buyerVatTin = buyerVatTin;
		this.buyerServiceTaxNo = buyerServiceTaxNo;
		this.buyerHasGst = buyerHasGst;
		this.buyerHasOverseas = buyerHasOverseas;
		this.declaration = declaration;
		this.iInvoiceHdrId = iInvoiceHdrId;
		this.paidAmt = paidAmt;
		this.balanceAmt = balanceAmt;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.invoiceLines = invoiceLines;
		this.pdfDocId = pdfDocId;
		this.docId = docId;
		this.invoiceStatus = invoiceStatus;
		this.invoiceCreatedBy = invoiceCreatedBy;
		this.invoiceCreatedByEmpName = invoiceCreatedByEmpName;
		this.invoiceCreatedDt = invoiceCreatedDt;
		this.dirGroupId = dirGroupId;
		this.dirGroupName = dirGroupName;
		this.approvedBy = approvedBy;
		this.approvedByEmpName = approvedByEmpName;
		this.approvedDt = approvedDt;
		this.approvalComments = approvalComments;
		this.voidedBy = voidedBy;
		this.voidedByEmpName = voidedByEmpName;
		this.voidedDt = voidedDt;
		this.companyName = companyName;
		this.companyAddress = companyAddress;
		this.companyGstNo = companyGstNo;
		this.companyStateName = companyStateName;
		this.companyCode = companyCode;
		this.companyCin = companyCin;
		this.companyLogo = companyLogo;
		this.companyPhoneNo = companyPhoneNo;
		this.companyAccountNo = companyAccountNo;
		this.companyIfscCode = companyIfscCode;
		this.companyAccountHolderName = companyAccountHolderName;
		this.companySwiftCode = companySwiftCode;
		this.companyBankName = companyBankName;
		this.companyBankBranch = companyBankBranch;
		this.exchangeRate = exchangeRate;
		this.invoiceWithoutExAmt = invoiceWithoutExAmt;
		this.transactionCurrId = transactionCurrId;
		this.transactionCurrCode = transactionCurrCode;
		this.transactionCurrName = transactionCurrName;
		this.transactionCurrSymbol = transactionCurrSymbol;
		this.transactionCurrDecimal = transactionCurrDecimal;
		this.companyCurrId = companyCurrId;
		this.companyCurrSymbol = companyCurrSymbol;
		this.companyCurrCode = companyCurrCode;
		this.companyCurrName = companyCurrName;
		this.companyCurrDecimal = companyCurrDecimal;
		this.isHandledGst = isHandledGst;
	}

	@Override
	public String toString() {
		return "InvoiceHdr [id=" + id + ", invoiceCode=" + invoiceCode + ", saleOrderId=" + saleOrderId
				+ ", saleOrderCode=" + saleOrderCode + ", refInvoiceNo=" + refInvoiceNo + ", invoiceDate=" + invoiceDate
				+ ", deliveryNote=" + deliveryNote + ", deliveryNoteDate=" + deliveryNoteDate + ", termsOfPayment="
				+ termsOfPayment + ", clientRef=" + clientRef + ", clientId=" + clientId + ", otherReference="
				+ otherReference + ", buyerOrderNo=" + buyerOrderNo + ", dated=" + dated + ", despatchDocumentNo="
				+ despatchDocumentNo + ", invoiceNotes=" + invoiceNotes + ", despatchedThrough=" + despatchedThrough
				+ ", destination=" + destination + ", country=" + country + ", termsOfDelivery=" + termsOfDelivery
				+ ", buyerName=" + buyerName + ", buyerEmail=" + buyerEmail + ", buyerGstinOrUin=" + buyerGstinOrUin
				+ ", buyerPlaceOfSupply=" + buyerPlaceOfSupply + ", buyerState=" + buyerState + ", buyerStateCode="
				+ buyerStateCode + ", buyerAddress=" + buyerAddress + ", buyerContact=" + buyerContact
				+ ", consigneeName=" + consigneeName + ", consigneeGstinOrUin=" + consigneeGstinOrUin
				+ ", consigneePlaceOfSupply=" + consigneePlaceOfSupply + ", consigneeState=" + consigneeState
				+ ", consigneeStateCode=" + consigneeStateCode + ", consigneeAddress=" + consigneeAddress
				+ ", consigneeContact=" + consigneeContact + ", beforeTaxSubTotalAmt=" + beforeTaxSubTotalAmt
				+ ", igstTaxAmt=" + igstTaxAmt + ", cgstTaxAmt=" + cgstTaxAmt + ", sgstTaxAmt=" + sgstTaxAmt
				+ ", totalTaxAmt=" + totalTaxAmt + ", totalQty=" + totalQty + ", afterTaxSubTotalAmt="
				+ afterTaxSubTotalAmt + ", totalDiscountPercentage=" + totalDiscountPercentage + ", totalDiscountAmt="
				+ totalDiscountAmt + ", shippingAmt=" + shippingAmt + ", finalPayableAmt=" + finalPayableAmt
				+ ", pmtDueDt=" + pmtDueDt + ", companyPanOrIecCode=" + companyPanOrIecCode + ", buyerVatTin="
				+ buyerVatTin + ", buyerServiceTaxNo=" + buyerServiceTaxNo + ", buyerHasGst=" + buyerHasGst
				+ ", buyerHasOverseas=" + buyerHasOverseas + ", declaration=" + declaration + ", iInvoiceHdrId="
				+ iInvoiceHdrId + ", paidAmt=" + paidAmt + ", balanceAmt=" + balanceAmt + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate
				+ ", invoiceLines=" + invoiceLines + ", pdfDocId=" + pdfDocId + ", docId=" + docId + ", invoiceStatus="
				+ invoiceStatus + ", invoiceCreatedBy=" + invoiceCreatedBy + ", invoiceCreatedByEmpName="
				+ invoiceCreatedByEmpName + ", invoiceCreatedDt=" + invoiceCreatedDt + ", dirGroupId=" + dirGroupId
				+ ", dirGroupName=" + dirGroupName + ", approvedBy=" + approvedBy + ", approvedByEmpName="
				+ approvedByEmpName + ", approvedDt=" + approvedDt + ", approvalComments=" + approvalComments
				+ ", voidedBy=" + voidedBy + ", voidedByEmpName=" + voidedByEmpName + ", voidedDt=" + voidedDt
				+ ", companyName=" + companyName + ", companyAddress=" + companyAddress + ", companyGstNo="
				+ companyGstNo + ", companyStateName=" + companyStateName + ", companyCode=" + companyCode
				+ ", companyCin=" + companyCin + ", companyLogo=" + companyLogo + ", companyPhoneNo=" + companyPhoneNo
				+ ", companyAccountNo=" + companyAccountNo + ", companyIfscCode=" + companyIfscCode
				+ ", companyAccountHolderName=" + companyAccountHolderName + ", companySwiftCode=" + companySwiftCode
				+ ", companyBankName=" + companyBankName + ", companyBankBranch=" + companyBankBranch
				+ ", exchangeRate=" + exchangeRate + ", invoiceWithoutExAmt=" + invoiceWithoutExAmt
				+ ", transactionCurrId=" + transactionCurrId + ", transactionCurrCode=" + transactionCurrCode
				+ ", transactionCurrName=" + transactionCurrName + ", transactionCurrSymbol=" + transactionCurrSymbol
				+ ", transactionCurrDecimal=" + transactionCurrDecimal + ", companyCurrId=" + companyCurrId
				+ ", companyCurrSymbol=" + companyCurrSymbol + ", companyCurrCode=" + companyCurrCode
				+ ", companyCurrName=" + companyCurrName + ", companyCurrDecimal=" + companyCurrDecimal
				+ ", isHandledGst=" + isHandledGst + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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

	public String getRefInvoiceNo() {
		return refInvoiceNo;
	}

	public void setRefInvoiceNo(String refInvoiceNo) {
		this.refInvoiceNo = refInvoiceNo;
	}

	public LocalDateTime getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDateTime invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public LocalDateTime getDeliveryNoteDate() {
		return deliveryNoteDate;
	}

	public void setDeliveryNoteDate(LocalDateTime deliveryNoteDate) {
		this.deliveryNoteDate = deliveryNoteDate;
	}

	public String getTermsOfPayment() {
		return termsOfPayment;
	}

	public void setTermsOfPayment(String termsOfPayment) {
		this.termsOfPayment = termsOfPayment;
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

	public LocalDateTime getDated() {
		return dated;
	}

	public void setDated(LocalDateTime dated) {
		this.dated = dated;
	}

	public String getDespatchDocumentNo() {
		return despatchDocumentNo;
	}

	public void setDespatchDocumentNo(String despatchDocumentNo) {
		this.despatchDocumentNo = despatchDocumentNo;
	}

	public String getInvoiceNotes() {
		return invoiceNotes;
	}

	public void setInvoiceNotes(String invoiceNotes) {
		this.invoiceNotes = invoiceNotes;
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

	public String getBuyerHasGst() {
		return buyerHasGst;
	}

	public void setBuyerHasGst(String buyerHasGst) {
		this.buyerHasGst = buyerHasGst;
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
	
	public String getBuyerHasOverseas() {
		return buyerHasOverseas;
	}

	public void setBuyerHasOverseas(String buyerHasOverseas) {
		this.buyerHasOverseas = buyerHasOverseas;
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

	public BigDecimal getBeforeTaxSubTotalAmt() {
		return beforeTaxSubTotalAmt;
	}

	public void setBeforeTaxSubTotalAmt(BigDecimal beforeTaxSubTotalAmt) {
		this.beforeTaxSubTotalAmt = beforeTaxSubTotalAmt;
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

	public BigDecimal getTotalTaxAmt() {
		return totalTaxAmt;
	}

	public void setTotalTaxAmt(BigDecimal totalTaxAmt) {
		this.totalTaxAmt = totalTaxAmt;
	}

	public Integer getTotalQty() {
		return totalQty;
	}

	public void setTotalQty(Integer totalQty) {
		this.totalQty = totalQty;
	}

	public BigDecimal getAfterTaxSubTotalAmt() {
		return afterTaxSubTotalAmt;
	}

	public void setAfterTaxSubTotalAmt(BigDecimal afterTaxSubTotalAmt) {
		this.afterTaxSubTotalAmt = afterTaxSubTotalAmt;
	}

	public String getTotalDiscountPercentage() {
		return totalDiscountPercentage;
	}

	public void setTotalDiscountPercentage(String totalDiscountPercentage) {
		this.totalDiscountPercentage = totalDiscountPercentage;
	}

	public BigDecimal getTotalDiscountAmt() {
		return totalDiscountAmt;
	}

	public void setTotalDiscountAmt(BigDecimal totalDiscountAmt) {
		this.totalDiscountAmt = totalDiscountAmt;
	}

	public BigDecimal getShippingAmt() {
		return shippingAmt;
	}

	public void setShippingAmt(BigDecimal shippingAmt) {
		this.shippingAmt = shippingAmt;
	}

	public BigDecimal getFinalPayableAmt() {
		return finalPayableAmt;
	}

	public void setFinalPayableAmt(BigDecimal finalPayableAmt) {
		this.finalPayableAmt = finalPayableAmt;
	}

	public LocalDateTime getPmtDueDt() {
		return pmtDueDt;
	}

	public void setPmtDueDt(LocalDateTime pmtDueDt) {
		this.pmtDueDt = pmtDueDt;
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

	public String getDeclaration() {
		return declaration;
	}

	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	public String getiInvoiceHdrId() {
		return iInvoiceHdrId;
	}

	public void setiInvoiceHdrId(String iInvoiceHdrId) {
		this.iInvoiceHdrId = iInvoiceHdrId;
	}

	public BigDecimal getPaidAmt() {
		return paidAmt;
	}

	public void setPaidAmt(BigDecimal paidAmt) {
		this.paidAmt = paidAmt;
	}

	public BigDecimal getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(BigDecimal balanceAmt) {
		this.balanceAmt = balanceAmt;
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

	public List<InvoiceLine> getInvoiceLines() {
		return invoiceLines;
	}

	public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
		this.invoiceLines = invoiceLines;
	}

	public String getPdfDocId() {
		return pdfDocId;
	}

	public void setPdfDocId(String pdfDocId) {
		this.pdfDocId = pdfDocId;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public String getInvoiceStatus() {
		return invoiceStatus;
	}

	public void setInvoiceStatus(String invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}

	public String getInvoiceCreatedBy() {
		return invoiceCreatedBy;
	}

	public void setInvoiceCreatedBy(String invoiceCreatedBy) {
		this.invoiceCreatedBy = invoiceCreatedBy;
	}

	public LocalDateTime getInvoiceCreatedDt() {
		return invoiceCreatedDt;
	}

	public void setInvoiceCreatedDt(LocalDateTime invoiceCreatedDt) {
		this.invoiceCreatedDt = invoiceCreatedDt;
	}

	public String getDirGroupId() {
		return dirGroupId;
	}

	public void setDirGroupId(String dirGroupId) {
		this.dirGroupId = dirGroupId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public LocalDateTime getApprovedDt() {
		return approvedDt;
	}

	public void setApprovedDt(LocalDateTime approvedDt) {
		this.approvedDt = approvedDt;
	}

	public String getApprovalComments() {
		return approvalComments;
	}

	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}

	public String getVoidedBy() {
		return voidedBy;
	}

	public void setVoidedBy(String voidedBy) {
		this.voidedBy = voidedBy;
	}

	public LocalDateTime getVoidedDt() {
		return voidedDt;
	}

	public void setVoidedDt(LocalDateTime voidedDt) {
		this.voidedDt = voidedDt;
	}

	public String getInvoiceCreatedByEmpName() {
		return invoiceCreatedByEmpName;
	}

	public void setInvoiceCreatedByEmpName(String invoiceCreatedByEmpName) {
		this.invoiceCreatedByEmpName = invoiceCreatedByEmpName;
	}

	public String getApprovedByEmpName() {
		return approvedByEmpName;
	}

	public void setApprovedByEmpName(String approvedByEmpName) {
		this.approvedByEmpName = approvedByEmpName;
	}

	public String getVoidedByEmpName() {
		return voidedByEmpName;
	}

	public void setVoidedByEmpName(String voidedByEmpName) {
		this.voidedByEmpName = voidedByEmpName;
	}

	public String getDirGroupName() {
		return dirGroupName;
	}

	public void setDirGroupName(String dirGroupName) {
		this.dirGroupName = dirGroupName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}

	public String getCompanyGstNo() {
		return companyGstNo;
	}

	public void setCompanyGstNo(String companyGstNo) {
		this.companyGstNo = companyGstNo;
	}

	public String getCompanyStateName() {
		return companyStateName;
	}

	public void setCompanyStateName(String companyStateName) {
		this.companyStateName = companyStateName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyCin() {
		return companyCin;
	}

	public void setCompanyCin(String companyCin) {
		this.companyCin = companyCin;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getCompanyPhoneNo() {
		return companyPhoneNo;
	}

	public void setCompanyPhoneNo(String companyPhoneNo) {
		this.companyPhoneNo = companyPhoneNo;
	}

	public String getCompanyAccountNo() {
		return companyAccountNo;
	}

	public void setCompanyAccountNo(String companyAccountNo) {
		this.companyAccountNo = companyAccountNo;
	}

	public String getCompanyIfscCode() {
		return companyIfscCode;
	}

	public void setCompanyIfscCode(String companyIfscCode) {
		this.companyIfscCode = companyIfscCode;
	}

	public String getCompanyAccountHolderName() {
		return companyAccountHolderName;
	}

	public void setCompanyAccountHolderName(String companyAccountHolderName) {
		this.companyAccountHolderName = companyAccountHolderName;
	}

	public String getCompanySwiftCode() {
		return companySwiftCode;
	}

	public void setCompanySwiftCode(String companySwiftCode) {
		this.companySwiftCode = companySwiftCode;
	}

	public String getCompanyBankName() {
		return companyBankName;
	}

	public void setCompanyBankName(String companyBankName) {
		this.companyBankName = companyBankName;
	}

	public String getCompanyBankBranch() {
		return companyBankBranch;
	}

	public void setCompanyBankBranch(String companyBankBranch) {
		this.companyBankBranch = companyBankBranch;
	}

	public String getCompanyCurrId() {
		return companyCurrId;
	}

	public void setCompanyCurrId(String companyCurrId) {
		this.companyCurrId = companyCurrId;
	}

	public String getCompanyCurrSymbol() {
		return companyCurrSymbol;
	}

	public void setCompanyCurrSymbol(String companyCurrSymbol) {
		this.companyCurrSymbol = companyCurrSymbol;
	}

	public String getCompanyCurrCode() {
		return companyCurrCode;
	}

	public void setCompanyCurrCode(String companyCurrCode) {
		this.companyCurrCode = companyCurrCode;
	}

	public String getCompanyCurrName() {
		return companyCurrName;
	}

	public void setCompanyCurrName(String companyCurrName) {
		this.companyCurrName = companyCurrName;
	}

	public float getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(float exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public BigDecimal getInvoiceWithoutExAmt() {
		return invoiceWithoutExAmt;
	}

	public void setInvoiceWithoutExAmt(BigDecimal invoiceWithoutExAmt) {
		this.invoiceWithoutExAmt = invoiceWithoutExAmt;
	}

	public String getTransactionCurrId() {
		return transactionCurrId;
	}

	public void setTransactionCurrId(String transactionCurrId) {
		this.transactionCurrId = transactionCurrId;
	}

	public String getTransactionCurrCode() {
		return transactionCurrCode;
	}

	public void setTransactionCurrCode(String transactionCurrCode) {
		this.transactionCurrCode = transactionCurrCode;
	}

	public String getTransactionCurrName() {
		return transactionCurrName;
	}

	public void setTransactionCurrName(String transactionCurrName) {
		this.transactionCurrName = transactionCurrName;
	}

	public String getTransactionCurrSymbol() {
		return transactionCurrSymbol;
	}

	public void setTransactionCurrSymbol(String transactionCurrSymbol) {
		this.transactionCurrSymbol = transactionCurrSymbol;
	}

	public String getIsHandledGst() {
		return isHandledGst;
	}

	public void setIsHandledGst(String isHandledGst) {
		this.isHandledGst = isHandledGst;
	}

	public String getTransactionCurrDecimal() {
		return transactionCurrDecimal;
	}

	public void setTransactionCurrDecimal(String transactionCurrDecimal) {
		this.transactionCurrDecimal = transactionCurrDecimal;
	}

	public String getCompanyCurrDecimal() {
		return companyCurrDecimal;
	}

	public void setCompanyCurrDecimal(String companyCurrDecimal) {
		this.companyCurrDecimal = companyCurrDecimal;
	}
	
	
}
