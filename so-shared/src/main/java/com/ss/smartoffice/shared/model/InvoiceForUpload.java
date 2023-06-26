package com.ss.smartoffice.shared.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id","invoiceNo","invoiceDate","saleOrderCode","deliveryNote","paymentDueDt","deliveryNoteDate","termsOfPayment","clientRef","clientId","otherReference","buyerOrderNo",
	"dated","despatchDocumentNo","despatchedThrough","destination","country","termsOfDelivery","buyerName","buyerEmail","buyerGstinOrUin",
	"buyerPlaceOfSupply","buyerState","buyerStateCode","buyerAddress","buyerContact","consigneeName","consigneeGstinOrUin","consigneePlaceOfSupply",
	"consigneeState","consigneeStateCode","consigneeAddress","consigneeContact","igstTaxAmt","cgstTaxAmt","sgstTaxAmt","totalTaxAmt","totalQty","subTotal",
	"amtSymbol","amtAbbri","amountChargeable","companyPanOrIecCode","buyerVatTin","buyerServiceTaxNo","totalDiscountPercentage","totalDiscountAmt","shippingCharges",
	"declaration","itemNo","itemType","itemName","itemDescription","itemQty","itemRate","itemUnit","itemIsDiscount","itemDiscountPercentage","itemLineDiscountAmt",
	"itemIgst","itemCgst","itemSgst","itemIgstTaxAmt","itemCgstTaxAmt","itemSgstTaxAmt","itemTotal","itemHsnOrSac"})	
public class InvoiceForUpload {
	@JsonProperty("id")	
	private String id;
	@JsonProperty("invoiceNo")
	private String invoiceNo;
	@JsonProperty("invoiceDate")
	private String invoiceDate;
	@JsonProperty("saleOrderCode")
	private String saleOrderCode;
	@JsonProperty("deliveryNote")
	private String deliveryNote;
	@JsonProperty("deliveryNoteDate")
	private String deliveryNoteDate;
	@JsonProperty("paymentDueDt")
	private String paymentDueDt;
	@JsonProperty("termsOfPayment")
	private String termsOfPayment;
	@JsonProperty("clientRef")
	private String clientRef;
	@JsonProperty("otherReference")
	private String otherReference;
	@JsonProperty("buyerOrderNo")
	private String buyerOrderNo;
	@JsonProperty("dated")
	private String dated;
	@JsonProperty("despatchDocumentNo")
	private String despatchDocumentNo;
	@JsonProperty("despatchedThrough")
	private String despatchedThrough;
	@JsonProperty("destination")
	private String destination;
	@JsonProperty("country")
	private String country;
	@JsonProperty("termsOfDelivery")
	private String termsOfDelivery;
	@JsonProperty("buyerName")
	private String buyerName;
	@JsonProperty("buyerEmail")
	private String buyerEmail;
	@JsonProperty("buyerGstinOrUin")
	private String buyerGstinOrUin;
	@JsonProperty("buyerPlaceOfSupply")
	private String buyerPlaceOfSupply;
	@JsonProperty("buyerState")
	private String buyerState;
	@JsonProperty("buyerStateCode")
	private String buyerStateCode;
	@JsonProperty("buyerAddress")
	private String buyerAddress;
	@JsonProperty("buyerContact")
	private String buyerContact;
	@JsonProperty("consigneeName")
	private String consigneeName;
	@JsonProperty("consigneeGstinOrUin")
	private String consigneeGstinOrUin;
	@JsonProperty("consigneePlaceOfSupply")
	private String consigneePlaceOfSupply;
	@JsonProperty("consigneeState")
	private String consigneeState;
	@JsonProperty("consigneeStateCode")
	private String consigneeStateCode;
	@JsonProperty("consigneeAddress")
	private String consigneeAddress;
	@JsonProperty("consigneeContact")
	private String consigneeContact;
	@JsonProperty("igstTaxAmt")
	private String igstTaxAmt;
	@JsonProperty("cgstTaxAmt")
	private String cgstTaxAmt;
	@JsonProperty("sgstTaxAmt")
	private String sgstTaxAmt;
	@JsonProperty("totalTaxAmt")
	private String totalTaxAmt;
	@JsonProperty("totalQty")
	private String totalQty;
	@JsonProperty("subTotal")
	private String subTotal;
	@JsonProperty("amtSymbol")
	private String amtSymbol;
	@JsonProperty("amtAbbri")
	private String amtAbbri;
	@JsonProperty("amountChargeable")
	private String amountChargeable;
	@JsonProperty("companyPanOrIecCode")
	private String companyPanOrIecCode;
	@JsonProperty("buyerVatTin")
	private String buyerVatTin;
	@JsonProperty("buyerServiceTaxNo")
	private String buyerServiceTaxNo;
	@JsonProperty("totalDiscountPercentage")
	private String totalDiscountPercentage;
	@JsonProperty("totalDiscountAmt")
	private String totalDiscountAmt;
	@JsonProperty("shippingCharges")
	private String shippingCharges;
	@JsonProperty("declaration")
	private String declaration;
	@JsonProperty("itemNo")
	private String itemNo;
	@JsonProperty("itemType")
	private String itemType;
	@JsonProperty("itemName")
	private String itemName;
	@JsonProperty("itemDescription")
	private String itemDescription;
	@JsonProperty("itemQty")
	private String itemQty;
	@JsonProperty("itemRate")
	private String itemRate;
	@JsonProperty("itemUnit")
	private String itemUnit;
	@JsonProperty("itemIsDiscount")
	private String itemIsDiscount;
	@JsonProperty("itemDiscountPercentage")
	private String itemDiscountPercentage;
	@JsonProperty("itemLineDiscountAmt")
	private String itemLineDiscountAmt;
	@JsonProperty("itemIgst")
	private String itemIgst;
	@JsonProperty("itemCgst")
	private String itemCgst;
	@JsonProperty("itemSgst")
	private String itemSgst;
	@JsonProperty("itemIgstTaxAmt")
	private String itemIgstTaxAmt;
	@JsonProperty("itemCgstTaxAmt")
	private String itemCgstTaxAmt;
	@JsonProperty("itemSgstTaxAmt")
	private String itemSgstTaxAmt;
	@JsonProperty("itemTotal")
	private String itemTotal;
	@JsonProperty("itemHsnOrSac")
	private String itemHsnOrSac;
	@Transient
	private String docId;
}
