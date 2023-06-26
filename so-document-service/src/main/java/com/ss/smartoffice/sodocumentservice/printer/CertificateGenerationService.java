package com.ss.smartoffice.sodocumentservice.printer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sodocumentservice.DocumentManagementService.DocMgmtService;
import com.ss.smartoffice.sodocumentservice.model.CertificateTracker;
import com.ss.smartoffice.sodocumentservice.model.ExpenseClaim;

@RestController
@RequestMapping(path="document/prints/certificates")
public class CertificateGenerationService {
	@Value("${certificate-tracker.url}")
	private String certificateUrl;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	CertificateGeneration certificateGeneration;
	
	@Autowired
	DocMgmtService docMgmtService;
	@GetMapping("/{incidentApplicantId}/generate-certificate")
	public String generateCertificate(@PathVariable(value="incidentApplicantId")Integer incidentApplicantId) throws SmartOfficeException{
		certificateGeneration.generateCertificate(doGetCertificate(incidentApplicantId.toString()));
		return incidentApplicantId.toString();
	}
	
	@GetMapping("/{docId}/download-certificate")
	public  ResponseEntity<InputStreamResource> getCertificateByDocId(@PathVariable("docId") String docId)
			throws SmartOfficeException {
				return docMgmtService.getDocByDocId(docId);

	}
	
	private List<CertificateTracker> doGetCertificate(String incidentApplicantId)throws SmartOfficeException{
		CertificateTracker certificateTracker = new CertificateTracker();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
		HttpEntity<CertificateTracker>request=new HttpEntity<CertificateTracker>(certificateTracker,headers);
		System.out.println(certificateUrl);
		String url=certificateUrl+incidentApplicantId+"/incident-applicant";
		ResponseEntity<CertificateTracker[]> certificateTrackerEntity = commonUtils.getRestTemplate().exchange(url,
				HttpMethod.GET, request,CertificateTracker[].class);
		List<CertificateTracker> certificateTrackerFromRest =Arrays.asList(certificateTrackerEntity.getBody());
		
		return certificateTrackerFromRest;
	}
}
