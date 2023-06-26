package com.ss.smartoffice.sodocumentservice.recruitment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.Applicant;
import com.ss.smartoffice.sodocumentservice.model.PurchaseOrder;

@Service
public class ApplicantPdfGenerator {
	
	private static final String APPLICANT_TEMPLATE_NAME = "html/applicant";
	
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	DocMgmtService docMgmtService;
	
	@Value("${applicant.pdf.location}")
	private String applicantPdfLocation;
	
	@Value("${applicant.url}")
	private String applicantUrl;

	@Autowired
	DocumentManagementHelper documentManagementHelper;
	
	@Autowired
	DocInfoHelper docInfoHelper;
	
	public void generatePdf(Applicant applicant) {
		ITextRenderer renderer = new ITextRenderer();
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("applicantName", applicant.getApplicantName());
		ctx.setVariable("curAddress", applicant.getCurAddress());
		ctx.setVariable("permAddress", applicant.getPermAddress());
		ctx.setVariable("contactNo", applicant.getContactNo());
		ctx.setVariable("contactMobileNo", applicant.getContactMobileNo());
		
		ctx.setVariable("appPhoto", applicant.getDocLocation()+"/"+applicant.getDocName());
//		ctx.setVariable("appPhoto", "/opt/so/data/docs/upload/APPLICANT-PDF/2019-02-17T22:43:08.921-343d282b-8546-46ed-aa24-c10a90f110f8");
		ctx.setVariable("maritalStatus", (applicant.getMaritalStatus()=="Y"?"Married":"Single"));
		ctx.setVariable("spouse", applicant.getSpouseName()+"-"+applicant.getSpouseOccup());
		ctx.setVariable("noOfChildren", applicant.getNoOfChildren());
		ctx.setVariable("noOfDependant", applicant.getNoOfDependant());
		ctx.setVariable("height", applicant.getHeight());
		ctx.setVariable("weight", applicant.getWeight());
		ctx.setVariable("eyeLeftPower", applicant.getEyeLeftPower());
		ctx.setVariable("eyeRightPower", applicant.getEyeRightPower());
		ctx.setVariable("bloodGroup", applicant.getBloodGroup());
		ctx.setVariable("identifnMrk1", applicant.getIdentifnMrk1());
		ctx.setVariable("identifnMrk2", applicant.getIdentifnMrk2());
		ctx.setVariable("physicalChalng", applicant.getPhysicalChalng());
		ctx.setVariable("monthlyRemun", applicant.getMonthlyRemun());
		ctx.setVariable("annualRemun", applicant.getAnnualRemun());
		ctx.setVariable("takeHomeRemun", applicant.getTakeHomeRemun());
		ctx.setVariable("nextIncrRemun", applicant.getNextIncrRemun());
		ctx.setVariable("benefitsRemun", applicant.getBenefitsRemun());
		ctx.setVariable("grossRemun", applicant.getGrossRemun());
		ctx.setVariable("grossOtherRemun", applicant.getGrossOtherRemun());	
		ctx.setVariable("languages", applicant.getLanguages());
		ctx.setVariable("educationInfos", applicant.getEducationalInfo());
		ctx.setVariable("academicAcheivs", applicant.getAcademicAcheiv());
		ctx.setVariable("previousEmployDetails", applicant.getPreviousEmployDetails());
		final String htmlContent = this.htmlTemplateEngine.process(APPLICANT_TEMPLATE_NAME, ctx);		
		String fileNamePrefix = "applicant-" + commonUtils.generateId();
		String fileNameSuffix = ".pdf";
		String fileLocation = applicantPdfLocation + fileNamePrefix + fileNameSuffix;
		try {
		renderer.setDocumentFromString(htmlContent);
		renderer.layout();	

		renderer.createPDF(new FileOutputStream(fileLocation));
		renderer.finishPDF();
		
		MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix+fileNameSuffix,fileNamePrefix+fileNameSuffix,"", new FileInputStream(new File(fileLocation)));
		MultipartFile[] multipartFileArray = new MultipartFile[1];
		multipartFileArray[0]=multipartFile;
		List<DocInfo> docInfos= docMgmtService.uploadDocAsBinary("APPLICANT-PDF", multipartFileArray);
		String docPdfId="";
		for(DocInfo docInfo:docInfos) {
			docPdfId =docInfo.getDocId();
			
		}
		
		
		List<DocInfo> docInfosFromDb = documentManagementHelper.getDocInfoByDocId(docPdfId);
		applicant.setAppPdfId(docPdfId);
		for(DocInfo docInfo :docInfosFromDb) {
			docInfo.setDocExtension("pdf");
		}
		docInfoHelper.saveAll(docInfosFromDb);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
	
		HttpEntity<Applicant> request = new HttpEntity<Applicant>(applicant, headers);
		String applicantUrlForPatch=applicantUrl+applicant.getId()+"/app-pdf";
		commonUtils.getRestTemplate().exchange(applicantUrlForPatch,HttpMethod.POST, request, Applicant.class);
		
		
		List<DocMetadata> docMetadataList = new ArrayList<>();
		List<DocInfo> docInfoList = new ArrayList<>();
		DocInfo docInfo = new DocInfo(); 
		docInfo.setDocId(applicant.getAppPdfId());
	
		DocMetadata docMetadata = new DocMetadata();
		docMetadata.setMdCode("applicant-id");
		docMetadata.setMdValue(applicant.getId().toString());
		docMetadataList.add(docMetadata);
		
		
		docInfo.setMetadataList(docMetadataList);
	
		docInfoList.add(docInfo);
		docMgmtService.checkInDocs(docInfoList);
	
	
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
