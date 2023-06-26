package com.ss.smartoffice.sodocumentservice.empprofileprint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.smartoffice.sodocumentservice.empprofileprint.EmpProfilePdfGenerator;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.sodocumentservice.model.Applicant;

@RestController
@Service
@RequestMapping(path="document/profile/employees")
public class EmpProfilePdfService {
	@Value("${emp.profile.url}")
	private String empProfileUrl;
	

	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	EmpProfilePdfGenerator empProfilePdfGenerator;
	
	@GetMapping("/{id}/generate-emp-profile-pdf")
	public void generatePurchaseOrderPdf(@PathVariable(value="id")int id) throws SmartOfficeException{
		memployee employee = new memployee();
		HttpHeaders headers = new HttpHeaders();
		
		headers.set("Authorization",commonUtils.getLoggedinAppToken() ); 
		HttpEntity<memployee> request = new HttpEntity<memployee>(employee, headers);
		ResponseEntity<memployee> employeeEntity = commonUtils.getRestTemplate().exchange(empProfileUrl+ id,
				HttpMethod.GET, request,memployee.class);
		
		memployee memployeeFromRest =employeeEntity.getBody();
		empProfilePdfGenerator.generatePdf(memployeeFromRest);
	}
}
