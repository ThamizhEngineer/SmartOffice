package com.ss.smartoffice.sodocumentservice.printer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

import com.lowagie.text.DocumentException;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.dm.DocInfoHelper;
import com.ss.smartoffice.shared.dm.DocTypeHelper;
import com.ss.smartoffice.shared.dm.DocumentManagementHelper;
import com.ss.smartoffice.shared.model.dm.DocInfo;
import com.ss.smartoffice.shared.model.dm.DocMetadata;
import com.ss.smartoffice.shared.model.dm.DocumentType;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.Applicant;
import com.ss.smartoffice.sodocumentservice.model.ExpenseClaim;
import com.ss.smartoffice.sodocumentservice.model.ExpenseClaimBill;
@Service
public class ExpenseClaimPdfGenerator {
	@Autowired
	private TemplateEngine htmlTemplateEngine;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	DocMgmtService docMgmtService;
	@Value("${expense.url}")
	private String expenseUrl;
	@Value("docs.base.location")
	private String expensePrintLocation;
	@Autowired
	DocumentManagementHelper documentManagementHelper;
	@Autowired
	DocTypeHelper docTypeHelper;
	
	@Autowired
	DocInfoHelper docInfoHelper;
	String fileLocation=null;
	String fileNamePrefix=null;
	public void generatePdf(ExpenseClaim expenseClaim)throws SmartOfficeException{
		String htmlContent=prepareContext(expenseClaim);
		formMetadta(htmlContent, expenseClaim);
	}
	public String prepareContext(ExpenseClaim expenseClaim)throws SmartOfficeException{
		
		final Context ctx = new Context(Locale.ENGLISH);
		ctx.setVariable("expenseClaimCode", expenseClaim.getExpenseClaimCode());
		ctx.setVariable("isJobBased", expenseClaim.getIsJobBased());
		ctx.setVariable("jobId", expenseClaim.getJobId());
		ctx.setVariable("jobCode", expenseClaim.getJobCode());
		ctx.setVariable("jobName", expenseClaim.getJobName());
		ctx.setVariable("costCenterId", expenseClaim.getCostCenterId());
		ctx.setVariable("costCenterCode", expenseClaim.getCostCenterCode());
		ctx.setVariable("costCenterName", expenseClaim.getCostCenterName());
		ctx.setVariable("appliedDt", expenseClaim.getAppliedDt());
		ctx.setVariable("appliedFname", expenseClaim.getAppliedEmpFName());
		ctx.setVariable("empRemarks", expenseClaim.getEmpRemarks());
		ctx.setVariable("expenseClaimStatus", expenseClaim.getExpenseClaimStatus());
		ctx.setVariable("employeeId", expenseClaim.getEmployeeId());
		ctx.setVariable("employeeCode", expenseClaim.getEmployeeCode());
		ctx.setVariable("employeeFName", expenseClaim.getEmployeeFName());
		ctx.setVariable("employeeLName", expenseClaim.getEmployeeLName());
		ctx.setVariable("expenseClaimAmount", expenseClaim.getExpenseClaimAmount());
		ctx.setVariable("n1EmpFName", expenseClaim.getN1EmpFName());
		ctx.setVariable("expenseClaimBills", expenseClaim.getExpenseClaimBills());
		ctx.setVariable("totalEntitledAmount", expenseClaim.getTotalEntitledAmount());
		List<DocumentType> docTypes= docTypeHelper.findByDocTypeCode("EXPENSE-CLAIM-PRINTOUT");
		fileNamePrefix = "expense-claim-" + LocalDateTime.now()+".pdf";
		fileLocation = expensePrintLocation + docTypes.get(0).getFirstFolderName();
		final String htmlContent = this.htmlTemplateEngine.process(docTypes.get(0).getTemplateName(), ctx);	
		return htmlContent;
		
	}
	public List<DocInfo> formMetadta(String htmlContent,ExpenseClaim expenseClaim)throws SmartOfficeException{
		ITextRenderer renderer = new ITextRenderer();
		try {
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();	

			renderer.createPDF(new FileOutputStream(fileLocation));
			renderer.finishPDF();
			
			MultipartFile multipartFile = new MockMultipartFile(fileNamePrefix,fileNamePrefix,"", new FileInputStream(new File(fileLocation)));
			MultipartFile[] multipartFileArray = new MultipartFile[1];
			multipartFileArray[0]=multipartFile;
			List<DocInfo> docInfos= docMgmtService.uploadDocAsBinary("EXPENSE-CLAIM-PRINTOUT", multipartFileArray);
			String docPdfId="";
			for(DocInfo docInfo:docInfos) {
				docPdfId =docInfo.getDocId();
				
			}
			
			
			List<DocInfo> docInfosFromDb = documentManagementHelper.getDocInfoByDocId(docPdfId);
			expenseClaim.setExpPdfId(docPdfId);
			for(DocInfo docInfo :docInfosFromDb) {
				docInfo.setDocExtension("pdf");
			}
			docInfoHelper.saveAll(docInfosFromDb);
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
		
			HttpEntity<ExpenseClaim> request = new HttpEntity<ExpenseClaim>(expenseClaim, headers);
			String expenseUrlForPatch=expenseUrl+expenseClaim.getId()+"/exp-pdf";
			commonUtils.getRestTemplate().exchange(expenseUrlForPatch,HttpMethod.POST, request, ExpenseClaim.class);
			
			
			List<DocMetadata> docMetadataList = new ArrayList<>();
			List<DocInfo> docInfoList = new ArrayList<>();
			DocInfo docInfo = new DocInfo(); 
			docInfo.setDocId(docPdfId);
		
			DocMetadata docMetadata = new DocMetadata();
			docMetadata.setMdCode("expense-claim-id");
			docMetadata.setMdValue(expenseClaim.getId().toString());
			docMetadataList.add(docMetadata);
			
			
			docInfo.setMetadataList(docMetadataList);
		
			docInfoList.add(docInfo);
			docMgmtService.checkInDocs(docInfoList);
		
		
			} catch (DocumentException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}
	
	
}
