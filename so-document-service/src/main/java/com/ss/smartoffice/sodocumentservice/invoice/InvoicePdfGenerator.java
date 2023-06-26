package com.ss.smartoffice.sodocumentservice.invoice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.util.StringUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.busconfig.ConfigBusHelper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.interceptor.NumberToWords;
import com.ss.smartoffice.shared.model.Config;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.InvoiceHdr;

@Service
public class InvoicePdfGenerator {
	
	private static final String INVOICE_PDF_TEMPLATE_NAME = "html/invoice-pdf";
	private static final String INVOICE_Without_gst_TEMPLATE_NAME = "html/supplier-invoice-portrait";
	private static final String INVOICE_Export_TEMPLATE_NAME = "html/export-invoice-pdf";
	
	@Value("${invoice.pdf.location}")
	private String invoicePdfLocation;

	@Value("${invoice-pdf.url}")
	private String InvoiceHdrUrl;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	NumberToWords numberToWords;

	@Autowired
	DocMgmtService docMgmtService;
	
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	
	@Autowired
	DocInfoHelper docInfoHelper;
	
	@Autowired
	ConfigBusHelper configBusHelper;
	
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	public void generatePdf(InvoiceHdr InvoiceHdr) {
		ITextRenderer renderer = new ITextRenderer();
		try {
			
			List<Config> config = (List<Config>) configBusHelper.getConfig("CORPORATE", "CORPORATE");
			
			LocalDate date = InvoiceHdr.getInvoiceDate().toLocalDate();			
			final Context ctx = new Context(Locale.ENGLISH);
			
			ctx.setVariable("companyName", InvoiceHdr.getCompanyName());
			ctx.setVariable("companyAddress",InvoiceHdr.getCompanyAddress());			
			ctx.setVariable("gstin", InvoiceHdr.getCompanyGstNo());
			ctx.setVariable("stateName", InvoiceHdr.getCompanyStateName());
			ctx.setVariable("stateCode", InvoiceHdr.getCompanyCode());
			ctx.setVariable("cin", InvoiceHdr.getCompanyCin());
			ctx.setVariable("logo", InvoiceHdr.getCompanyLogo());
			ctx.setVariable("invoiceCode", InvoiceHdr.getInvoiceCode());
			ctx.setVariable("balanceAmt", InvoiceHdr.getBalanceAmt());
			ctx.setVariable("buyerAddress", InvoiceHdr.getBuyerAddress());
			ctx.setVariable("buyerGstinOrUin", InvoiceHdr.getBuyerGstinOrUin()); 
			ctx.setVariable("buyerPlaceOfSupply", InvoiceHdr.getBuyerPlaceOfSupply());
			ctx.setVariable("invoiceDate", InvoiceHdr.getInvoiceDate());
			ctx.setVariable("termsOfPayment", InvoiceHdr.getTermsOfPayment());
			ctx.setVariable("deliveryNote", InvoiceHdr.getDeliveryNote());
			ctx.setVariable("pmtDueDt", InvoiceHdr.getPmtDueDt());
			ctx.setVariable("beforeTaxSubTotalAmt", InvoiceHdr.getBeforeTaxSubTotalAmt());
//			ctx.setVariable("cgst", InvoiceHdr.getCgstTaxAmt());
//			ctx.setVariable("sgst", InvoiceHdr.getSgstTaxAmt());
			ctx.setVariable("afterTaxSubTotalAmt", InvoiceHdr.getAfterTaxSubTotalAmt());
			ctx.setVariable("termsOfPayment", InvoiceHdr.getTermsOfPayment());
			ctx.setVariable("currSymbol", InvoiceHdr.getTransactionCurrSymbol());
			ctx.setVariable("currAbbr", InvoiceHdr.getTransactionCurrName());
			ctx.setVariable("exchangeRate", InvoiceHdr.getExchangeRate());

			ctx.setVariable("buyerHasGst", InvoiceHdr.getBuyerHasGst());
			


			ctx.setVariable("clientName", InvoiceHdr.getBuyerName());
			ctx.setVariable("poRefNumber", InvoiceHdr.getRefInvoiceNo());
			ctx.setVariable("poDt", date);
			ctx.setVariable("address",InvoiceHdr.getBuyerAddress());
			ctx.setVariable("supplierRefNumber", InvoiceHdr.getClientRef());
			ctx.setVariable("otherRefNumber", InvoiceHdr.getOtherReference());
			ctx.setVariable("despatchThrough", InvoiceHdr.getDespatchedThrough());
			ctx.setVariable("destination", InvoiceHdr.getDestination());
			ctx.setVariable("deliveryTerms", InvoiceHdr.getTermsOfDelivery());
			ctx.setVariable("panNumber", InvoiceHdr.getCompanyPanOrIecCode());
			ctx.setVariable("tinNumber", InvoiceHdr.getBuyerVatTin());
			ctx.setVariable("poLines", InvoiceHdr.getInvoiceLines());
			ctx.setVariable("cgst", InvoiceHdr.getCgstTaxAmt());
			ctx.setVariable("sgst", InvoiceHdr.getSgstTaxAmt());
			ctx.setVariable("igst", InvoiceHdr.getIgstTaxAmt());
			ctx.setVariable("grossPoAmt", InvoiceHdr.getFinalPayableAmt());
			ctx.setVariable("balanceAmt", InvoiceHdr.getBalanceAmt());
			ctx.setVariable("grossPoAmtWords",numberToWords.convert(InvoiceHdr.getFinalPayableAmt().intValue())+" "+InvoiceHdr.getTransactionCurrName()+" Only");
		
			final String htmlContent ;
			if(InvoiceHdr.getBuyerHasOverseas().equals("Y")) {
				htmlContent = this.htmlTemplateEngine.process(INVOICE_Export_TEMPLATE_NAME, ctx);
			}else if(InvoiceHdr.getIsHandledGst().equals("N") || (InvoiceHdr.getBuyerHasGst().equals("N")) ){
				htmlContent = this.htmlTemplateEngine.process(INVOICE_Without_gst_TEMPLATE_NAME, ctx);
			}
			else {
				htmlContent = this.htmlTemplateEngine.process(INVOICE_PDF_TEMPLATE_NAME, ctx);	
			}				
			String fileNamePrefix = "invoice-" + commonUtils.generateId();
			String fileNameSuffix = ".pdf";
			String fileLocation = invoicePdfLocation + fileNamePrefix + fileNameSuffix;
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();	
			renderer.createPDF(new FileOutputStream(fileLocation));
			renderer.finishPDF();
			MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix+fileNameSuffix,fileNamePrefix+fileNameSuffix,"", new FileInputStream(new File(fileLocation)));
			MultipartFile[] multipartFileArray = new MultipartFile[1];
			multipartFileArray[0]=multipartFile;
			List<DocInfo> docInfos= docMgmtService.uploadDocAsBinary("INVOICE-PDF", multipartFileArray);
			String docId="";
			
			for(DocInfo docInfo:docInfos) {
				docId =docInfo.getDocId();			
			}
			InvoiceHdr.setPdfDocId(docId);
			
			List<DocInfo> docInfosFromDb = documentManagementHelper.getDocInfoByDocId(docId);
			for(DocInfo docInfo :docInfosFromDb) {
				docInfo.setDocExtension("pdf");
			}
			System.out.println(docInfosFromDb);
			docInfoHelper.saveAll(docInfosFromDb);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
			HttpEntity<InvoiceHdr> request = new HttpEntity<InvoiceHdr>(InvoiceHdr, headers);
			String InvoiceHdrUrlForPost=InvoiceHdrUrl+InvoiceHdr.getId()+"/doc-id";
			commonUtils.getRestTemplate().exchange(InvoiceHdrUrlForPost,HttpMethod.POST, request, InvoiceHdr.class);
			
			
//			List<DocMetadata> docMetadataList = new ArrayList<>();
//			List<DocInfo> docInfoList = new ArrayList<>();
//			DocInfo docInfo = new DocInfo(); 
//			docInfo.setDocId(InvoiceHdr.getDocId());
//		
//			DocMetadata docMetadata = new DocMetadata();
//			docMetadata.setMdCode("vendor-id");
//			docMetadata.setMdValue(InvoiceHdr.getVendorId().toString());
//			docMetadataList.add(docMetadata);
//			
//			DocMetadata docMetadata1 = new DocMetadata();
//			docMetadata1.setMdCode("vendor-po-id");
//			docMetadata1.setMdValue(InvoiceHdr.getId().toString());
//			docMetadataList.add(docMetadata1);
//			docInfo.setMetadataList(docMetadataList);
//		
//			docInfoList.add(docInfo);
//			docMgmtService.checkInDocs(docInfoList);	
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 catch (Exception e) {
				e.printStackTrace();
			}

		
	}
}
