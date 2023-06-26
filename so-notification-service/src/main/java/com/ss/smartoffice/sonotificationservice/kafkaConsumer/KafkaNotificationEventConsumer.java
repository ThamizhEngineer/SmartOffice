package com.ss.smartoffice.sonotificationservice.kafkaConsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUserSummary;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.interceptor.AuthUserRepo; 
import com.ss.smartoffice.sonotificationservice.Announcement.Announcement;
import com.ss.smartoffice.sonotificationservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.sonotificationservice.payout.EmployeeSalaryCreatedEventProcessor;
import com.ss.smartoffice.shared.model.UserGroupEmployeeMapping;
import com.ss.smartoffice.shared.model.employee.memployee;

import com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule.EventNotificationRule;
import com.ss.smartoffice.sonotificationservice.seed.eventnotificationrule.EventNotificationRuleRepository;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
import com.ss.smartoffice.sonotificationservice.transaction.event.EventRepository;
import com.ss.smartoffice.sonotificationservice.transaction.event.EventService;
import com.ss.smartoffice.sonotificationservice.transaction.event.NotificationKey;
import com.ss.smartoffice.sonotificationservice.transaction.event.NotificationKeyValueRepository; 

@Service
public class KafkaNotificationEventConsumer {
	@Autowired
	EventNotificationRuleRepository ntfnRuleRepository;
	@Value("${usergropsemployee.url}")
	private String usergropsemployeeUrl;

	@Autowired
	AuthUserRepo authuserRepo;
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	EventService eventService;

	@Value("${employee.url}")
	private String employeeUrl;

	@Value("${announcement.url}")
	private String announcementUrl;

	@Value("${middle-manager.url}")
	private String middleUrl;

	@Value("${high-manager.url}")
	private String highUrl;
	@Autowired
	EventRepository eventRepository;
	@Value("${docs.email.template.location}")
	private String templateLocation;

	@Autowired
	EventPublisherService eventPublisherService;

	@Autowired
	NotificationKeyValueRepository notificationKeyValueRepository;

	@Autowired
	EmployeeSalaryCreatedEventProcessor employeeSalaryCreatedEventProcessor;

	private static Logger log = LoggerFactory.getLogger(KafkaNotificationEventConsumer.class);
	public Event processNotificationRule(Event event, EventNotificationRule ntfnRule) throws SmartOfficeException, IOException {
		
		log.debug("In processNotificationRule -- "+event.getName()+"("+event.getId()+")", event, ntfnRule);
		String appToken = commonUtils.setAuthenticationContext(event.getContextAuthUserId(),"async"); 
		Map<String, String> eveMap = new HashMap<String, String>();
		List<AuthUserSummary> authUsers = new ArrayList<AuthUserSummary>();

		if (event.getBusinessKeys() != null || event.getNotificationKeys() != null || event.getKeyValues() != null) {
			eveMap = event.formKeyValues();
		}
		
		if (ntfnRule.getIsCustom().equals("Y")) {
			handleCustomNotification(event,appToken);
		}else {
			authUsers = identifyRecipients(event, ntfnRule, eveMap, appToken);
		}  		
		
		//----------------		
		List<NotificationKey> notificationKeys = new ArrayList<NotificationKey>();
		if (authUsers!=null && !authUsers.isEmpty()) {
			for (AuthUserSummary user : authUsers) {
				notificationKeys = setNotificationPayload(event, ntfnRule, eveMap, user);
				event.setNotificationKeys(notificationKeys);
				pushEventToChannelTopic(event, ntfnRule);
			}
		}else {
			log.debug("In processNotificationRule --  AuthUsers is empty");
		}
		return event;
	}

	
	private List<AuthUserSummary> identifyRecipients(Event event, EventNotificationRule ntfnRule,Map<String, String> eveMap,String appToken) {
		List<AuthUserSummary> authUsers = new ArrayList<AuthUserSummary>();
		
		try {
			eveMap = event.formKeyValues();
			log.debug("identifyRecipients -- "+event.getName()+"("+event.getId()+")", eveMap, appToken );

			if (ntfnRule.getKnowRecipientDetails().equals("Y")) {
				System.out.println("161603 - NOTE");
				// To -do
				// form a dummy AuthUser with email, mobile and userId in event pay load
				// don't save AuthUserSummary
				// authUsers.add(authUser)
				eveMap = event.formKeyValues();
			} 
			else if (ntfnRule.getRecipientUserGpKey() != null && !ntfnRule.getRecipientUserGpKey().isEmpty()) { 
				String userGroupId = (String) eveMap.get(ntfnRule.getRecipientUserGpKey());
				List<UserGroupEmployeeMapping> userGroupEmployeeMappings = fetchUserGrpEmpMapping(appToken, userGroupId); 
				for (UserGroupEmployeeMapping userGroupEmployeeMapping : userGroupEmployeeMappings) {
					authUsers.add(userGroupEmployeeMapping.getAuthUser());
				}
				eveMap = event.formKeyValues();
			} 
			else if (ntfnRule.getRecipientEmployeeKey() != null && !ntfnRule.getRecipientEmployeeKey().isEmpty()) { 
				String value = (String) eveMap.get(ntfnRule.getRecipientEmployeeKey());
				
				List<AuthUserSummary> listFromDB = authuserRepo.findByEmployeeId(value);
				if(listFromDB!=null & listFromDB.size()>0) {
					authUsers.add(listFromDB.get(0));				
				}
				else{
					log.warn("identifyRecipients -- "+event.getName()+"("+event.getId()+")"," No AuthUser found for the employee("+value+") ");
				}
			} 
			else if (ntfnRule.getRecipientCustomerKey() != null && !ntfnRule.getRecipientCustomerKey().isEmpty()) {
				String value = (String) eveMap.get(ntfnRule.getRecipientCustomerKey());
				AuthUserSummary authUser = new AuthUserSummary();
				if(eveMap.get("bk.applicantId")!=null) {
					 authUser = authuserRepo.findByApplicantId(value).get(0);
				}else {
					 authUser = authuserRepo.findByPartnerId(value).get(0);
				}			
				authUsers.add(authUser);
			} 
			else {
				log.warn("User Key missing in event payload");
				throw new SmartOfficeException("User Key missing in event payload");
			}
		} catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+")",e);
		}
		return authUsers;
	}


	private void handleCustomNotification(Event event,String appToken) {	

		log.debug("handleCustomNotification -- "+event.getName()+"("+event.getId()+")", appToken ); 
		switch (event.getName()) {
		case AnnouncementEvent:
			if (event.getPayload() != null) {
				String payload = event.getPayload();
				String announcementId = event.getEventKeyValues().get(0).getValue();			
				Announcement announcement = fetchAnnouncementRest(appToken, announcementId);

				if (announcement.getAnnouncementCategory().equals("All")) {
					List<memployee> memployee = fetchEmployeeRest(appToken);

					for (memployee emp : memployee) {
						List<NotificationKey> emailnotificationKey = new ArrayList<NotificationKey>();
						NotificationKey notificationKey = new NotificationKey();
						System.out.println(emp.getEmailId());
						notificationKey.setEmailId(emp.getEmailId());

						notificationKey.setEmailMessage(announcement.getMessage());
						notificationKey.setEmailSubject(announcement.getSubject());
						emailnotificationKey.add(notificationKey);
						event.setNotificationKeys(emailnotificationKey);
						eventPublisherService.pushEventDirectly(event, "email-topic");
					}
				} else if (payload.contains("Manager")) {
		
					List<memployee> memployee = fetchEmployeeRest(appToken);
					for (memployee emp : memployee) {
						List<NotificationKey> emailnotificationKey = new ArrayList<NotificationKey>();
						NotificationKey notificationKey = new NotificationKey();
						notificationKey.setEmailId(emp.getEmailId());
						notificationKey.setEmailMessage(announcement.getMessage());
						notificationKey.setEmailSubject(announcement.getSubject());
						emailnotificationKey.add(notificationKey);
						event.setNotificationKeys(emailnotificationKey);
						eventPublisherService.pushEventDirectly(event, "email-topic");
					}

				} else if (payload.contains("Senior Management")) {
			
					List<memployee> memployee = fetchEmployeeRest(appToken);
					for (memployee emp : memployee) {
						List<NotificationKey> emailnotificationKey = new ArrayList<NotificationKey>();
						NotificationKey notificationKey = new NotificationKey();
						notificationKey.setEmailId(emp.getEmailId());
						notificationKey.setEmailMessage(announcement.getMessage());
						notificationKey.setEmailSubject(announcement.getSubject());
						emailnotificationKey.add(notificationKey);
						event.setNotificationKeys(emailnotificationKey);
						eventPublisherService.pushEventDirectly(event, "email-topic");
					}
				}
			} 
			break;
		case EmployeeSalaryCreatedEvent:
			employeeSalaryCreatedEventProcessor.processPayout(event);
			break;	
		default:
			log.warn("handleCustomNotification -- "+event.getName()+"("+event.getId()+")","Custom event type but handler not available");
			break;
		}
	}
	
	private Event pushEventToChannelTopic(Event event, EventNotificationRule ntfnRule) {
		if (ntfnRule.getSendSms().equals("Y")) {
			log.debug("pushEventToChannelTopic -- "+event.getName()+"("+event.getId()+")","Pushing directly to sms topic");
			eventPublisherService.pushEventDirectly(event, "sms-topic");
		}
		if (ntfnRule.getSendEmail().equals("Y")) {
			log.debug("pushEventToChannelTopic -- "+event.getName()+"("+event.getId()+")","Pushing directly to email topic");
			eventPublisherService.pushEventDirectly(event, "email-topic");
		}
		if (ntfnRule.getSendInAppNotfn().equals("Y")) {
			log.debug("pushEventToChannelTopic -- "+event.getName()+"("+event.getId()+")","Pushing directly to inapp notification topic");
			eventPublisherService.pushEventDirectly(event, "inapp-notfn-topic");
		}
		if (ntfnRule.getSendDash().equals("Y")) {
			log.debug("pushEventToChannelTopic -- "+event.getName()+"("+event.getId()+")","Pushing directly to dash metric topic");
			eventPublisherService.pushEventDirectly(event, "dash-metric-topic");
		}
		return event;
	}
	
	private List<NotificationKey> setNotificationPayload(Event event, EventNotificationRule ntfnRule,
			Map<String, String> eveMap, AuthUserSummary user) {
		NotificationKey notifKeyInDb = new NotificationKey();
		List<NotificationKey> notificationKeys = new ArrayList<NotificationKey>();

		notifKeyInDb.setEventId(String.valueOf(event.getId()));
		notifKeyInDb.setMobileNumber(user.getMobileNumber());
		notifKeyInDb.setEmailId(user.getEmailId());
		notifKeyInDb.setUserId(user.getId() + "");
//		notifKeyInDb = notificationKeyValueRepository.save(notifKeyInDb); 
		if (ntfnRule.getSendSms().equals("Y")) {
			eveMap = event.formKeyValues();
			notifKeyInDb.setSmsMessage(CommonUtils.replacePlaceHolders(ntfnRule.getSmsMessage(), eveMap));
		}
		if (ntfnRule.getSendEmail().equals("Y")) {
			if (ntfnRule.getEmailMessage() == null) {
				eveMap = event.formKeyValues();
				String fileString = commonUtils.getHtmlContent(templateLocation + ntfnRule.getEmailTemplateName());
				notifKeyInDb.setEmailMessage(fileString);
			} else {
				eveMap = event.formKeyValues();
				notifKeyInDb.setEmailMessage(CommonUtils.replacePlaceHolders(ntfnRule.getEmailMessage(), eveMap));
				notifKeyInDb.setEmailSubject(CommonUtils.replacePlaceHolders(ntfnRule.getEmailSubject(), eveMap));
			}
		}
		if (ntfnRule.getSendInAppNotfn().equals("Y") && ntfnRule.getInAppNotfnMessage() != null) {
			eveMap = event.formKeyValues();
			System.out.println(ntfnRule.getInAppNotfnMessage());
			notifKeyInDb.setInAppNotfnMessage(CommonUtils.replacePlaceHolders(ntfnRule.getInAppNotfnMessage(), eveMap));
		}
		notifKeyInDb = notificationKeyValueRepository.save(notifKeyInDb);

		log.debug("setNotificationPayload -- "+event.getName()+"("+event.getId()+")", notifKeyInDb);
		notificationKeys.add(notifKeyInDb);		
		return notificationKeys;
	}
	
//	----------------------------------------------Rest Fetch--------------------------------------------------------------------------------------------------
	
	private List<memployee> fetchEmployeeRest(String appToken) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", appToken);
		memployee memployee1 = new memployee();
		HttpEntity<memployee> memployeerequest = new HttpEntity<memployee>(memployee1, headers);
		ResponseEntity<memployee[]> memployees = commonUtils.getRestTemplate().exchange(highUrl,
				HttpMethod.GET, memployeerequest, memployee[].class);
		List<memployee> memployee = Arrays.asList(memployees.getBody());
		return memployee;
	}
	
	private Announcement fetchAnnouncementRest(String appToken, String announcementId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", appToken);
		Announcement announcement1 = new Announcement();
		HttpEntity<Announcement> request = new HttpEntity<Announcement>(announcement1, headers);
		ResponseEntity<Announcement> announcementEntity = commonUtils.getRestTemplate()
				.exchange(announcementUrl + announcementId, HttpMethod.GET, request, Announcement.class);
		Announcement announcement = announcementEntity.getBody();
		return announcement;
	}
	
	private List<UserGroupEmployeeMapping> fetchUserGrpEmpMapping(String appToken, String userGroupId) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", appToken);
		UserGroupEmployeeMapping userGroupEmployeeMapping1 = new UserGroupEmployeeMapping();
		HttpEntity<UserGroupEmployeeMapping> request = new HttpEntity<UserGroupEmployeeMapping>(
				userGroupEmployeeMapping1, headers);
		ResponseEntity<UserGroupEmployeeMapping[]> userGroupEmployeeMappingEntity = commonUtils.getRestTemplate()
				.exchange(usergropsemployeeUrl + userGroupId, HttpMethod.GET, request,
						UserGroupEmployeeMapping[].class);
		return Arrays.asList(userGroupEmployeeMappingEntity.getBody());
	}

//	-------------------------------------------Not to be used anymore--------------------------------------------------------------------------------------------------

	@KafkaListener(topics = "notification-topic", groupId = "notfn-consumer-grp1", containerFactory = "kafkaListenerStringContainerFactory")
	public void consumer(String message, String eventString)
			throws JsonParseException, JsonMappingException, IOException { 
		Event event = new ObjectMapper().readValue(eventString, Event.class);
//		String appToken = commonUtils.setAuthenticationContext(event.getContextAuthUserId(),"async"); 
		try {
			if (event.getName().equals(Event.EventTypes.EmployeeSalaryCreatedEvent)) {
				employeeSalaryCreatedEventProcessor.processPayout(event);
			}
			String eventName = event.getName().toString();
			List<EventNotificationRule> ntfnRules = ntfnRuleRepository.findByEventName(eventName);
			if (ntfnRules != null && !ntfnRules.isEmpty()) {
				for (EventNotificationRule ntfnRule : ntfnRules) {
					// if notfnRule has entityStatus filter, then check if event has that
					// entityStatus
//					if (!(ntfnRule.getEntityStatus() == null || ntfnRule.getEntityStatus().isEmpty())
//							&& ntfnRule.getEntityStatus().equalsIgnoreCase(event.getEntityStatus())) {
//						System.out.println("kh-1005- In Entity status sending event" + ntfnRule.getEntityStatus());
//						processNotificationRule(event, ntfnRule);
//					} else {
//						System.out.println("kh-1006- in else" + ":" + ntfnRule);
//						processNotificationRule(event, ntfnRule);
//					}
//					System.out.println("kh-1007- event in process Notification Receiver");

					processNotificationRule(event, ntfnRule);
				}
			}
		} catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+")",e);
		}
	}

}
