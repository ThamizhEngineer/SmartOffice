package com.ss.smartoffice.soservice.transaction.invoiceinterface;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.InvoiceForUpload;
import com.ss.smartoffice.shared.model.partner.Partner;
import com.ss.smartoffice.soservice.master.item.Item;
import com.ss.smartoffice.soservice.master.item.ItemRepository;
import com.ss.smartoffice.soservice.master.partnerservice.PartnerRepository;
import com.ss.smartoffice.soservice.transaction.invoice.InvoiceGenerator;
import com.ss.smartoffice.soservice.transaction.model.SaleOrder;
import com.ss.smartoffice.soservice.transaction.saleorder.SaleOrderRepository;

@Service
public class IntInvoiceHelper {
	
	@Autowired 
	CommonUtils commonUtils;
	@Autowired
	IntInvoiceHdrRepo hdrRepo;
	@Autowired
	IntInvoiceLineRepo lineRepo;
	@Autowired
	ItemRepository itemRepo;
	@Autowired
	PartnerRepository partnerRepo;
	@Autowired
	InvoiceGenerator invoiceGenerator;
	@Autowired
	SaleOrderRepository saleOrderRepository;
	
	public void start(List<InvoiceForUpload> extarctedData) {
		Iterable<IntInvoiceLine> savedRecords = savingRecords(extarctedData);
		validate(savedRecords);
		String iInvoicehdrId = savedRecords.iterator().next().getiInvoiceHdrId();
		invoiceGenerator.start(iInvoicehdrId); //Invoice generation starts here
	}
	
	private Iterable<IntInvoiceLine> savingRecords(List<InvoiceForUpload> extarctedData) {
		try {
			String docId = extarctedData.get(0).getDocId();
			IntInvoiceHdr intInvHdr = formIntInvoiceHdr(docId);
			List<IntInvoiceLine> intInvLines = new ArrayList<IntInvoiceLine>();
			for (InvoiceForUpload invoiceForUpload : extarctedData) {
				IntInvoiceLine intInvLine = formIntInvoiceLine(intInvHdr.getId(),invoiceForUpload);
				intInvLines.add(intInvLine);
			}
			return lineRepo.saveAll(intInvLines);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException("Error while saving records");
		}
	}
	
	private Iterable<IntInvoiceLine> validate(Iterable<IntInvoiceLine> savedRecords){
		for (IntInvoiceLine intInvoiceLine : savedRecords) {
			try {
				List<Item> item = itemRepo.findByHsnSacCode(intInvoiceLine.getItemHsnOrSac());
				SaleOrder saleOrder = saleOrderRepository.findBySaleOrderCode(intInvoiceLine.getSaleOrderCode());
				boolean isItem = checkFunc(item.size());
				List<Partner> partner = partnerRepo.fetchByReferenceNumber(intInvoiceLine.getClientRef());
				boolean isPartner = checkFunc(partner.size());
				
				if(saleOrder!=null) {
					intInvoiceLine.setSaleOrderId(saleOrder.getSaleOrderCode());
					intInvoiceLine.setSaleOrderId(saleOrder.getId().toString());
				}
				
				if (isItem==true && isPartner==true) {
					intInvoiceLine.setItemId(String.valueOf(item.get(0).getId()));
					intInvoiceLine.setClientId(String.valueOf(partner.get(0).getId()));
					intInvoiceLine.setIsValid("Y");
				}else if(isPartner==true && isItem==false){
					intInvoiceLine.setIsValid("N");
					intInvoiceLine.setStatus("Item master empty or duplicate. item-reference: "+intInvoiceLine.getItemNo());
				} else if(isPartner==false && isItem==true) {
					intInvoiceLine.setIsValid("N");
					intInvoiceLine.setStatus("Partner master empty or duplicate. partner-reference: "+intInvoiceLine.getClientRef());
				}else {
					intInvoiceLine.setIsValid("N");
					intInvoiceLine.setStatus("Both partner & item master is empty or duplicate. partner-reference: "+intInvoiceLine.getItemNo() +" and item-reference: "+intInvoiceLine.getClientRef());
				}
				intInvoiceLine.setModifiedBy(commonUtils.getLoggedinUserId());
				intInvoiceLine.setModifiedDate(LocalDateTime.now());
				lineRepo.save(intInvoiceLine);
			} catch (Exception e) {
				intInvoiceLine.setIsError("Y");
				intInvoiceLine.setErrorMessage(e.getLocalizedMessage());
				intInvoiceLine.setIsValid("N");
				intInvoiceLine.setStatus("Error during validation");
				intInvoiceLine.setModifiedBy(commonUtils.getLoggedinUserId());
				intInvoiceLine.setModifiedDate(LocalDateTime.now());
				lineRepo.save(intInvoiceLine);
				e.printStackTrace();
			}
		}
		return savedRecords;
	}
	
	private boolean checkFunc(int size) {
		if (size==1) {
			return true;
		}else {
			return false;
		}
	}
	
	private IntInvoiceHdr formIntInvoiceHdr(String docId){
		IntInvoiceHdr intInvHdr = new IntInvoiceHdr();
		intInvHdr.setUploadDocId(docId);
		intInvHdr.setUploadDate(LocalDateTime.now());
		intInvHdr.setProcessDate(LocalDateTime.now());
		intInvHdr.setCreatedBy(commonUtils.getLoggedinUserId());
		intInvHdr.setCreatedDate(LocalDateTime.now());
		intInvHdr = hdrRepo.save(intInvHdr);
		return intInvHdr;
	}
	
	private IntInvoiceLine formIntInvoiceLine(Integer intInvHdrId, InvoiceForUpload invoiceForUpload) {
		IntInvoiceLine i = new IntInvoiceLine();
		i.setiInvoiceHdrId(intInvHdrId.toString());
		i.setInvoiceNo(invoiceForUpload.getInvoiceNo());
		i.setInvoiceDate(invoiceForUpload.getInvoiceDate()); 
		i.setSaleOrderCode(invoiceForUpload.getSaleOrderCode());
		i.setPmtDueDt(invoiceForUpload.getPaymentDueDt());
		i.setDeliveryNote(invoiceForUpload.getDeliveryNote());
		i.setDeliveryNoteDate(invoiceForUpload.getDeliveryNoteDate());
		i.setTermsOfPayment(invoiceForUpload.getTermsOfPayment());
		i.setClientRef(invoiceForUpload.getClientRef());
		i.setOtherReference(invoiceForUpload.getOtherReference());
		i.setBuyerOrderNo(invoiceForUpload.getBuyerOrderNo());
		i.setDated(invoiceForUpload.getDated());
		i.setDespatchDocumentNo(invoiceForUpload.getDespatchDocumentNo());
		i.setDespatchedThrough(invoiceForUpload.getDespatchedThrough());
		i.setDestination(invoiceForUpload.getDestination());
		i.setCountry(invoiceForUpload.getCountry());
		i.setTermsOfDelivery(invoiceForUpload.getTermsOfDelivery());
		i.setBuyerName(invoiceForUpload.getBuyerName());
		i.setBuyerEmail(invoiceForUpload.getBuyerEmail());
		i.setBuyerGstinOrUin(invoiceForUpload.getBuyerGstinOrUin());
		i.setBuyerPlaceOfSupply(invoiceForUpload.getBuyerPlaceOfSupply());
		i.setBuyerState(invoiceForUpload.getBuyerState());
		i.setBuyerStateCode(invoiceForUpload.getBuyerStateCode());
		i.setBuyerAddress(invoiceForUpload.getBuyerAddress());
		i.setBuyerContact(invoiceForUpload.getBuyerContact());
		i.setConsigneeName(invoiceForUpload.getConsigneeName());
		i.setConsigneeGstinOrUin(invoiceForUpload.getConsigneeGstinOrUin());
		i.setConsigneePlaceOfSupply(invoiceForUpload.getConsigneePlaceOfSupply());
		i.setConsigneeState(invoiceForUpload.getConsigneeState());
		i.setConsigneeStateCode(invoiceForUpload.getConsigneeStateCode());
		i.setConsigneeAddress(invoiceForUpload.getConsigneeAddress());
		i.setConsigneeContact(invoiceForUpload.getConsigneeContact());
		i.setIgstTaxAmt(invoiceForUpload.getIgstTaxAmt());
		i.setCgstTaxAmt(invoiceForUpload.getCgstTaxAmt());
		i.setSgstTaxAmt(invoiceForUpload.getSgstTaxAmt());
		i.setTotalTaxAmt(invoiceForUpload.getTotalTaxAmt());
		i.setTotalQty(invoiceForUpload.getTotalQty());
		i.setSubTotal(invoiceForUpload.getSubTotal());
//		i.setAmtSymbol(invoiceForUpload.getAmtSymbol()); DB does not support the incoming format
		i.setAmtAbbri(invoiceForUpload.getAmtAbbri());
		i.setAmountChargeable(invoiceForUpload.getAmountChargeable());
		i.setCompanyPanOrIecCode(invoiceForUpload.getCompanyPanOrIecCode());
		i.setBuyerVatTin(invoiceForUpload.getBuyerVatTin());
		i.setBuyerServiceTaxNo(invoiceForUpload.getBuyerServiceTaxNo());
		i.setTotalDiscountPercentage(invoiceForUpload.getTotalDiscountPercentage());
		i.setTotalDiscountAmt(invoiceForUpload.getTotalDiscountAmt());
		i.setShippingCharges(invoiceForUpload.getShippingCharges());
		i.setDeclaration(invoiceForUpload.getDeclaration());
		i.setItemNo(invoiceForUpload.getItemNo());	
		i.setItemType(invoiceForUpload.getItemType());
		i.setItemName(invoiceForUpload.getItemName());
		i.setItemDescription(invoiceForUpload.getItemDescription());
		i.setItemQty(invoiceForUpload.getItemQty());
		i.setItemRate(invoiceForUpload.getItemRate());
		i.setItemUnit(invoiceForUpload.getItemUnit());
		i.setItemIsDiscount(invoiceForUpload.getItemIsDiscount());
		i.setItemDiscountPercentage(invoiceForUpload.getItemDiscountPercentage());
		i.setItemLineDiscountAmt(invoiceForUpload.getItemLineDiscountAmt());
		i.setItemIgst(invoiceForUpload.getItemIgst());
		i.setItemCgst(invoiceForUpload.getItemCgst());
		i.setItemSgst(invoiceForUpload.getItemSgst());
		i.setItemIgstTaxAmt(invoiceForUpload.getItemIgstTaxAmt());
		i.setItemCgstTaxAmt(invoiceForUpload.getItemCgstTaxAmt());
		i.setItemSgstTaxAmt(invoiceForUpload.getItemSgstTaxAmt());
		i.setItemTotal(invoiceForUpload.getItemTotal());
		i.setItemHsnOrSac(invoiceForUpload.getItemHsnOrSac());
		i.setCreatedBy(commonUtils.getLoggedinUserId());
		i.setCreatedDate(LocalDateTime.now());
		return i;
	}

}
