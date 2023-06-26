package com.ss.smartoffice.sonotificationservice.payout;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.EmployeePayout;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;


@RestController
@RequestMapping(path = "/email-payslips")
@Service
public class EmployeeSalaryCreatedEventProcessor {

	@Value("${payout.url}")
	private String payoutUrl;

	@Autowired
	EmailService emailService;

	@Autowired
	CommonUtils commonUtils;

	public void processPayout(Event event) {
		System.out.println("kh-processPayout-came-here");
		try {
			if (event.getBusinessKeys() != null) {

				Integer id = Integer.parseInt(event.getBusinessKeys().get(0).getEmpPayoutId());

				System.out.println("kh-id-" + id);

				EmployeePayout employeePayout = commonUtils.getRestTemplate().getForObject(payoutUrl + id,
						EmployeePayout.class);

				emailService.sendMailWithAttachment(employeePayout);

			} else {
				throw new SmartOfficeException("Event Business key is empty ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}

	}
	
	@CrossOrigin(origins="*")
	@GetMapping("/{id}")
	public void processPayoutViaRest(@PathVariable(value = "id") String id) {
		try {
			
				EmployeePayout employeePayout = commonUtils.getRestTemplate().getForObject(payoutUrl + id,
						EmployeePayout.class);
				try {
				
					emailService.sendMailWithAttachment(employeePayout);
				} catch (SmartOfficeException soe) {
					soe.printStackTrace();
				}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
		

	}

}
