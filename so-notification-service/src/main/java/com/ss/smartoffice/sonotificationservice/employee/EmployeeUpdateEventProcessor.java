package com.ss.smartoffice.sonotificationservice.employee;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

@Service
public class EmployeeUpdateEventProcessor {
	
	
	@Value("${employees.url}")
	private String employeesUrl;

	@Autowired
	EmployeeUpdateEmailService employeeUpdateEmailService;
	

	@Autowired
	CommonUtils commonUtils;
	
	public void processEmployeeUpdate(Event event) {
		try {
			if (event.getNotificationKeys() != null) {
			
//				String employeeId = event.getNotificationKeys().get(0).getEmployeeId();
//				System.out.println(employeeId);
				
//				memployee employee = commonUtils.getRestTemplate().getForObject(employeesUrl + "/" + employeeId,
//						memployee.class);
//				System.out.println("hi"+employee);
				
//					employeeUpdateEmailService.sendMailEmployeeUpdate(employee);
				
			} else {
				throw new SmartOfficeException("event keyvalues is empty ");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		

	}

}
