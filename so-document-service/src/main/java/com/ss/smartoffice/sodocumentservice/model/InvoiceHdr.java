package com.ss.smartoffice.sodocumentservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@Data
public class InvoiceHdr {

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
	private BigDecimal beforeTaxSubTotalAmt;
	private BigDecimal igstTaxAmt;
	private BigDecimal cgstTaxAmt;
	private BigDecimal sgstTaxAmt;
	private BigDecimal totalTaxAmt;
	private Integer totalQty;
	private BigDecimal afterTaxSubTotalAmt;
	private String totalDiscountPercentage;
	private BigDecimal totalDiscountAmt;
	private BigDecimal shippingAmt;
	private BigDecimal finalPayableAmt;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime pmtDueDt;
	private String companyPanOrIecCode;
	private String buyerVatTin;
	private String buyerServiceTaxNo;
	private String buyerHasGst;
	private String buyerHasOverseas;
	private String declaration;
	@Column(name = "i_invoice_hdr_id")
	private String iInvoiceHdrId;
	private BigDecimal paidAmt;
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
	private BigDecimal invoiceWithoutExAmt;
	private String transactionCurrId;
	private String transactionCurrCode;
	private String transactionCurrName;
	private String transactionCurrSymbol; 
	private String transactionCurrDecimal;
    private String companyCurrId;
	private String companyCurrSymbol;
	private String companyCurrCode;
	private String companyCurrName;
	private String companyCurrDecimal;
	private String isHandledGst;
	
}
