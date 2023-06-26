package com.ss.smartoffice.soservice.master.partnerservice;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.partner.PartnerEmployee;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;

@Service
public class PartnerEventGenerator {

	@Autowired
	EventRepository eventRepository;

	@Autowired
	NotificationKeyRepository notificationKeyRepository;
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;


	@Autowired
	BusinessKeyRepository businessKeyRepository;

	@Autowired
	EventPublisherService eventPublisherService;
	@Autowired
	CommonUtils commonUtils;
	
	@Async("asyncThreadPoolTaskExecutor")
	public void triggerPartnerUserDetail(PartnerEmployee partnerEmployee,AuthUser authUser,AuthUser loggedInUser)throws SmartOfficeException{
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		event.setName(Event.EventTypes.NewPartnerEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
//		event.setAppToken(commonUtils.getLoggedinAppToken());
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey>businessKeys= new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setPartnerId(String.valueOf(partnerEmployee.getId()));
		businessKey.setEventId(savedEvent.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);		
		List<EventKeyValue> eventKeyValues = new ArrayList<>();
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("partnerName");
		eventKeyValue.setValue(partnerEmployee.getFirstName()+partnerEmployee.getLastName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue1 = new EventKeyValue();
		eventKeyValue1.setEventId(savedEvent.getId().toString());
		eventKeyValue1.setKeyPair("userName");
		eventKeyValue1.setValue(authUser.getUserName());
		eventKeyValueRepository.save(eventKeyValue1);
		eventKeyValues.add(eventKeyValue1);

		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("password");
		eventKeyValue2.setValue(authUser.getPassword());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		event.setEventKeyValues(eventKeyValues);
		eventPublisherService.pushEvent(savedEvent);
	}

	
}
