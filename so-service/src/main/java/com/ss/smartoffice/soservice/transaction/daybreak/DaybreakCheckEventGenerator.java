package com.ss.smartoffice.soservice.transaction.daybreak;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.AuthToken;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.OfficeCalender;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.leaveapplication.LeaveApplication;

@Service
public class DaybreakCheckEventGenerator {
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EventKeyValueRepository eventKeyValueRepository;
	
	@Autowired
	BusinessKeyRepository businessKeyRepository;

	@Autowired
	EventPublisherService eventPublisherService;
	
	@Autowired
	CommonUtils commonUtils;

	@Async("asyncThreadPoolTaskExecutor")
	public void checkDayTypeEvent(@RequestBody OfficeCalender officeCalender,Integer adminGrpId,AuthUser loggedInUser)throws SmartOfficeException{
			
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		
		Event event = new Event();
		event.setName(Event.EventTypes.DayTypeNotSetEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setHr1UserGroupId(adminGrpId.toString());
		businessKey.setEventId(savedEvent.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("officeName");
		eventKeyValue.setValue(officeCalender.getOfficeName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("calDate");
		eventKeyValue2.setValue(officeCalender.getCalDate().format(formatter));
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		
		event.setEventKeyValues(eventKeyValues);		
		eventPublisherService.pushEvent(event);
	}
	
	
	@Async("asyncThreadPoolTaskExecutor")
	public void checkEmployeeEvent(memployee employee,String hr1GrpId,String hr1GroupName,AuthUser loggedInUser)throws SmartOfficeException{		
			
		commonUtils.setAuthenticationContext(loggedInUser,"async");

		Event event = new Event();
		event.setName(Event.EventTypes.EmployeeAttendanceNotEligibleEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setHr1UserGroupId(hr1GrpId);
		businessKey.setEmployeeId(employee.getId().toString());
		businessKey.setEmployeeCode(employee.getEmpCode());
		businessKey.setEventId(savedEvent.getId().toString());
		businessKey.setInternalId(employee.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("empName");
		eventKeyValue.setValue(employee.getEmpName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("intenalId");
		eventKeyValue2.setValue(employee.getInternalId());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		
		EventKeyValue eventKeyValue3 = new EventKeyValue();
		eventKeyValue3.setEventId(savedEvent.getId().toString());
		eventKeyValue3.setKeyPair("HrGrpName");
		eventKeyValue3.setValue(hr1GroupName);
		eventKeyValueRepository.save(eventKeyValue3);
		eventKeyValues.add(eventKeyValue3);
		
		event.setEventKeyValues(eventKeyValues);		
		eventPublisherService.pushEvent(event);
	}
	
	@Async("asyncThreadPoolTaskExecutor")
	public void employeeShiftNotSetEvent(memployee employee,String hr1GrpId,String hr1GroupName,AuthUser loggedInUser)throws SmartOfficeException{		
			
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		event.setName(Event.EventTypes.EmployeeShiftNotSetEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setHr1UserGroupId(hr1GrpId);
		businessKey.setEmployeeId(employee.getId().toString());
		businessKey.setEmployeeCode(employee.getEmpCode());
		businessKey.setEventId(savedEvent.getId().toString());
		businessKey.setInternalId(employee.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("empName");
		eventKeyValue.setValue(employee.getEmpName());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("intenalId");
		eventKeyValue2.setValue(employee.getInternalId());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		
		EventKeyValue eventKeyValue3 = new EventKeyValue();
		eventKeyValue3.setEventId(savedEvent.getId().toString());
		eventKeyValue3.setKeyPair("HrGrpName");
		eventKeyValue3.setValue(hr1GroupName);
		eventKeyValueRepository.save(eventKeyValue3);
		eventKeyValues.add(eventKeyValue3);
		
		event.setEventKeyValues(eventKeyValues);		
		eventPublisherService.pushEvent(event);
	}
	
	
	@Async("asyncThreadPoolTaskExecutor")
	public void checkUnApproveLeaveEvent(LeaveApplication leaveApplication,String managerId,AuthUser loggedInUser)throws SmartOfficeException{
		
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		
		Event event = new Event();
		event.setName(Event.EventTypes.LeaveNotApprovedEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey = new BusinessKey();
		businessKey.setEmployeeId(leaveApplication.getEmployeeId());
		businessKey.setN1EmpId(managerId);
		businessKey.setEventId(savedEvent.getId().toString());
		businessKey.setInternalId(leaveApplication.getId().toString());
		businessKeyRepository.save(businessKey);
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("empName");
		eventKeyValue.setValue(leaveApplication.getEmpFname()+""+leaveApplication.getEmpLname());
		eventKeyValueRepository.save(eventKeyValue);
		eventKeyValues.add(eventKeyValue);
		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("leaveStatus");
		eventKeyValue2.setValue(leaveApplication.getLeaveStatus());
		eventKeyValueRepository.save(eventKeyValue2);
		eventKeyValues.add(eventKeyValue2);
		
		EventKeyValue eventKeyValue3 = new EventKeyValue();
		eventKeyValue3.setEventId(savedEvent.getId().toString());
		eventKeyValue3.setKeyPair("ManagerName");
		if(leaveApplication.getLeaveStatus().equals("N1-APPROVAL-PENDING")) {
			eventKeyValue3.setValue(leaveApplication.getN1EmpFname());
		}else {
			eventKeyValue3.setValue(leaveApplication.getN2EmpFname());	
		}		
		eventKeyValueRepository.save(eventKeyValue3);
		eventKeyValues.add(eventKeyValue3);
		
		event.setEventKeyValues(eventKeyValues);		
		eventPublisherService.pushEvent(event);
	}
	
}
