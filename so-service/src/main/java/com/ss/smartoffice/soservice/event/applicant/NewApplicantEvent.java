//package com.ss.smartoffice.soservice.event.applicant;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Service;
//
//import com.ss.smartoffice.shared.common.CommonUtils;
//import com.ss.smartoffice.shared.model.SmartOfficeException;
//
//import com.ss.smartoffice.shared.model.applicant.Applicant;
//import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantRepository;
//import com.ss.smartoffice.soservice.transaction.recruitment.ApplicantService;
//import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
//import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
//import com.ss.smartoffice.shared.model.AuthUser;
//import com.ss.smartoffice.shared.model.AuthUserRole;
//import com.ss.smartoffice.soservice.transaction.event.*;
//import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;
//@Service
//public class NewApplicantEvent {
//
//	@Value("${user.url}")
//	private String userUrl;
//	
//	@Value("${userrole.url}")
//	private String userRoles;
//	
//	@Autowired
//	ApplicantService applicantService;
//	
//	@Autowired
//	ApplicantRepository applicantRepository;
//	
//	@Autowired
//	AuthUserRepository AuthUserRepository;
//	
//	 @Autowired
//	 EventPublisherService eventPublisherService;
//	 
//	 @Autowired
//	 CommonUtils commonUtils;
//	 
//	public Event process(Event event) {
//		try {
//			
//
////			Map<String, String> keyValues = event.getKeyValues();
//
//			String applicantId = event.getBusinessKeys().get(0).getApplicantId();
//			Optional<Applicant> optionalApplicant = applicantService.getApplicantById(Integer.parseInt(applicantId));
//			Applicant applicant = optionalApplicant.get();
//			AuthUser user = new AuthUser();
//			user.setApplicantId(String.valueOf(applicant.getId()));
//			user.setEmployeeId(String.valueOf(applicant.getId()));
//			user.setFirstName(applicant.getFirstName());
//			user.setEmailId(applicant.getContactEmailId());
//			user.setEmpCode(applicant.getApplicantCode());
//			user.setUserName(applicant.getFirstName());
//			user.setPassword(applicant.getDob().replaceAll("-", ""));
//			user.setAppClientId("pothigai-power");
//			user.setGroupId("1");
//			user.setUserType("E");
//			
//			// ...
//
//			HttpHeaders headers = new HttpHeaders();
//			headers.set("Authorization",commonUtils.getLoggedinAppToken() );      
//
//			HttpEntity<AuthUser> request = new HttpEntity<AuthUser>(user, headers);
//
//			AuthUser savedUser = commonUtils.getRestTemplate().postForObject(userUrl, request, AuthUser.class);
//			List<AuthUserRole>authUserRoles= new ArrayList<AuthUserRole>();
//			AuthUserRole authUserRole= new AuthUserRole();
//			authUserRole.setAuthUserId(String.valueOf(savedUser.getId()));
//			authUserRole.setAuthRoleId("63");
//			authUserRoles.add(authUserRole);
//			savedUser.setAuthUserRoles(authUserRoles);
//			
//				AuthUserRepository.save(savedUser);
//			
//			applicantRepository.save(applicant);
//			
//			
//			
//		
//			
//		
//			//-------------------------------------------------
//			Event notificationEvent = new Event();
//			
//			notificationEvent.setName(Event.EventTypes.ApplicantAddEvent);
//			notificationEvent.setCategory(EventCategory.NotificationEvent);
//			List<NotificationKey>notificationKeyValues= new ArrayList<NotificationKey>();
//			NotificationKey notificationKeyValue= new NotificationKey();
//			notificationKeyValue.setMobileNumber(applicant.getContactMobileNo());
//			notificationKeyValue.setApplicantId(Integer.toString(applicant.getId()));
//			notificationKeyValue.setAppToken(commonUtils.getLoggedinAppToken());
//			notificationKeyValue.setSendEmail(applicant.getContactEmailId());
//			
//			
//			eventPublisherService.pushEvent(event);
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			new SmartOfficeException(e.getMessage());
//		}
//		return event;
//
//	}
//
//	
//}
