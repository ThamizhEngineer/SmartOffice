package com.ss.smartoffice.sodocumentservice.recruitment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sodocumentservice.model.Applicant;
import com.ss.smartoffice.sodocumentservice.model.PurchaseOrder;

@RestController
@RequestMapping(path="document/recruitments/applicants")
public class ApplicantService {
	
	@Value("${applicant.url}")
	private String applicantUrl;
	

	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	ApplicantPdfGenerator applicantPdfGenerator;
	
	@GetMapping("/{id}/generate-pdf")
	public void generatePurchaseOrderPdf(@PathVariable(value="id")int id) throws SmartOfficeException{
		
		Applicant applicant = new Applicant();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
		
		HttpEntity<Applicant> request = new HttpEntity<Applicant>(applicant, headers);
		ResponseEntity<Applicant> applicantEntity = commonUtils.getRestTemplate().exchange(applicantUrl+ id,
				HttpMethod.GET, request,Applicant.class);
		
		Applicant applicantFromRest =applicantEntity.getBody();
		applicantPdfGenerator.generatePdf(applicantFromRest);
		
	}

}
