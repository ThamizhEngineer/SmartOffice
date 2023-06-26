package com.ss.smartoffice.sonotificationservice.employee;

import java.util.Map;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.sonotificationservice.model.User;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
@Service
public class EmployeePasswordResetEventProcessor {

	@Value("${user.url}")
	private String userUrl;
	
	@Autowired
	EmployeePasswordResetService employeePasswordResetService;
	
	@Autowired
	CommonUtils commonUtils;
	
	public void processEmployeePasswordUpdate(Event event) {
		try {
			if (event.getNotificationKeys() != null) {
				
//				String userId=event.getNotificationKeys().get(0).getEmpLoginId();
//				User user= commonUtils.getRestTemplate().getForObject(userUrl+ "/"+ userId, 
//						User.class);
//				System.out.println(user);
				
//					employeePasswordResetService.sendPasswordEmployeeUpdate(user);
				
			}else {
				throw new SmartOfficeException("event keyvalues is empty ");
			} 	
		} catch (Exception e) {
			e.printStackTrace();
			throw new SmartOfficeException(e.getMessage());
		}
		
	} 
		
	}
	
	

