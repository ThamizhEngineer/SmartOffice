//package com.ss.smartoffice.soservice.event.employee;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.ss.smartoffice.shared.common.CommonUtils;
//import com.ss.smartoffice.shared.model.SmartOfficeException;
//import com.ss.smartoffice.shared.model.AuthUser;
////import com.ss.smartoffice.shared.model.Event;
//import com.ss.smartoffice.shared.model.employee.memployee;
//import com.ss.smartoffice.soservice.transaction.event.Event;
////import com.ss.smartoffice.soservice.event.EventGenerator;
//import com.ss.smartoffice.soservice.master.employee.EmployeeService;
//
//
//@Service
//public class EmployeeUpdateEventProcessor {
//	@Value("${user.url}")
//	private String userUrl;
//
//	@Autowired
//	EmployeeService employeeService;
//
////	@Autowired
////	EventGenerator eventGenerator;
//
//	@Autowired
//	CommonUtils commonUtils;

//	public Event processEmployeeUpdateEvent(Event event) {
//		try {

//			Map<String, String> keyValues = event.getKeyValues();

//			String employeeId = keyValues.get("memployee-id");
//			String employeeLoginId = keyValues.get("memployee-login-id");
//			Optional<memployee> optionalEmployee = employeeService.getEmployeeById(Integer.parseInt(employeeId));
//			memployee memployee = optionalEmployee.get();
//			RestTemplate restTemplate = commonUtils.getRestTemplate();
//			AuthUser user = restTemplate.getForObject(userUrl + "/" + employeeLoginId, AuthUser.class);
//			if (!user.getUserName().equals(memployee.getContactMobileNo())) {
//				user.setUserName(memployee.getContactMobileNo());
//				user.setEmailId(memployee.getEmailId());
//				user.setEmpCode(memployee.getEmpCode());
//				user.setEmpDesignation(memployee.getDesigName());
//				user.setFirstName(memployee.getEmpName());
////				restTemplate.put(userUrl + "/" + employeeLoginId, user, AuthUser.class);
//
//				Event notificationEvent = new Event();
//				notificationEvent.setId((int) commonUtils.getNextNumber());
//				notificationEvent.setName(Event.EventTypes.EmployeeUpdateEvent);
//				Map<String, String> keyValues1 = new HashMap<String, String>();
//				keyValues1.put("mobile-number", memployee.getContactMobileNo());
//				keyValues1.put("memployee-id", Integer.toString(memployee.getId()));
//				notificationEvent.setKeyValues(keyValues1);
//				eventGenerator.generateNofiticationEvent(notificationEvent);

//			}

//			if ((user.getEmailId() != null && memployee.getEmailId() != null
//					&& !(user.getEmailId().equals(memployee.getEmailId())))
//					|| (user.getEmpCode() != null && memployee.getEmpCode() != null
//							&& !(user.getEmpCode().equals(memployee.getEmpCode())))
//					|| (user.getFirstName() != null && memployee.getEmpName() != null
//							&& !(user.getFirstName().equals(memployee.getEmpName())))
//					|| (user.getEmpDesignation() != null && memployee.getDesigName() != null
//							&& !(user.getEmpDesignation().equals(memployee.getDesigName())))) {
//				System.out.println("in getEmailId");
//				user.setEmailId(memployee.getEmailId());
//				user.setFirstName(memployee.getEmpName());
//				user.setEmpCode(memployee.getEmpCode());
//				user.setEmpDesignation(memployee.getDesigName());
//				restTemplate.put(userUrl + "/" + employeeLoginId, user, AuthUser.class);
//				System.out.println(user);
//			}
//			
//		} catch (Exception e) {
//			throw new SmartOfficeException(e.getMessage());
//		}
//		
		
//		return event;
//
//	}
//
//}
