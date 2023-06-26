package com.ss.smartoffice.soservice.transaction.leaveapplication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
public class LeaveApplicationEventGenerator {
	@Autowired
	EventRepository eventRepository;

	@Autowired
	NotificationKeyRepository notificationKeyRepository;
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
	@Async("asyncThreadPoolTaskExecutor")
	public LeaveApplication triggerLeaveEvents(LeaveApplication leaveApplication, String action, AuthUser loggedInUser)
			throws SmartOfficeException {
		commonUtils.setAuthenticationContext(loggedInUser,"async");

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // sleep for 2 seconds
		
		switch (action) {

		case ("apply"):
			Event event = new Event();
			event.setName(Event.EventTypes.LeaveAppliedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setEntityStatus(leaveApplication.getLeaveStatus());
//			event.setAppToken(event.getAppToken());
			event.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent = eventRepository.save(event);
			int leaveId = leaveApplication.getId();
			TypedQuery<LeaveApplication> leaveQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="
							+ leaveId,
					LeaveApplication.class);
			List<LeaveApplication> leaveById = leaveQuery.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeId(leaveApplicationBK.getEmployeeId());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN1EmpId(leaveApplicationBK.getN1EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				event.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue.setKeyPair("n1EmpCode");
				eventKeyValue.setValue(leaveApplicationBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue1.setKeyPair("n1EmpFullName");
				eventKeyValue1.setValue(leaveApplicationBK.getN1EmpFname()+leaveApplicationBK.getN1EmpLname());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue2.setKeyPair("toDt");
				eventKeyValue2.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue3.setKeyPair("employeeFullName");
				eventKeyValue3.setValue(leaveApplicationBK.getEmpFname() + "" + leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				event.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(event);
			}
			break;
		case ("n1-approve"):
			System.out.println("hi in event");
			Event event1 = new Event();
			event1.setName(Event.EventTypes.LeaveN1ApprovedEvent);
			event1.setCategory(Event.EventCategory.NotificationEvent);
			event1.setEntityStatus(leaveApplication.getLeaveStatus());
//			event1.setAppToken(event1.getAppToken());
			event1.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent1 = eventRepository.save(event1);
			int leaveId1 = leaveApplication.getId();
			TypedQuery<LeaveApplication> leaveQuery1 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="+ leaveId1,
					LeaveApplication.class);
			List<LeaveApplication> leaveById1 = leaveQuery1.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById1) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeId(leaveApplicationBK.getEmployeeId());
				businessKey.setHr1UserGroupId(leaveApplicationBK.getHr1UserGroupId());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN1EmpId(leaveApplicationBK.getN1EmpId());
				businessKey.setN2EmpId(leaveApplicationBK.getN2EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent1.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue.setKeyPair("n1EmpCode");
				eventKeyValue.setValue(leaveApplicationBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue1.setKeyPair("n1EmpFullName");
				eventKeyValue1.setValue(leaveApplicationBK.getN1EmpFname()+leaveApplicationBK.getN1EmpLname());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue2.setKeyPair("toDt");
				eventKeyValue2.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue3.setKeyPair("fromDt");
				eventKeyValue3.setValue(leaveApplicationBK.getStartDt().toString());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue5.setKeyPair("employeeFullName");
				eventKeyValue5.setValue(leaveApplicationBK.getEmpFname()+leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue5);
				
				
				eventKeyValues.add(eventKeyValue5);
				
				EventKeyValue eventKeyValue7 = new EventKeyValue();
				eventKeyValue7.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue7.setKeyPair("n2EmpCode");
				eventKeyValue7.setValue(leaveApplicationBK.getN2EmpCode());
				eventKeyValueRepository.save(eventKeyValue7);
				eventKeyValues.add(eventKeyValue7);
				
				
				EventKeyValue eventKeyValue6 = new EventKeyValue();
				eventKeyValue6.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue6.setKeyPair("n2EmpFullName");
				eventKeyValue6.setValue(leaveApplicationBK.getN2EmpFname()+leaveApplicationBK.getN2EmpLname());
				eventKeyValueRepository.save(eventKeyValue6);
				eventKeyValues.add(eventKeyValue6);
				
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				
			
				savedEvent1.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent1);
			}
			break;

		case ("n1-reject"):
			Event event2 = new Event();
			event2.setName(Event.EventTypes.LeaveN1RejectedEvent);
			event2.setCategory(Event.EventCategory.NotificationEvent);
			event2.setEntityStatus(leaveApplication.getLeaveStatus());
//			event2.setAppToken(event2.getAppToken());
			event2.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent2 = eventRepository.save(event2);
			int leaveId2 = leaveApplication.getId();
			TypedQuery<LeaveApplication> leaveQuery2 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="+ leaveId2,
					LeaveApplication.class);
			List<LeaveApplication> leaveById2 = leaveQuery2.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById2) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeId(leaveApplicationBK.getEmployeeId());
				businessKey.setHr1UserGroupId(leaveApplicationBK.getHr1UserGroupId());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN1EmpId(leaveApplicationBK.getN1EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent2.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue.setKeyPair("fromDt");
				eventKeyValue.setValue(leaveApplicationBK.getStartDt().toString());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue1.setKeyPair("toDt");
				eventKeyValue1.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue2.setKeyPair("n1EmpCode");
				eventKeyValue2.setValue(leaveApplicationBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue3.setKeyPair("n1EmpFullName");
				eventKeyValue3.setValue(leaveApplicationBK.getN1EmpFname()+leaveApplicationBK.getN1EmpLname());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue5.setKeyPair("employeeFullName");
				eventKeyValue5.setValue(leaveApplicationBK.getEmpFname()+leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				savedEvent2.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent2);
			}
			break;

		case ("n2-approve"):
			Event event3 = new Event();
			event3.setName(Event.EventTypes.LeaveN2ApprovedEvent);
			event3.setCategory(Event.EventCategory.NotificationEvent);
			event3.setEntityStatus(leaveApplication.getLeaveStatus());
//			event3.setAppToken(event3.getAppToken());
			event3.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent3 = eventRepository.save(event3);
			int leaveId3 = leaveApplication.getId();
			TypedQuery<LeaveApplication> leaveQuery3 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="+ leaveId3,
					LeaveApplication.class);
			List<LeaveApplication> leaveById3 = leaveQuery3.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById3) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeId(leaveApplicationBK.getEmployeeId());
				businessKey.setHr1UserGroupId(leaveApplicationBK.getHr1UserGroupId());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN1EmpId(leaveApplicationBK.getN1EmpId());
				businessKey.setN2EmpId(leaveApplicationBK.getN2EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent3.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue.setKeyPair("fromDt");
				eventKeyValue.setValue(leaveApplicationBK.getStartDt().toString());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue1.setKeyPair("toDt");
				eventKeyValue1.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue2.setKeyPair("n2EmpCode");
				eventKeyValue2.setValue(leaveApplicationBK.getN2EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue3.setKeyPair("n2EmpFullName");
				eventKeyValue3.setValue(leaveApplicationBK.getN2EmpFname()+leaveApplicationBK.getN2EmpLname());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue5.setKeyPair("employeeFullName");
				eventKeyValue5.setValue(leaveApplicationBK.getEmpFname()+leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				savedEvent3.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent3);
			}
			break;
		case ("n2-reject"):
			Event event4 = new Event();
			event4.setName(Event.EventTypes.LeaveN2RejectedEvent);
			event4.setEntityStatus(leaveApplication.getLeaveStatus());
			event4.setCategory(Event.EventCategory.NotificationEvent);
//			event4.setAppToken(event4.getAppToken());
			event4.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent4 = eventRepository.save(event4);
			int leaveId4 = leaveApplication.getId();
			TypedQuery<LeaveApplication> leaveQuery4 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="+ leaveId4,
					LeaveApplication.class);
			List<LeaveApplication> leaveById4 = leaveQuery4.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById4) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeId(leaveApplicationBK.getEmployeeId());
				businessKey.setHr1UserGroupId(leaveApplicationBK.getHr1UserGroupId());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN2EmpId(leaveApplicationBK.getN2EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent4.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue.setKeyPair("fromDt");
				eventKeyValue.setValue(leaveApplicationBK.getStartDt().toString());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue1.setKeyPair("toDt");
				eventKeyValue1.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue2.setKeyPair("n2EmpCode");
				eventKeyValue2.setValue(leaveApplicationBK.getN2EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue3.setKeyPair("n2EmpFullName");
				eventKeyValue3.setValue(leaveApplicationBK.getN2EmpFname()+leaveApplicationBK.getN2EmpLname());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue5.setKeyPair("employeeFullName");
				eventKeyValue5.setValue(leaveApplicationBK.getEmpFname()+leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				savedEvent4.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent4);

			}
			break;
		case ("hr1-settle"):
			Event event8 = new Event();
			event8.setName(Event.EventTypes.LeaveSettledEvent);
			event8.setEntityStatus(leaveApplication.getLeaveStatus());
			event8.setCategory(Event.EventCategory.NotificationEvent);
//			event8.setAppToken(event8.getAppToken());
			event8.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent8 = eventRepository.save(event8);
			int leaveId8 = leaveApplication.getId();
			
			TypedQuery<LeaveApplication> leaveQuery8 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="+ leaveId8,
					LeaveApplication.class);
			List<LeaveApplication> leaveById8 = leaveQuery8.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById8) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeId(leaveApplicationBK.getEmployeeId());
				businessKey.setHr1UserGroupId(leaveApplicationBK.getHr1UserGroupId());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN2EmpId(leaveApplicationBK.getN2EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent8.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				
				
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent8.getId()));
				eventKeyValue2.setKeyPair("empCode");
				eventKeyValue2.setValue(leaveApplicationBK.getEmployeeCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent8.getId()));
				eventKeyValue5.setKeyPair("employeeFullName");
				eventKeyValue5.setValue(leaveApplicationBK.getEmpFname()+leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent8.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				savedEvent8.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent8);

			}
			break;
		case ("cancel"):
			Event event5 = new Event();
			event5.setName(Event.EventTypes.LeaveCancelledEvent);
			event5.setEntityStatus(leaveApplication.getLeaveStatus());
			event5.setCategory(Event.EventCategory.NotificationEvent);
//			event5.setAppToken(event5.getAppToken());
			event5.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent5 = eventRepository.save(event5);
			int leaveId5 = leaveApplication.getId();
			TypedQuery<LeaveApplication> leaveQuery5 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="
							+ leaveId5,
					LeaveApplication.class);
			List<LeaveApplication> leaveById5 = leaveQuery5.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById5) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN1EmpId(leaveApplicationBK.getN1EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent5.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue.setKeyPair("fromDt");
				eventKeyValue.setValue(leaveApplicationBK.getStartDt().toString());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue1.setKeyPair("toDt");
				eventKeyValue1.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue2.setKeyPair("toDt");
				eventKeyValue2.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue3.setKeyPair("n1EmpCode");
				eventKeyValue3.setValue(leaveApplicationBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue4 = new EventKeyValue();
				eventKeyValue4.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue4.setKeyPair("n1EmpFullName");
				eventKeyValue4.setValue(leaveApplicationBK.getN1EmpFname()+leaveApplicationBK.getN1EmpLname());
				eventKeyValueRepository.save(eventKeyValue4);
				eventKeyValues.add(eventKeyValue4);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue5.setKeyPair("employeeFullName");
				eventKeyValue5.setValue(leaveApplicationBK.getEmpFname()+leaveApplicationBK.getEmpLname());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				savedEvent5.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent5);

			}
			break;
		case ("update"):
			Event event6 = new Event();
			event6.setName(Event.EventTypes.LeaveUpdatedEvent);
			event6.setCategory(Event.EventCategory.NotificationEvent);
			event6.setEntityStatus(leaveApplication.getLeaveStatus());
//			event6.setAppToken(event6.getAppToken());
			event6.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent6 = eventRepository.save(event6);
			int leaveId6 = leaveApplication.getId();
			System.out.println(leaveId6);
			TypedQuery<LeaveApplication> leaveQuery6 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication s where id="
							+ leaveId6,
					LeaveApplication.class);
			List<LeaveApplication> leaveById6 = leaveQuery6.getResultList();
			for (LeaveApplication leaveApplicationBK : leaveById6) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setLeaveRequestCode(leaveApplicationBK.getLeaveCode());
				businessKey.setEmployeeCode(leaveApplicationBK.getEmployeeCode());
				businessKey.setN1EmpId(leaveApplicationBK.getN1EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent6.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent6.getId()));
				eventKeyValue.setKeyPair("fromDt");
				eventKeyValue.setValue(leaveApplicationBK.getStartDt().toString());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent6.getId()));
				eventKeyValue1.setKeyPair("toDt");
				eventKeyValue1.setValue(leaveApplicationBK.getEndDt().toString());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent6.getId()));
				eventKeyValue2.setKeyPair("n1EmpCode");
				eventKeyValue2.setValue(leaveApplicationBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent6.getId()));
				eventKeyValue3.setKeyPair("n1EmpFullName");
				eventKeyValue3.setValue(leaveApplicationBK.getN1EmpFname()+leaveApplicationBK.getN1EmpLname());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue9 = new EventKeyValue();
				eventKeyValue9.setEventId(String.valueOf(savedEvent6.getId()));
				eventKeyValue9.setKeyPair("hr1UserGrpId");
				eventKeyValue9.setValue(leaveApplicationBK.getHr1UserGroupName());
				eventKeyValueRepository.save(eventKeyValue9);
				eventKeyValues.add(eventKeyValue9);
				savedEvent6.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent6);

			}
			break;

		}
		return leaveApplication;
			}

	
	
}
