package com.ss.smartoffice.sonotificationservice.JobEvent;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;


import com.ss.smartoffice.sonotificationservice.model.Partner;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

@Service
public class JobConfirmationEventProcessor {
	@Value("${client.url}")
	private String clientUrl;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	JobConfirmationEmailService jobConfirmationEmailService;

	public void processJobConfUser(Event event) {
		try {

			if (event.getNotificationKeys() != null) {
				
//				String clientId = event.getNotificationKeys().get(0).getClientId();
//				Partner partner = commonUtils.getRestTemplate().getForObject(clientUrl  + clientId, Partner.class);
//				System.out.println(partner);
			
//					jobConfirmationEmailService.sendMailNewUser(partner);
				
			} else {
				throw new SmartOfficeException("event keyvalues is empty ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getLocalizedMessage());
		}
	}
}
