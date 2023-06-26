package com.ss.smartoffice.soservice.transaction.exitInterview;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;

@Service
public class ExitInterviewEventGenerator {

	@Autowired
	EventPublisherService eventPublisherService;
	
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	BusinessKeyRepository businessKeyRepository;
	
	@Autowired
	EventRepository eventRepository;

	@Autowired
	NotificationKeyRepository notificationKeyRepository;
	
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	
	@Async("asyncThreadPoolTaskExecutor")
	public void exitInterviewCreateEvent(ExitInterview exitInterview,AuthUser loggedInUser)throws SmartOfficeException{
		try {
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.ExitInterviewCreateEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setEmployeeId(exitInterview.getEmpId());
			busKey.setN1EmpId(exitInterview.getN1ManagerId());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empName",exitInterview.getEmpName());					
			ekvs.add(keyValue);			
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empCode",exitInterview.getEmpCode());					
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "n1ManagerName",exitInterview.getN1ManagerName());					
			ekvs.add(keyValue);		
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Async("asyncThreadPoolTaskExecutor")
	public void exitInterviewN1AppovedEvent(ExitInterview exitInterview,AuthUser loggedInUser)throws SmartOfficeException{
		try {
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.ExitInterviewN1ClearedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setAcc2UserGroupId(exitInterview.getAcc2GroupId());			
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);			
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empName",exitInterview.getEmpName());					
			ekvs.add(keyValue);			
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empCode",exitInterview.getEmpCode());					
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "acc2GroupName",exitInterview.getAcc2GrpName());					
			ekvs.add(keyValue);		
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Async("asyncThreadPoolTaskExecutor")
	public void exitInterviewAccClearedEvent(ExitInterview exitInterview,AuthUser loggedInUser)throws SmartOfficeException{
		try {
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.ExitInterviewAccClearedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setHr2UserGroupId(exitInterview.getHr2GroupId());			
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empName",exitInterview.getEmpName());					
			ekvs.add(keyValue);			
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empCode",exitInterview.getEmpCode());					
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr2GroupName",exitInterview.getHr2GrpName());					
			ekvs.add(keyValue);		
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Async("asyncThreadPoolTaskExecutor")
	public void exitInterviewCompleteEvent(ExitInterview exitInterview,AuthUser loggedInUser)throws SmartOfficeException{
		try {
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			Event savedEvent =new Event();
			
			List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
			BusinessKey busKey = new BusinessKey();
			EventKeyValue keyValue = new EventKeyValue();
			List<EventKeyValue> ekvs = new ArrayList<>();
			event.setName(Event.EventTypes.ExitInterviewHrClearedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setEmployeeId(exitInterview.getEmpId());			
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empName",exitInterview.getEmpName());					
			ekvs.add(keyValue);			
			keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empCode",exitInterview.getEmpCode());					
			ekvs.add(keyValue);			
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
