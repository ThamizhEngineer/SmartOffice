package com.ss.smartoffice.soservice.transaction.invoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ss.smartoffice.shared.busconfig.ConfigBusHelper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.currency.Currency;
import com.ss.smartoffice.shared.model.currency.CurrencyRepo;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.shared.model.partner.Partner;
import com.ss.smartoffice.soservice.master.employee.EmployeeRepository;
import com.ss.smartoffice.soservice.master.partnerservice.PartnerRepository;
import com.ss.smartoffice.soservice.transaction.invoiceinterface.IntInvoiceLine;
import com.ss.smartoffice.soservice.transaction.invoiceinterface.IntInvoiceLineRepo;

@Service
public class InvoiceGenerator {
	private static Logger log = LoggerFactory.getLogger(InvoiceGenerator.class);

	@Autowired
	CommonUtils commonUtils;
	@Autowired
	IntInvoiceLineRepo intInvoiceLineRepo;
	@Autowired
	InvoiceLineRepo invoiceLineRepo;
	@Autowired
	InvoiceHdrRepo invoiceHdrRepo;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	ConfigBusHelper configBusHelper;
	@Autowired
	CurrencyRepo currencyRepo;
	@Autowired
	PartnerRepository partnerRepo;

	public void start(String iInvoiceHdrId) {
		log.info("Invoice generation sarted for: " + iInvoiceHdrId);
		List<IntInvoiceLine> validRecords = intInvoiceLineRepo.fetchValidRecordsByIinvoiceHdrId(iInvoiceHdrId);
		List<String> invoiceNos = validRecords.stream().map(IntInvoiceLine::getInvoiceNo).collect(Collectors.toList());
		invoiceNos = invoiceNos.stream().distinct().collect(Collectors.toList());
		for (String invoiceNo : invoiceNos) {
			List<IntInvoiceLine> x = validRecords.stream().filter(i -> i.getInvoiceNo().equals(invoiceNo))
					.collect(Collectors.toList());
			InvoiceHdr invoiceHdr = formInvoiceHdr(x.get(0));
			List<InvoiceLine> invoiceLines = new ArrayList<InvoiceLine>();
			for (IntInvoiceLine intInvoiceLine : x) {
				InvoiceLine invoiceLine = formInvoiceLine(intInvoiceLine);
				invoiceLine.setInvoiceHdrId(invoiceHdr.getId().toString());
				invoiceLines.add(invoiceLine);
			}
			Iterable<InvoiceLine> iLine = invoiceLineRepo.saveAll(invoiceLines);
			calculatingHdr(invoiceHdr, iLine);
		}
		log.info("Invoice generation completed for: " + iInvoiceHdrId);
	}

	private InvoiceHdr formInvoiceHdr(IntInvoiceLine i) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		InvoiceHdr hdr = new InvoiceHdr();
		hdr.setiInvoiceHdrId(i.getiInvoiceHdrId());
		hdr.setInvoiceCode("CODE"); // Needs to be generated
		hdr.setSaleOrderCode(i.getSaleOrderCode());
		hdr.setSaleOrderId(i.getSaleOrderId());
		hdr.setRefInvoiceNo(i.getInvoiceNo());
		hdr.setInvoiceDate(LocalDateTime.parse(i.getInvoiceDate(), dateTimeFormatter));
		hdr.setPmtDueDt(LocalDateTime.parse(i.getPmtDueDt(), dateTimeFormatter));
		hdr.setDeliveryNote(i.getDeliveryNote());
		hdr.setDeliveryNoteDate(LocalDateTime.parse(i.getDeliveryNoteDate(), dateTimeFormatter));
		hdr.setTermsOfPayment(i.getTermsOfPayment());
		hdr.setClientRef(i.getClientRef());
		hdr.setClientId(i.getClientId()); // From system
		hdr.setOtherReference(i.getOtherReference());
		hdr.setBuyerOrderNo(i.getBuyerOrderNo());
		hdr.setDated(LocalDateTime.parse(i.getPmtDueDt(), dateTimeFormatter));
		hdr.setDespatchDocumentNo(i.getDespatchDocumentNo());
		hdr.setDespatchedThrough(i.getDespatchedThrough());
		hdr.setDestination(i.getDestination());
		hdr.setCountry(i.getCountry());
		hdr.setTermsOfDelivery(i.getTermsOfDelivery());
		hdr.setBuyerName(i.getBuyerName());
		hdr.setBuyerEmail(i.getBuyerEmail());
		hdr.setBuyerGstinOrUin(i.getBuyerGstinOrUin());
		hdr.setBuyerPlaceOfSupply(i.getBuyerPlaceOfSupply());
		hdr.setBuyerState(i.getBuyerState());
		hdr.setBuyerStateCode(i.getBuyerStateCode());
		hdr.setBuyerAddress(i.getBuyerAddress());
		hdr.setBuyerContact(i.getBuyerContact());
		hdr.setConsigneeName(i.getConsigneeName());
		hdr.setConsigneeGstinOrUin(i.getConsigneeGstinOrUin());
		hdr.setConsigneePlaceOfSupply(i.getConsigneePlaceOfSupply());
		hdr.setConsigneeState(i.getConsigneeState());
		hdr.setConsigneeStateCode(i.getConsigneeStateCode());
		hdr.setConsigneeAddress(i.getConsigneeAddress());
		hdr.setConsigneeContact(i.getConsigneeContact());
		hdr.setIgstTaxAmt(toBigDecimal(i.getIgstTaxAmt()));
		hdr.setCgstTaxAmt(toBigDecimal(i.getCgstTaxAmt()));
		hdr.setSgstTaxAmt(toBigDecimal(i.getSgstTaxAmt()));
		hdr.setTotalQty(intNullCheck(i.getTotalQty()));
		hdr.setTotalDiscountPercentage(i.getTotalDiscountPercentage());
		hdr.setTotalDiscountAmt(toBigDecimal(i.getTotalDiscountAmt()));
		hdr.setShippingAmt(toBigDecimal(i.getShippingCharges()));
		hdr.setTransactionCurrName(i.getAmtAbbri());
		hdr.setCompanyPanOrIecCode(i.getCompanyPanOrIecCode());
		hdr.setBuyerVatTin(i.getBuyerVatTin());
		hdr.setBuyerServiceTaxNo(i.getBuyerServiceTaxNo());
		hdr.setDeclaration(i.getDeclaration());
		hdr.setCreatedBy(commonUtils.getLoggedinUserId());		
		
		memployee employee = employeeRepository.findById(Integer.parseInt(commonUtils.getLoggedinEmployeeId())).get();
		hdr.setInvoiceCreatedByEmpName(employee.getEmpName());
		hdr.setDirGroupId(employee.getDirUsrGrpId());
		hdr.setDirGroupName(employee.getDirUsrGrpName());
		hdr.setInvoiceStatus("CREATED");
		hdr.setInvoiceCreatedBy(commonUtils.getLoggedinUserId());
		
		hdr.setInvoiceCreatedDt(LocalDateTime.now());
		hdr.setCreatedDate(LocalDateTime.now());
		updateCompanyInfo(hdr);
		List<Partner> partner = partnerRepo.fetchByReferenceNumber(i.getClientRef());
		if(partner.size()==1) {
			hdr.setBuyerEmail(partner.get(0).getEmailId());	
			hdr.setBuyerName(partner.get(0).getClientName());			
			hdr.setBuyerGstinOrUin(partner.get(0).getGstNo());
			hdr.setBuyerEmail(partner.get(0).getEmailId());
			hdr.setTransactionCurrId(partner.get(0).getTransactionCurrId());			
			hdr.setBuyerHasGst(partner.get(0).getCompanyHasGst());		
			hdr.setExchangeRate(commonUtils.findExchangeRate(hdr.getTransactionCurrId(),hdr.getCompanyCurrId()));
		}
		
		return invoiceHdrRepo.save(hdr);
	}
	
	
	private InvoiceLine formInvoiceLine(IntInvoiceLine i) {
		InvoiceLine line = new InvoiceLine();
		line.setItemNo(i.getItemNo());
		line.setItemId(i.getItemId()); // From system
		line.setItemType(i.getItemType());
		line.setItemName(i.getItemName());
		line.setItemDescription(i.getItemDescription());
		line.setItemQty(intNullCheck(i.getItemQty()));
		line.setItemNotes(i.getItemDescription());
		line.setItemRate(nullCheck(i.getItemRate()));
		line.setItemPer(i.getItemUnit());
		line.setIsDiscount(i.getItemIsDiscount());
		line.setDiscountPercentage(i.getItemDiscountPercentage());
		line.setLineDiscountAmt(toBigDecimal(i.getItemLineDiscountAmt()));
		line.setIgst(nullCheck(i.getItemIgst()));
		line.setCgst(nullCheck(i.getItemCgst()));
		line.setSgst(nullCheck(i.getItemSgst()));
		line.setIgstTaxAmt(toBigDecimal(i.getItemIgstTaxAmt()));
		line.setCgstTaxAmt(toBigDecimal(i.getItemCgstTaxAmt()));
		line.setSgstTaxAmt(toBigDecimal(i.getItemSgstTaxAmt()));
		line.setItemTotal(toBigDecimal(i.getItemTotal()));
		line.setItemHsnOrSac(i.getItemHsnOrSac());
		line.setCreatedBy(commonUtils.getLoggedinUserId());
		line.setCreatedDate(LocalDateTime.now());
		line = calculatingLine(line);
		return line;
	}

	public InvoiceLine calculatingLine(InvoiceLine line) {
		log.info("Calculating items");
		try {			
			BigDecimal itemTaxAmount = new BigDecimal(0);
			BigDecimal itemTotal = new BigDecimal(0);
			float tempItemBeforeTaxAmount = (line.getItemRate() * line.getItemQty());
			BigDecimal itemBeforeTaxAmount = new BigDecimal(tempItemBeforeTaxAmount).subtract(line.getLineDiscountAmt());
			itemTaxAmount = itemTaxAmount.add(line.getIgstTaxAmt().add(line.getCgstTaxAmt()).add(line.getSgstTaxAmt()));
			itemTotal = itemBeforeTaxAmount.add(itemTaxAmount);
			line.setItemBeforeTaxAmount(itemBeforeTaxAmount);
			line.setItemTaxAmount(itemTaxAmount);
			line.setItemTotal(itemTotal);
		} catch (Exception e) {
			log.error("Error during item - line calculation");
			e.printStackTrace();
		}
		return line;
	}

	public InvoiceHdr calculatingHdr(InvoiceHdr hdr, Iterable<InvoiceLine> line) {
		log.info("Calculating hdr");
		try {
			updateCompanyInfo(hdr);
			BigDecimal beforeTaxSubTotalAmt = new BigDecimal(0);
			BigDecimal totalTaxAmt = new BigDecimal(0);
			BigDecimal afterTaxSubTotalAmt = new BigDecimal(0);
			BigDecimal finalPayableAmt = new BigDecimal(0);
			BigDecimal invoiceWithoutExAmt = new BigDecimal(0);
			for (InvoiceLine invoiceLine : line) {
				beforeTaxSubTotalAmt = beforeTaxSubTotalAmt.add(toBigDecimal(invoiceLine.getItemTotal().toString()));
				totalTaxAmt = totalTaxAmt.add(toBigDecimal(invoiceLine.getItemTaxAmount().toString()));
			}
			// afterTaxSubTotalAmt = beforeTaxSubTotalAmt.add(totalTaxAmt); User will send tax added value 
			afterTaxSubTotalAmt = beforeTaxSubTotalAmt;
			finalPayableAmt = (afterTaxSubTotalAmt.subtract(hdr.getTotalDiscountAmt())).add(hdr.getShippingAmt());

			hdr.setBeforeTaxSubTotalAmt(beforeTaxSubTotalAmt);
			hdr.setTotalTaxAmt(totalTaxAmt);
			hdr.setAfterTaxSubTotalAmt(afterTaxSubTotalAmt);
			hdr.setFinalPayableAmt(finalPayableAmt);
			BigDecimal paidamt = new BigDecimal(0);
			hdr.setPaidAmt(paidamt);			
			hdr.setExchangeRate(hdr.getExchangeRate());
			invoiceWithoutExAmt=(finalPayableAmt.multiply(BigDecimal.valueOf(hdr.getExchangeRate())));
			hdr.setInvoiceWithoutExAmt(invoiceWithoutExAmt);
			hdr.setBalanceAmt(invoiceWithoutExAmt);
			invoiceHdrRepo.save(hdr);
		} catch (NumberFormatException e) {
			log.error("Error while calculating hdr");
			e.printStackTrace();
		}
		return hdr;
	}

	private float nullCheck(String input) {
		float out = 0;
		try {
			if (input != null) {
				input = input.replaceAll("[^\\d.]", ""); // Removing non numeric
				return Float.parseFloat(input);
			} else {
				return out;
			}
		} catch (NumberFormatException e) {
			log.error("Error during nullcheck");
			e.printStackTrace();
			return out;
		}
	}
	
	private Integer intNullCheck(String input) {
		Integer out = 0;
		try {
			if (input != null) {
				input = input.replaceAll("[^\\d.]", ""); // Removing non numeric
				String i = String.valueOf(Math.round(Float.parseFloat(input)));
				return Integer.parseInt(i);
			} else {
				return out;
			}
		} catch (NumberFormatException e) {
			log.error("Error during int null check");
			e.printStackTrace();
			return out;
		}
	}

	public BigDecimal toBigDecimal(String input) {
		BigDecimal defaultOut = new BigDecimal(0);
		try {
			if (input != null && !input.equals("0") && input != "") {
				input = input.replaceAll("[^\\d.]", ""); // Removing non numeric
				BigDecimal out = new BigDecimal(input);
				return out;
			} else {
				return defaultOut;
			}
		} catch (Exception e) {
			log.error("Error while converting to bigDecimal");
			e.printStackTrace();
			return defaultOut;
		}
	}
	
	
	public InvoiceHdr updateCompanyInfo(InvoiceHdr invoiceHdr)throws SmartOfficeException{
		
		List<Config> config = (List<Config>) configBusHelper.getConfig("CORPORATE", "CORPORATE");		
		Map<String, String> companyDetails = new HashMap<>();
		for(Config c:config) {
			companyDetails.put(c.getConfigDtlName(), c.getConfigDtlValue());
		}
		invoiceHdr.setCompanyName(companyDetails.get("name"));
		invoiceHdr.setCompanyAddress(companyDetails.get("address"));
		invoiceHdr.setCompanyGstNo(companyDetails.get("gstNo"));
		invoiceHdr.setCompanyStateName(companyDetails.get("stateName"));
		invoiceHdr.setCompanyCode(companyDetails.get("code"));
		invoiceHdr.setCompanyCin(companyDetails.get("cin"));
		invoiceHdr.setCompanyLogo(companyDetails.get("logo"));
		invoiceHdr.setCompanyPhoneNo(companyDetails.get("phoneNo"));
		invoiceHdr.setCompanyAccountNo(companyDetails.get("accountNo"));
		invoiceHdr.setCompanyIfscCode(companyDetails.get("ifscCode"));
		invoiceHdr.setCompanyAccountHolderName(companyDetails.get("accountHolderName"));
		invoiceHdr.setCompanySwiftCode(companyDetails.get("swiftCode"));
		invoiceHdr.setCompanyBankName(companyDetails.get("bankName"));
		invoiceHdr.setCompanyBankBranch(companyDetails.get("bankBranch"));
		
		Currency currency = currencyRepo.findById(companyDetails.get("operationCurr")).get();
		invoiceHdr.setCompanyCurrId(currency.getId());
		invoiceHdr.setCompanyCurrName(currency.getCurrName());
		invoiceHdr.setCompanyCurrCode(currency.getCurrCode());
		invoiceHdr.setCompanyCurrSymbol(currency.getCurrSymbol());
		
		Currency currency2 = currencyRepo.findById(invoiceHdr.getTransactionCurrId()).get();
		invoiceHdr.setTransactionCurrCode(currency2.getCurrCode());
		invoiceHdr.setTransactionCurrName(currency2.getCurrName());
		invoiceHdr.setTransactionCurrSymbol(currency2.getCurrSymbol());
		
		return invoiceHdr;	
	}
}
