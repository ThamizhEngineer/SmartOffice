package com.ss.smartoffice.sonotificationservice.applicant;

import java.util.Map;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;

import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
import com.ss.smartoffice.shared.model.applicant.Applicant;

@Service
public class NewApplicantEventProcess {

	@Value("${applicant.url}")
	private String applicantUrl;

	@Autowired
	NewApplicantEmailService newApplicantEmailService;

	@Autowired
	CommonUtils commonUtils;
	
	

	public void processNewApplicant(Event event) {
		try {
			
			if (event.getNotificationKeys() != null) {
				
//				String applicantId = event.getNotificationKeys().get(0).getApplicantId();
				
//				Applicant applicant1 =new Applicant();
//				HttpHeaders headers = new HttpHeaders();
//				
//				System.out.println("memployee Testing"+applicant1);
//				
//				headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
//				HttpEntity<Applicant> request = new HttpEntity<Applicant>(applicant1, headers);
//				ResponseEntity<Applicant> applicant = commonUtils.getRestTemplate().exchange(applicantUrl+applicantId,
//						HttpMethod.GET, request,Applicant.class);
//		
//				Applicant applicantGet =applicant.getBody();
//				
//				System.out.println("applicantGet"+applicantGet);
				
				
			} else {
				throw new SmartOfficeException("event keyvalues is empty ");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

	}

}
