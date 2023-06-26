package com.ss.smartoffice.soservice.master.employee;

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
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.applicant.Applicant;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;


@Service
public class EmployeeProfileEventGenerator {

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
	EmployeeRepository employeeRepository;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	AuthUserRepository authUserRepository;

	@Async("asyncThreadPoolTaskExecutor")
	public void triggerEmpProfileEvents(memployee savedEmp, String action, AuthUser loggedInUser)
			throws SmartOfficeException {

		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		Event savedEvent = new Event();
		memployee employeeById;
		List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
		BusinessKey busKey = new BusinessKey();
		EventKeyValue keyValue = new EventKeyValue();
		List<EventKeyValue> ekvs = new ArrayList<>();

		AuthUser authUser;
		int empId = savedEmp.getId();
		String eventId = savedEvent.getId() + "";
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // sleep for 2 seconds
		employeeById = employeeRepository.findById(empId).get();
		System.out.println("employeeById=" + employeeById);

		switch (action) {
		case ("create"):
			// triggering business event
			

			event = new Event();
			event.setName(Event.EventTypes.EmployeeCreatedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
//			event.setAppToken(event.getAppToken());
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);

			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setEventId(String.valueOf(savedEvent.getId()));
			busKey.setEmployeeId(employeeById.getId().toString());
			busKey.setEmployeeCode(employeeById.getEmpCode());
			busKey.setDirUserGroupId(employeeById.getDirUsrGrpId());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
//			ekvs = new ArrayList<>();
//			keyValue = new EventKeyValue(null, eventId, "employeeFullName", employeeById.getEmpName());
//			eventKeyValueRepository.save(keyValue);
//			ekvs.add(keyValue);
//			keyValue = new EventKeyValue(null, eventId, "onBoardUsrGrpId", employeeById.getDirUsrGrpName());
//			eventKeyValueRepository.save(keyValue);
//			ekvs.add(keyValue);
//			AuthUser authUserEmp = authUserRepository.findByEmployeeId(employeeById.getId().toString()).get(0);
//			keyValue = new EventKeyValue(null, eventId, "userName", authUserEmp.getUserName());
//			eventKeyValueRepository.save(keyValue);
//			ekvs.add(keyValue);
//
//			keyValue = new EventKeyValue(null, eventId, "password", authUserEmp.getPassword());
//			eventKeyValueRepository.save(keyValue);
//			ekvs.add(keyValue);
//			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);

			break;
		case ("user-created"): // this will be called from EmployeeCreatedEventProcessor - businessevent
								// processor
			event = new Event();
			event.setName(Event.EventTypes.NewUserCreatedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
//			event.setAppToken(event.getAppToken());
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);

			authUser = authUserRepository.findByEmployeeId(empId+"").get(0);
			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setEventId(String.valueOf(savedEvent.getId()));
			busKey.setEmployeeId(employeeById.getId().toString());
			busKey.setEmployeeCode(employeeById.getEmpCode());
			busKey.setDirUserGroupId(employeeById.getDirUsrGrpId());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			ekvs = new ArrayList<>();

			keyValue = new EventKeyValue(null, eventId, "employeeCode", employeeById.getEmpCode());
			eventKeyValueRepository.save(keyValue);
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, eventId, "firstName", authUser.getFirstName());
			eventKeyValueRepository.save(keyValue);
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, eventId, "lastName", authUser.getLastName());
			eventKeyValueRepository.save(keyValue);
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, eventId, "userName", authUser.getUserName());
			eventKeyValueRepository.save(keyValue);
			ekvs.add(keyValue);
			keyValue = new EventKeyValue(null, eventId, "password", authUser.getPassword());
			eventKeyValueRepository.save(keyValue);
			ekvs.add(keyValue);
			savedEvent.setEventKeyValues(ekvs);
			eventPublisherService.pushEvent(savedEvent);

			break;
		case ("onboard"):

			event = new Event();
		event.setName(Event.EventTypes.EmployeeCreatedEvent);
		event.setCategory(Event.EventCategory.BuisnessEvent);
//		event.setAppToken(event.getAppToken());
		event.setContextAuthUserId(loggedInUser.getId());
		savedEvent = eventRepository.save(event);

		busKeys = new ArrayList<BusinessKey>();
		busKey = new BusinessKey();
		busKey.setEventId(String.valueOf(savedEvent.getId()));
		busKey.setEmployeeId(employeeById.getId().toString());
		busKey.setEmployeeCode(employeeById.getEmpCode());
		busKey.setDirUserGroupId(employeeById.getDirUsrGrpId());
		businessKeyRepository.save(busKey);
		busKeys.add(busKey);
		savedEvent.setBusinessKeys(busKeys);
		eventPublisherService.pushEvent(savedEvent);
			
			Event event2 = new Event();
			event2.setName(Event.EventTypes.EmployeeOnboardedEvent);
			event2.setCategory(Event.EventCategory.NotificationEvent);
//			event2.setAppToken(event2.getAppToken());
			event2.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent2 = eventRepository.save(event2);
			int empId2 = savedEmp.getId();
			TypedQuery<memployee> empQuery2 = entityManager.createQuery(
					"select emp from com.ss.smartoffice.shared.model.employee.memployee emp where id=" + empId2,
					memployee.class);
			List<memployee> employeeById2 = empQuery2.getResultList();
			System.out.println("employeeById=" + empQuery2);
			for (memployee employeeBK : employeeById2) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent2.getId()));
				businessKey.setEmployeeId(employeeBK.getId().toString());
				businessKey.setEmployeeCode(employeeBK.getEmpCode());
				businessKey.setHr1UserGroupId(employeeBK.getHr1UsrGrpId());
				businessKey.setDirUserGroupId(employeeBK.getDirUsrGrpId());

				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent2.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(employeeBK.getEmpName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue secondeventKeyValue = new EventKeyValue();
				secondeventKeyValue.setEventId(String.valueOf(savedEvent2.getId()));
				secondeventKeyValue.setKeyPair("onBoardUsrGrpId");
				secondeventKeyValue.setValue(employeeBK.getDirUsrGrpName());
				eventKeyValueRepository.save(secondeventKeyValue);
				eventKeyValues.add(secondeventKeyValue);
				savedEvent2.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent2);
				break;
			}
			break;
		case ("complete-profile"):
			Event event3 = new Event();
			event3.setName(Event.EventTypes.EmployeeProfileCompletedEvent);
			event3.setCategory(Event.EventCategory.NotificationEvent);
//			event3.setAppToken(event3.getAppToken());
			event3.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent3 = eventRepository.save(event3);
			int empId3 = savedEmp.getId();
			TypedQuery<memployee> empQuery3 = entityManager.createQuery(
					"select emp from com.ss.smartoffice.shared.model.employee.memployee emp where id=" + empId3,
					memployee.class);
			List<memployee> employeeById3 = empQuery3.getResultList();
			System.out.println("employeeById=" + employeeById3);
			for (memployee employeeBK : employeeById3) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent3.getId()));
				businessKey.setEmployeeId(employeeBK.getId().toString());
				businessKey.setDirUserGroupId(employeeBK.getDirUsrGrpId());
				businessKey.setHr1UserGroupId(employeeBK.getHr1UsrGrpId());
				businessKey.setEmployeeCode(employeeBK.getEmpCode());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent3.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(employeeBK.getEmpName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue secondeventKeyValue = new EventKeyValue();
				secondeventKeyValue.setEventId(String.valueOf(savedEvent3.getId()));
				secondeventKeyValue.setKeyPair("onBoardEmpId");
				secondeventKeyValue.setValue(employeeBK.getOnboardEmpId());
				eventKeyValueRepository.save(secondeventKeyValue);
				eventKeyValues.add(secondeventKeyValue);
				savedEvent3.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent3);
				break;
			}
			break;
		case ("profile-update"):
			Event event4 = new Event();
			event4.setName(Event.EventTypes.EmployeeUpdateEvent);
			event4.setCategory(Event.EventCategory.NotificationEvent);
//			event4.setAppToken(event4.getAppToken());
			event4.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent4 = eventRepository.save(event4);
			int empId4 = savedEmp.getId();
			TypedQuery<memployee> empQuery4 = entityManager.createQuery(
					"select emp from com.ss.smartoffice.shared.model.employee.memployee emp where id=" + empId4,
					memployee.class);
			List<memployee> employeeById4 = empQuery4.getResultList();
			System.out.println("employeeById=" + employeeById4);
			for (memployee employeeBK : employeeById4) {

				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent4.getId()));
				businessKey.setEmployeeId(employeeBK.getId().toString());
				businessKey.setEmployeeCode(employeeBK.getEmpCode());
				businessKey.setHr1UserGroupId(employeeBK.getHr1UsrGrpId());
				businessKey.setDirUserGroupId(employeeBK.getDirUsrGrpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent4.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(employeeBK.getEmpName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);

				savedEvent4.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent4);
				break;
			}
//			}

			break;

		case ("profile-validate"):
			Event event5 = new Event();
			event5.setName(Event.EventTypes.EmployeeProfileValidatedEvent);
			event5.setCategory(Event.EventCategory.NotificationEvent);
//			event5.setAppToken(event5.getAppToken());
			event5.setContextAuthUserId(loggedInUser.getId());	
			Event savedEvent5 = eventRepository.save(event5);
			int empId5 = savedEmp.getId();
			TypedQuery<memployee> empQuery5 = entityManager.createQuery(
					"select emp from com.ss.smartoffice.shared.model.employee.memployee emp where id=" + empId5,
					memployee.class);
			List<memployee> employeeById5 = empQuery5.getResultList();
			System.out.println("employeeById=" + employeeById5);
			for (memployee employeeBK : employeeById5) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent5.getId()));
				businessKey.setEmployeeId(employeeBK.getId().toString());
				businessKey.setEmployeeCode(employeeBK.getEmpCode());
				businessKey.setHr1UserGroupId(employeeBK.getHr1UsrGrpId());
				businessKey.setDirUserGroupId(employeeBK.getDirUsrGrpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent5.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent5.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(employeeBK.getEmpName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);

				savedEvent5.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent5);
				break;
			}
			break;
		case ("skill-update"):
			Event event6 = new Event();
			event6.setName(Event.EventTypes.EmployeeUpdateEvent);
			event6.setCategory(Event.EventCategory.NotificationEvent);
//			event6.setAppToken(event6.getAppToken());
			event6.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent6 = eventRepository.save(event6);
			int empId6 = savedEmp.getId();
			TypedQuery<memployee> empQuery6 = entityManager.createQuery(
					"select emp from com.ss.smartoffice.shared.model.employee.memployee emp where id=" + empId6,
					memployee.class);
			List<memployee> employeeById6 = empQuery6.getResultList();
			System.out.println("employeeById=" + employeeById6);
			for (memployee employeeBK : employeeById6) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent6.getId()));
				businessKey.setEmployeeId(employeeBK.getId().toString());
				businessKey.setEmployeeCode(employeeBK.getEmpCode());
				businessKey.setHr1UserGroupId(employeeBK.getHr1UsrGrpId());
				businessKey.setN1EmpId(employeeBK.getN1EmpId());
				businessKey.setDirUserGroupId(employeeBK.getDirUsrGrpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent6.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent6.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(employeeBK.getEmpName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);

				savedEvent6.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent6);
				break;
			}
			break;

		case ("skill-validate"):
			Event event7 = new Event();
			event7.setName(Event.EventTypes.EmployeeSkillValidatedEvent);
			event7.setCategory(Event.EventCategory.NotificationEvent);
//			event7.setAppToken(event7.getAppToken());
			event7.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent7 = eventRepository.save(event7);
			int empId7 = savedEmp.getId();
			TypedQuery<memployee> empQuery7 = entityManager.createQuery(
					"select emp from com.ss.smartoffice.shared.model.employee.memployee emp where id=" + empId7,
					memployee.class);
			List<memployee> employeeById7 = empQuery7.getResultList();
			System.out.println("employeeById=" + employeeById7);
			for (memployee employeeBK : employeeById7) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent7.getId()));
				businessKey.setEmployeeId(employeeBK.getId().toString());
				businessKey.setEmployeeCode(employeeBK.getEmpCode());
				businessKey.setHr1UserGroupId(employeeBK.getHr1UsrGrpId());
				businessKey.setN1EmpId(employeeBK.getN1EmpId());
				businessKey.setDirUserGroupId(employeeBK.getDirUsrGrpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent7.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent7.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(employeeBK.getEmpName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);

				savedEvent7.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent7);
				break;
			}
			break;
		case ("update-profile-by-hr"):

			// triggering business event
			event = new Event();
			event.setName(Event.EventTypes.EmployeeUpdatedByHrEvent);
			event.setCategory(Event.EventCategory.BuisnessEvent);
//			event.setAppToken(event.getAppToken());
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);

			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setEventId(String.valueOf(savedEvent.getId()));
			busKey.setEmployeeId(employeeById.getId().toString());
			busKey.setEmployeeCode(employeeById.getEmpCode());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			List<EventKeyValue> eventKeyValues = new ArrayList<>();

			EventKeyValue eventKeyValue = new EventKeyValue();
			eventKeyValue.setEventId(String.valueOf(savedEvent.getId()));
			eventKeyValue.setKeyPair("office-change");
			eventKeyValue.setValue(employeeById.getOfficeChange());
			eventKeyValueRepository.save(eventKeyValue);
			eventKeyValues.add(eventKeyValue);

			savedEvent.setEventKeyValues(eventKeyValues);
			eventPublisherService.pushEvent(savedEvent);

			break;
		case ("office-change"):

			// triggering notification event
			event = new Event();
			event.setName(Event.EventTypes.OfficeChangeEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
//			event.setAppToken(event.getAppToken());
			event.setContextAuthUserId(loggedInUser.getId());
			savedEvent = eventRepository.save(event);

			busKeys = new ArrayList<BusinessKey>();
			busKey = new BusinessKey();
			busKey.setEventId(String.valueOf(savedEvent.getId()));
			busKey.setEmployeeId(employeeById.getId().toString());
			busKey.setEmployeeCode(employeeById.getEmpCode());
			businessKeyRepository.save(busKey);
			busKeys.add(busKey);
			savedEvent.setBusinessKeys(busKeys);
			List<EventKeyValue> eventKeyValues1 = new ArrayList<>();
			EventKeyValue eventKeyValue1 = new EventKeyValue();
			eventKeyValue1.setEventId(String.valueOf(savedEvent.getId()));
			eventKeyValue1.setKeyPair("employeeFullName");
			eventKeyValue1.setValue(employeeById.getEmpName());
			eventKeyValueRepository.save(eventKeyValue1);
			eventKeyValues1.add(eventKeyValue1);
			savedEvent.setEventKeyValues(eventKeyValues1);
			eventPublisherService.pushEvent(savedEvent);

			break;

		default:
			break;
		}
	}

	@Async("asyncThreadPoolTaskExecutor")
	public void triggerEmpCoversionEvent(memployee employee, AuthUser loggedInUser, Applicant applicant)
			throws SmartOfficeException {
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		event.setName(Event.EventTypes.EmployeeConversionEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
//		event.setAppToken(commonUtils.getLoggedinAppToken());
		event.setContextAuthUserId(loggedInUser.getId());
		Event savedEvent = eventRepository.save(event);
		List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
		BusinessKey busKey = new BusinessKey();
		busKey.setEventId(String.valueOf(savedEvent.getId()));
		busKey.setApplicantId(applicant.getId().toString());
		busKey.setEmployeeCode(employee.getEmpCode());
		businessKeyRepository.save(busKey);
		busKeys.add(busKey);
		savedEvent.setBusinessKeys(busKeys);
		List<EventKeyValue> ekvs = new ArrayList<>();
		EventKeyValue keyValue = new EventKeyValue(null, String.valueOf(savedEvent.getId()), "applicantName",
				applicant.getFirstName());
		eventKeyValueRepository.save(keyValue);
		ekvs.add(keyValue);
		savedEvent.setEventKeyValues(ekvs);
		eventPublisherService.pushEvent(savedEvent);

	}

}
