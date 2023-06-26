package com.ss.smartoffice.sodocumentservice.purchaseorder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.interceptor.NumberToWords;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.PurchaseOrder;

@Service
public class PurchaseOrderPdfGenerator {
	
	private static final String PURCHASE_ORDER_TEMPLATE_NAME = "html/purchase-order";
	
	@Value("${purchaseorder.pdf.location}")
	private String purchaseorderPdfLocation;

	@Value("${purchaseorder.url}")
	private String purchaseOrderUrl;
	
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
	private TemplateEngine htmlTemplateEngine;
	
	
	public void generatePdf(PurchaseOrder purchaseOrder) {
		ITextRenderer renderer = new ITextRenderer();
		try {

		SimpleDateFormat dt= new SimpleDateFormat("dd-MMMM-yyyy");
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("vendorName", purchaseOrder.getVendorName());
		ctx.setVariable("poRefNumber", purchaseOrder.getPoRefNumber());
		ctx.setVariable("poDt", dt.format(purchaseOrder.getPoDt()));
		ctx.setVariable("address",purchaseOrder.getAddress());
		ctx.setVariable("supplierRefNumber", purchaseOrder.getSupplierRefNumber());
		ctx.setVariable("otherRefNumber", purchaseOrder.getOtherRefNumber());
		ctx.setVariable("despatchThrough", purchaseOrder.getDespatchThrough());
		ctx.setVariable("destination", purchaseOrder.getDestination());
		ctx.setVariable("deliveryTerms", purchaseOrder.getDeliveryTerms());
		ctx.setVariable("vendorPanNumber", purchaseOrder.getVendorPanNumber());
		ctx.setVariable("vendorTinNumber", purchaseOrder.getVendorTinNumber());
		ctx.setVariable("poLines", purchaseOrder.getPurchaseOrderLines());
		ctx.setVariable("cgst", purchaseOrder.getCgst());
		ctx.setVariable("sgst", purchaseOrder.getSgst());
		ctx.setVariable("igst", purchaseOrder.getIgst());
		ctx.setVariable("grossPoAmt", purchaseOrder.getGrossPoAmt());
		ctx.setVariable("grossPoAmtWords",numberToWords.convert(purchaseOrder.getGrossPoAmt().intValue())+" Rupees "+ "Only");
	
		final String htmlContent = this.htmlTemplateEngine.process(PURCHASE_ORDER_TEMPLATE_NAME, ctx);		
		String fileNamePrefix = "purchase-order-" + commonUtils.generateId();
		String fileNameSuffix = ".pdf";
		String fileLocation = purchaseorderPdfLocation + fileNamePrefix + fileNameSuffix;
		renderer.setDocumentFromString(htmlContent);
		renderer.layout();	
		renderer.createPDF(new FileOutputStream(fileLocation));
		renderer.finishPDF();
		MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix+fileNameSuffix,fileNamePrefix+fileNameSuffix,"", new FileInputStream(new File(fileLocation)));
		MultipartFile[] multipartFileArray = new MultipartFile[1];
		multipartFileArray[0]=multipartFile;
		List<DocInfo> docInfos= docMgmtService.uploadDocAsBinary("VENDOR-PO", multipartFileArray);
		String docId="";
		
		for(DocInfo docInfo:docInfos) {
			docId =docInfo.getDocId();			
		}
		purchaseOrder.setDocId(docId);
		
		List<DocInfo> docInfosFromDb = documentManagementHelper.getDocInfoByDocId(docId);
		for(DocInfo docInfo :docInfosFromDb) {
			docInfo.setDocExtension("pdf");
		}
		System.out.println(docInfosFromDb);
		docInfoHelper.saveAll(docInfosFromDb);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
		purchaseOrder.setPoDt(null);
		HttpEntity<PurchaseOrder> request = new HttpEntity<PurchaseOrder>(purchaseOrder, headers);
		String purchaseOrderUrlForPost=purchaseOrderUrl+purchaseOrder.getId()+"/doc-id";
		commonUtils.getRestTemplate().exchange(purchaseOrderUrlForPost,HttpMethod.POST, request, PurchaseOrder.class);
		checkIn(purchaseOrder);
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		 catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void checkIn(@RequestBody PurchaseOrder purchaseOrder) {
		try {
			List<DocMetadata> docMetadataList = new ArrayList<>();
			List<DocInfo> docInfoList = new ArrayList<>();
			DocInfo docInfo = new DocInfo(); 
			docInfo.setDocId(purchaseOrder.getDocId());
		
			DocMetadata docMetadata = new DocMetadata();
			docMetadata.setMdCode("vendor-id");
			docMetadata.setMdValue(purchaseOrder.getVendorId().toString());
			docMetadataList.add(docMetadata);
			
			DocMetadata docMetadata1 = new DocMetadata();
			docMetadata1.setMdCode("vendor-po-id");
			docMetadata1.setMdValue(purchaseOrder.getId().toString());
			docMetadataList.add(docMetadata1);
			docInfo.setMetadataList(docMetadataList);
		
			docInfoList.add(docInfo);
			docMgmtService.checkInDocs(docInfoList);
		}catch (Exception e) {
			e.getLocalizedMessage();
		}
	}

}
