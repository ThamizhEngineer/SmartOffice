package com.ss.smartoffice.soservice.transaction.ShiftChange;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;

@Service
public class ShiftChangeRequestEventService {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	BusinessKeyRepository businessKeyRepository;

	@Autowired
	EventPublisherService eventPublisherService;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	ShiftChangeRepo shiftChangeRepo;
	
	@Async("asyncThreadPoolTaskExecutor")
	public void shiftCreateEvent(Integer id,AuthUser loggedInUser)throws SmartOfficeException{
		
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		
		ShiftChange shiftRequestFromDb = shiftChangeRepo.findById(id).get();
		
		System.out.println(shiftRequestFromDb);
		event.setName(Event.EventTypes.ShiftChangeRequqestEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setHr1UserGroupId(shiftRequestFromDb.getHr1GroupId());
		businessKey.setEmployeeId(shiftRequestFromDb.getN1Id());
		businessKey.setN1EmpId(shiftRequestFromDb.getN1Id());
		businessKey.setEventId(savedEvent.getId().toString());
		businessKey.setInternalId(shiftRequestFromDb.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("empName");
		eventKeyValue.setValue(shiftRequestFromDb.getEmpName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("fromDate");
		eventKeyValue2.setValue(shiftRequestFromDb.getFromDt().toString());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		
		EventKeyValue eventKeyValue3 = new EventKeyValue();
		eventKeyValue3.setEventId(savedEvent.getId().toString());
		eventKeyValue3.setKeyPair("shiftName");
		eventKeyValue3.setValue(shiftRequestFromDb.getNewShiftName());
		eventKeyValueRepository.save(eventKeyValue3);
		eventKeyValues.add(eventKeyValue3);
		
		event.setEventKeyValues(eventKeyValues);		
		eventPublisherService.pushEvent(event);
	}
	
	
	
	@Async("asyncThreadPoolTaskExecutor")
	public void shiftActionEvent(Integer id,AuthUser loggedInUser)throws SmartOfficeException{
		
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		
		ShiftChange shiftRequestFromDb = shiftChangeRepo.findById(id).get();
		
		System.out.println(shiftRequestFromDb);
		event.setName(Event.EventTypes.ShiftChangeActionEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setHr1UserGroupId(shiftRequestFromDb.getHr1GroupId());
		businessKey.setN1EmpId(shiftRequestFromDb.getN1Id());
		businessKey.setEventId(savedEvent.getId().toString());
		businessKey.setEmployeeId(shiftRequestFromDb.getModifiedBy());
		businessKey.setInternalId(shiftRequestFromDb.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("empName");
		eventKeyValue.setValue(shiftRequestFromDb.getEmpName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("action");
		eventKeyValue2.setValue(shiftRequestFromDb.getStatus());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		
		EventKeyValue eventKeyValue3 = new EventKeyValue();
		eventKeyValue3.setEventId(savedEvent.getId().toString());
		eventKeyValue3.setKeyPair("shiftName");
		eventKeyValue3.setValue(shiftRequestFromDb.getNewShiftName());
		eventKeyValueRepository.save(eventKeyValue3);
		eventKeyValues.add(eventKeyValue3);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String date = shiftRequestFromDb.getFromDt().format(formatter);
		
		EventKeyValue eventKeyValue4 = new EventKeyValue();
		eventKeyValue4.setEventId(savedEvent.getId().toString());
		eventKeyValue4.setKeyPair("fromDt");
		eventKeyValue4.setValue(date);
		eventKeyValueRepository.save(eventKeyValue4);
		eventKeyValues.add(eventKeyValue4);
		
		event.setEventKeyValues(eventKeyValues);		
		eventPublisherService.pushEvent(event);
	}
		
}
