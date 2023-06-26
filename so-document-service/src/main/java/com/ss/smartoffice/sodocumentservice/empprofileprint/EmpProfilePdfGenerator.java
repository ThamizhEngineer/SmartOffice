package com.ss.smartoffice.sodocumentservice.empprofileprint;

import java.io.File;
import java.io.FileInputStream;
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
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.Applicant;

@Service
public class EmpProfilePdfGenerator {
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	DocMgmtService docMgmtService;
	
	@Value("${emp-profile.pdf.location}")
	private String empProfilePdfLocation;
	
	@Value("${emp.profile.url}")
	private String empProfileUrl;
	
	
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	
	@Autowired
	DocInfoHelper docInfoHelper;
	private static final String EMP_PROFILE_PRINT_TEMPLATE_NAME = "html/emp-profile";
	public void generatePdf(memployee memployeeFromRest) {
		// TODO Auto-generated method stub
		ITextRenderer renderer = new ITextRenderer();
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("empPhoto", memployeeFromRest.getDocLocation()+"/"+memployeeFromRest.getDocName());
//		ctx.setVariable("empPhoto", "/opt/so/data/docs/upload/EMP-OTHER/2019-09-30T10:54:34.228-3e6ba169-b74d-42cd-8903-1ea45edbe569");
		
		ctx.setVariable("empName", memployeeFromRest.getEmpName());
		ctx.setVariable("emailId", memployeeFromRest.getEmailId());
		ctx.setVariable("empCode", memployeeFromRest.getEmpCode());
		ctx.setVariable("dob", memployeeFromRest.getDob());
		ctx.setVariable("sex", memployeeFromRest.getGender());
		ctx.setVariable("mobNo", memployeeFromRest.getContactMobileNo());
		ctx.setVariable("curAddress", memployeeFromRest.getCurAddress());
		ctx.setVariable("permAddress", memployeeFromRest.getPermAddress());
		ctx.setVariable("maritalStatus", (memployeeFromRest.getMaritalStatus()=="Y"?"Married":"Single"));
		ctx.setVariable("passport", memployeeFromRest.getPassport());
		ctx.setVariable("drivingLicence", memployeeFromRest.getDrivingLicense());
		ctx.setVariable("hobbies", memployeeFromRest.getHobbies());
		ctx.setVariable("height", memployeeFromRest.getHeight());
		ctx.setVariable("weight", memployeeFromRest.getWeight());
		ctx.setVariable("eyePower", memployeeFromRest.getEyePower());
		ctx.setVariable("eyeLeftPower", memployeeFromRest.getEyeLeftPower());
		ctx.setVariable("eyeRightPower", memployeeFromRest.getEyeRightPower());
		ctx.setVariable("bloodGroup", memployeeFromRest.getBloodGroup());
		ctx.setVariable("gradeName", memployeeFromRest.getGradeName());
		ctx.setVariable("designation", memployeeFromRest.getDesigName());
		ctx.setVariable("n1Manager", memployeeFromRest.getManagerName());
		ctx.setVariable("n2Manager", memployeeFromRest.getReviewManagerName());
		ctx.setVariable("hr1Manager", memployeeFromRest.getHr1UsrGrpName());
		ctx.setVariable("hr2Manager", memployeeFromRest.getHr2UsrGrpName());
		ctx.setVariable("acc1Manager", memployeeFromRest.getIsAcc1UsrGrpName());
		ctx.setVariable("acc2Manager", memployeeFromRest.getIsAcc2UsrGrpName());
		ctx.setVariable("pfNo", memployeeFromRest.getPfNo());
		ctx.setVariable("esiNo", memployeeFromRest.getEsiNo());
		ctx.setVariable("uanNo", memployeeFromRest.getUanNo());
		ctx.setVariable("officeName", memployeeFromRest.getOfficeName());
		ctx.setVariable("deptName", memployeeFromRest.getDeptName());
		
		ctx.setVariable("empCategory", memployeeFromRest.getEmpCategory());
		ctx.setVariable("internalId", memployeeFromRest.getInternalId());
		ctx.setVariable("familyinfos", memployeeFromRest.getFamilyInfo());
		ctx.setVariable("educationinfos", memployeeFromRest.getEducationalInfo());
		ctx.setVariable("previousemploymentdetails", memployeeFromRest.getPreviousEmployDetails());
		ctx.setVariable("bankdetails",memployeeFromRest.getBankDetails());
		ctx.setVariable("prePfNo",memployeeFromRest.getPrePfNo());
		ctx.setVariable("preEsiNo",memployeeFromRest.getPreEsiNo());
		ctx.setVariable("preUanNo",memployeeFromRest.getUanNo());
		ctx.setVariable("contactNo",memployeeFromRest.getContactNo());
		ctx.setVariable("relaContNo2",memployeeFromRest.getRelaContNo2());
		ctx.setVariable("emailId",memployeeFromRest.getEmailId());
		ctx.setVariable("relation",memployeeFromRest.getRelation());
		final String htmlContent = this.htmlTemplateEngine.process(EMP_PROFILE_PRINT_TEMPLATE_NAME, ctx);		
		String fileNamePrefix = "emp-profile" + commonUtils.generateId();
		String fileNameSuffix = ".pdf";
		String fileLocation = empProfilePdfLocation + fileNamePrefix + fileNameSuffix;
		try {
		renderer.setDocumentFromString(htmlContent);
		renderer.layout();	

		renderer.createPDF(new FileOutputStream(fileLocation));
		renderer.finishPDF();
		
		MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix+fileNameSuffix,fileNamePrefix+fileNameSuffix,"", new FileInputStream(new File(fileLocation)));
		MultipartFile[] multipartFileArray = new MultipartFile[1];
		multipartFileArray[0]=multipartFile;
		List<DocInfo> docInfos= docMgmtService.uploadDocAsBinary("EMPLOYEE-PDF", multipartFileArray);
		String docPdfId="";
		for(DocInfo docInfo:docInfos) {
			docPdfId =docInfo.getDocId();
			
		}
		
		
		List<DocInfo> docInfosFromDb = documentManagementHelper.getDocInfoByDocId(docPdfId);
		memployeeFromRest.setEmpProfilePdfId(docPdfId);
		for(DocInfo docInfo :docInfosFromDb) {
			docInfo.setDocExtension("pdf");
		}
		docInfoHelper.saveAll(docInfosFromDb);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
	
		HttpEntity<memployee> request = new HttpEntity<memployee>(memployeeFromRest, headers);
		String applicantUrlForPatch=empProfileUrl+memployeeFromRest.getId()+"/app-pdf-emp-profile";
		System.out.println(applicantUrlForPatch);
		commonUtils.getRestTemplate().exchange(applicantUrlForPatch,HttpMethod.POST, request, memployee.class);
		
		
		List<DocMetadata> docMetadataList = new ArrayList<>();
		List<DocInfo> docInfoList = new ArrayList<>();
		DocInfo docInfo = new DocInfo(); 
		docInfo.setDocId(memployeeFromRest.getEmpProfilePdfId());
	
		DocMetadata docMetadata = new DocMetadata();
		docMetadata.setMdCode("employee-profile-id");
		docMetadata.setMdValue(memployeeFromRest.getId().toString());
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
