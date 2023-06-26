package com.ss.smartoffice.sodocumentservice.printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocTypeHelper;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.shared.model.dm.DocumentType;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.CertificateTracker;


@Service
public class CertificateGeneration {
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	@Autowired
	CommonUtils commonUtils;
	@Value("${certificate-tracker.url}")
	private String certificateTrackerUrl;
	@Value("docs.base.location")
	private String certificatePrintLocation;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocTypeHelper docTypeHelper;
	@Autowired
	DocMgmtService docMgmtService;
	
	@Autowired
	DocInfoHelper docInfoHelper;
	String fileLocation=null;
	String fileNameSuffix =null;
	String fileNamePrefix=null;
	
	public void generateCertificate(List<CertificateTracker> list)throws SmartOfficeException{
	String htmlContent=prepareContext(list);
	formMetadta(htmlContent, list);
	}
	
	private List<DocInfo> formMetadta(String htmlContent, List<CertificateTracker> certificateTrackers) {
		ITextRenderer renderer = new ITextRenderer();
		try {
			String docPdfId="";
			for(CertificateTracker certificateTracker:certificateTrackers) {
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();	

			renderer.createPDF(new FileOutputStream(fileLocation));
			renderer.finishPDF();
			
			MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix+fileNameSuffix,fileNamePrefix+fileNameSuffix,"", new FileInputStream(new File(fileLocation)));
			MultipartFile[] multipartFileArray = new MultipartFile[1];
			multipartFileArray[0]=multipartFile;
			List<DocInfo> docInfos= docMgmtService.uploadDocAsBinary("CERTIFICATE", multipartFileArray);
			
			for(DocInfo docInfo:docInfos) {
				docPdfId =docInfo.getDocId();
				
			}
			List<DocInfo> docInfosFromDb = documentManagementHelper.getDocInfoByDocId(docPdfId);
			
			for(DocInfo docInfo :docInfosFromDb) {
				docInfo.setDocExtension("pdf");
			}
			docInfoHelper.saveAll(docInfosFromDb);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
		
			certificateTracker.setCertificateDocId(docPdfId);
		
			HttpEntity<CertificateTracker> request = new HttpEntity<CertificateTracker>(certificateTracker, headers);
			String certificateUrlForPatch=certificateTrackerUrl+"/_internal/"+certificateTracker.getId()+"/"+"certificate-pdf";
			commonUtils.getRestTemplate().exchange(certificateUrlForPatch,HttpMethod.POST, request, CertificateTracker.class);
		
			List<DocMetadata> docMetadataList = new ArrayList<>();
			List<DocInfo> docInfoList = new ArrayList<>();
			DocInfo docInfo = new DocInfo(); 
			docInfo.setDocId(docPdfId);
		
			DocMetadata docMetadata = new DocMetadata();
			docMetadata.setMdCode("certificate-id");
			docMetadata.setMdValue(certificateTracker.getId().toString());
			docMetadataList.add(docMetadata);
			
			
			docInfo.setMetadataList(docMetadataList);
		
			docInfoList.add(docInfo);
			docMgmtService.checkInDocs(docInfoList);
		} 
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	
		
	}

	public String prepareContext(List<CertificateTracker> certificateTrackers)throws SmartOfficeException{
		final Context ctx = new Context(Locale.ENGLISH);
		
		SimpleDateFormat dt= new SimpleDateFormat("dd-MM-yyyy");
		for(CertificateTracker certificateTracker:certificateTrackers) {
		ctx.setVariable("fname", certificateTracker.getFname());
		ctx.setVariable("lname", certificateTracker.getLname());
		ctx.setVariable("score", certificateTracker.getScore());
		ctx.setVariable("date", certificateTracker.getIssuedDt());
		ctx.setVariable("location", certificateTracker.getLocation());
		ctx.setVariable("code", certificateTracker.getCertificateCode());
		
		
		 
		 
		}
		List<DocumentType> docTypes= docTypeHelper.findByDocTypeCode("CERTIFICATE");
		fileNameSuffix = ".pdf";
		fileNamePrefix = "certificate" + LocalDateTime.now();
		fileLocation = certificatePrintLocation + docTypes.get(0).getFirstFolderName() + fileNameSuffix;
		final String htmlContent = this.htmlTemplateEngine.process(docTypes.get(0).getTemplateName(), ctx);	
		return htmlContent;
		
	}
}
