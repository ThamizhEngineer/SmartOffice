package com.ss.smartoffice.soservice.transaction.incident;

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
import com.ss.smartoffice.shared.interceptor.AuthUserRepository;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.IncidentApplicant;
import com.ss.smartoffice.shared.model.employee.memployee;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.Interview.Interview;
import com.ss.smartoffice.soservice.transaction.Interview.InterviewRound;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;
import com.ss.smartoffice.soservice.transaction.event.Event.EventTypes;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;
import com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim;
import com.ss.smartoffice.soservice.transaction.vacancyrequest.VacancyRequest;

@Service
public class IncidentEventGenerator {
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
	IncidentRepo incidentRepo;

	@Autowired
	EventPublisherService eventPublisherService;
	@Autowired
	CommonUtils commonUtils;
	
	@Autowired
	AuthUserRepository authUserRepository;
public List<BusinessKey> formBuisnessKeys(String interviewId,String employeeId,String applicantId,String dirUserGroupId,Integer eventId)throws SmartOfficeException{
	List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();	
	try {
		BusinessKey businessKey = new BusinessKey();
		businessKey.setInterviewId(interviewId);
		businessKey.setDirUserGroupId(dirUserGroupId);
		businessKey.setApplicantId(applicantId);
		businessKey.setEmployeeId(employeeId);
		businessKey.setEventId(eventId.toString());
		businessKeyRepository.save(businessKey);
		System.out.println("in form business keys"+businessKey.getEmployeeId());
		businessKeys.add(businessKey);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return businessKeys;
}
@Async("asyncThreadPoolTaskExecutor")
public void triggerInterviewRoundEvent(String action,String round,String id,Interview interview,AuthUser loggedInUser)throws SmartOfficeException{
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	Event event = new Event();
	if(interview.getInterviewRound()!=null&&!interview.getInterviewRound().isEmpty()) {
		for(InterviewRound interviewRound:interview.getInterviewRound()) {
		
			event.setName(Event.EventTypes.ScheduleInterviewerEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
//				event.setAppToken(commonUtils.getLoggedinAppToken());
			event.setContextAuthUserId(loggedInUser.getId());

			Event savedEvent = eventRepository.save(event);
			List<BusinessKey>businessKeys=formBuisnessKeys(id,null, null,null,savedEvent.getId());
			event.setBusinessKeys(businessKeys);
			
			List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
			
			EventKeyValue eventKeyValue = new EventKeyValue();
			eventKeyValue.setEventId(savedEvent.getId().toString());
			eventKeyValue.setKeyPair("RoundName");
			eventKeyValue.setValue(interviewRound.getRoundName());
			eventKeyValueRepository.save(eventKeyValue);
			eventKeyValues.add(eventKeyValue);
			TypedQuery<InterviewRound> interviewQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.Interview s where id="
							+ interviewRound.getId(),
							InterviewRound.class);
			List<InterviewRound> interviewRounds = interviewQuery.getResultList();
			EventKeyValue eventKeyValue2 = new EventKeyValue();
			eventKeyValue2.setEventId(savedEvent.getId().toString());	
			eventKeyValue2.setKeyPair("interviewName");
			eventKeyValue2.setValue(interviewRounds.get(0).getInterviewEmpName());
			eventKeyValueRepository.save(eventKeyValue2);
			
			eventKeyValues.add(eventKeyValue2);
			event.setEventKeyValues(eventKeyValues);
			
			eventPublisherService.pushEvent(event);
			
		}
	}	
}


@Async("asyncThreadPoolTaskExecutor")
	public void triggerNewIncidentEvent(String id, Incident incident, AuthUser loggedInUser)
			throws SmartOfficeException {
		try {
			commonUtils.setAuthenticationContext(loggedInUser,"async");
			Event event = new Event();
			event.setName(Event.EventTypes.IncidentDirApprovalEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
//			event.setAppToken(event.getAppToken());
			event.setContextAuthUserId(loggedInUser.getId());
			Event savedEvent = eventRepository.save(event);
			System.out.println(savedEvent);
			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
			BusinessKey businessKey = new BusinessKey();
			businessKey.setIncidentId(String.valueOf(incident.getId()));
			businessKey.setIncidentCode(String.valueOf(incident.getIncidentCode()));
			businessKey.setEventId(savedEvent.getId().toString());
			businessKey.setDirUserGroupId(incident.getApproveUsrGrpId());
			;
			businessKeyRepository.save(businessKey);
			businessKeys.add(businessKey);
			event.setBusinessKeys(businessKeys);

			List<EventKeyValue> eventKeyValues = new ArrayList<>();

			EventKeyValue eventKeyValue = new EventKeyValue();
			eventKeyValue.setEventId(savedEvent.getId().toString());
			eventKeyValue.setKeyPair("incidentType");
			eventKeyValue.setValue(incident.getIncidentType());
			eventKeyValueRepository.save(eventKeyValue);
			eventKeyValues.add(eventKeyValue);

			EventKeyValue eventKeyValue1 = new EventKeyValue();
			eventKeyValue1.setEventId(savedEvent.getId().toString());
			eventKeyValue1.setKeyPair("incidentName");
			eventKeyValue1.setValue(incident.getIncidentName());
			eventKeyValueRepository.save(eventKeyValue1);
			eventKeyValues.add(eventKeyValue1);
			TypedQuery<Incident> incidentQuery = entityManager
					.createQuery("SELECT s from com.ss.smartoffice.soservice.transaction.incident.Incident s where id="
							+ incident.getId(), Incident.class);
			List<Incident> incidentById = incidentQuery.getResultList();
			EventKeyValue eventKeyValue2 = new EventKeyValue();
			eventKeyValue2.setEventId(savedEvent.getId().toString());
			eventKeyValue2.setKeyPair("employeeName");
			eventKeyValue2.setValue(incidentById.get(0).getIncidentCreatedEmpName());
			eventKeyValueRepository.save(eventKeyValue2);
			eventKeyValues.add(eventKeyValue2);

			EventKeyValue eventKeyValue3 = new EventKeyValue();
			eventKeyValue3.setEventId(savedEvent.getId().toString());
			eventKeyValue3.setKeyPair("incidentCode");
			eventKeyValue3.setValue(incidentById.get(0).getIncidentCode());
			eventKeyValueRepository.save(eventKeyValue3);
			eventKeyValues.add(eventKeyValue3);
			event.setEventKeyValues(eventKeyValues);

			eventPublisherService.pushEvent(event);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			incident.setErrorCode("ERROR");
			incident.setErrorMessage(e.getLocalizedMessage());
			incidentRepo.save(incident);
		}

}



@Async("asyncThreadPoolTaskExecutor")
	public void triggerFinalRoundEventToHr(String action,String round,String id,Interview interview,AuthUser loggedInUser)throws SmartOfficeException{
		commonUtils.setAuthenticationContext(loggedInUser,"async");			
		Event event = new Event();
		event.setName(Event.EventTypes.ScheduleInterviewerHrEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
//				event.setAppToken(commonUtils.getLoggedinAppToken());
		event.setContextAuthUserId(loggedInUser.getId());

		Event savedEvent = eventRepository.save(event);
		Incident incidentById=incidentRepo.findById(Integer.parseInt(interview.getIncidentId())).get();
		List<BusinessKey>businessKeys=formBuisnessKeys(null,id.toString(), null,incidentById.getApproveUsrGrpId()
				,savedEvent.getId());
		event.setBusinessKeys(businessKeys);
		System.out.println(businessKeys +"businessKeys");
		List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
		EventKeyValue eventKeyValue = new EventKeyValue();
		eventKeyValue.setEventId(savedEvent.getId().toString());
		eventKeyValue.setKeyPair("finalDecisionEmpName");
		eventKeyValue.setValue(interview.getFinalDecisionEmpName());
		eventKeyValues.add(eventKeyValue);
		eventKeyValueRepository.save(eventKeyValue);
		EventKeyValue eventKeyValue1 = new EventKeyValue();
		eventKeyValue1.setEventId(savedEvent.getId().toString());
		eventKeyValue1.setKeyPair("finalDecision");
		eventKeyValue1.setValue(interview.getFinalDecision());
		eventKeyValues.add(eventKeyValue1);
		TypedQuery<IncidentApplicant> incidentApplicant = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.incident.IncidentApplicant s where id="
						+ interview.getApplicantId(),
						IncidentApplicant.class);
		List<IncidentApplicant> incidentApplicants = incidentApplicant.getResultList();
		
		EventKeyValue eventKeyValue2 = new EventKeyValue();
		eventKeyValue2.setEventId(savedEvent.getId().toString());
		eventKeyValue2.setKeyPair("incidentName");
		eventKeyValue2.setValue(incidentApplicants.get(0).getFirstName());
		eventKeyValues.add(eventKeyValue2);
		eventKeyValueRepository.save(eventKeyValue2);
		
		
	
		EventKeyValue eventKeyValue3 = new EventKeyValue();
		eventKeyValue3.setEventId(savedEvent.getId().toString());	
		eventKeyValue3.setKeyPair("finalDecisionEmpName");
		eventKeyValue3.setValue(interview.getFinalDecisionEmpName());
		eventKeyValueRepository.save(eventKeyValue3);
		eventKeyValues.add(eventKeyValue3);
		
		EventKeyValue eventKeyValue4 = new EventKeyValue();
		eventKeyValue4.setEventId(savedEvent.getId().toString());	
		eventKeyValue4.setKeyPair("dirUsrGrpId");
		eventKeyValue4.setValue(incidentById.getApproveUsrGrpName());
		eventKeyValueRepository.save(eventKeyValue4);
		eventKeyValues.add(eventKeyValue4);
		
		event.setEventKeyValues(eventKeyValues);
		eventPublisherService.pushEvent(event);
		
			
		}

@Async("asyncThreadPoolTaskExecutor")
public void triggerTestRound(Incident incident,IncidentApplicant applicantFromReq,AuthUser loggedInUser)throws SmartOfficeException{
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	Event event = new Event();
	event.setName(Event.EventTypes.TestCreationEvent);
	event.setCategory(Event.EventCategory.NotificationEvent);
//	event.setAppToken(commonUtils.getLoggedinAppToken());
	event.setContextAuthUserId(loggedInUser.getId());

	// fetch user-credentials based on applicationId
	AuthUser appltUser = authUserRepository.findByApplicantId(applicantFromReq.getApplicantId());

	Event savedEvent = eventRepository.save(event);
	List<BusinessKey>businessKeys=formBuisnessKeys(null,null, applicantFromReq.getApplicantId().toString(),null,savedEvent.getId());
	event.setBusinessKeys(businessKeys);
	System.out.println(businessKeys +"businessKeys");
	List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();	
	eventKeyValues.add(new EventKeyValue(savedEvent.getId().toString(),"applicantName",applicantFromReq.getFirstName()));
	eventKeyValues.add(new EventKeyValue(savedEvent.getId().toString(),"incidentName",incident.getIncidentName()));	
	eventKeyValues.add(new EventKeyValue(savedEvent.getId().toString(),"userName",appltUser.getUserName()));
	eventKeyValues.add(new EventKeyValue(savedEvent.getId().toString(),"password",appltUser.getPassword()));
	event.setEventKeyValues(eventKeyValues);
	eventKeyValues.forEach((keyValue)->{
		keyValue = eventKeyValueRepository.save(keyValue);
	});
	eventPublisherService.pushEvent(savedEvent);
}

//------------------------------------AssignTrainingEvent----------------------------------------------------------------------------


@Async("asyncThreadPoolTaskExecutor")
public void triggerAssignTrainingEvent(Incident incident,IncidentApplicant applicantFromReq,AuthUser loggedInUser)throws SmartOfficeException{
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	Event event = new Event();
	event.setName(Event.EventTypes.AssignTrainingEvent);
	event.setCategory(Event.EventCategory.NotificationEvent);
	event.setContextAuthUserId(loggedInUser.getId());

	Event savedEvent = eventRepository.save(event);
	List<BusinessKey>businessKeys=formBuisnessKeys(null,applicantFromReq.getEmployeeId(), null,null,savedEvent.getId());
	event.setBusinessKeys(businessKeys);
	System.out.println(businessKeys +"businessKeys");
	List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
	EventKeyValue eventKeyValue = new EventKeyValue();
	eventKeyValue.setEventId(savedEvent.getId().toString());
	eventKeyValue.setKeyPair("employeeName");
	eventKeyValue.setValue(applicantFromReq.getFirstName());
	eventKeyValueRepository.save(eventKeyValue);
	eventKeyValues.add(eventKeyValue);
	EventKeyValue eventKeyValue1 = new EventKeyValue();
	eventKeyValue1.setEventId(savedEvent.getId().toString());
	eventKeyValue1.setKeyPair("incidentName");
	eventKeyValue1.setValue(incident.getIncidentName());
	eventKeyValueRepository.save(eventKeyValue1);
	eventKeyValues.add(eventKeyValue1);
	
	EventKeyValue eventKeyValue2 = new EventKeyValue();
	eventKeyValue2.setEventId(savedEvent.getId().toString());
	eventKeyValue2.setKeyPair("fromDate");
	eventKeyValue2.setValue(incident.getTrStartDt().toString());
	eventKeyValueRepository.save(eventKeyValue2);
	eventKeyValues.add(eventKeyValue2);
	EventKeyValue eventKeyValue3 = new EventKeyValue();
	eventKeyValue3.setEventId(savedEvent.getId().toString());
	eventKeyValue3.setKeyPair("toDate");
	eventKeyValue3.setValue(incident.getTrEndDt().toString());
	eventKeyValueRepository.save(eventKeyValue3);
	eventKeyValues.add(eventKeyValue3);
	
	event.setEventKeyValues(eventKeyValues);
	eventPublisherService.pushEvent(savedEvent);
}

//------------------------------------ScheduleInterview----------------------------------------------------------------------------

@Async("asyncThreadPoolTaskExecutor")
public void triggerScheduleInterview(Incident incident,IncidentApplicant applicantFromReq,AuthUser loggedInUser)throws SmartOfficeException{
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	
	Event event = new Event();
	event.setName(Event.EventTypes.ScheduleInterviewEvent);
	event.setCategory(Event.EventCategory.NotificationEvent);
//	event.setAppToken(commonUtils.getLoggedinAppToken());
	event.setContextAuthUserId(loggedInUser.getId());
	Event savedEvent = eventRepository.save(event);
	List<BusinessKey>businessKeys=formBuisnessKeys(null,null, applicantFromReq.getApplicantId().toString(),null,savedEvent.getId());
	event.setBusinessKeys(businessKeys);
	List<EventKeyValue>eventKeyValues = new ArrayList<EventKeyValue>();
	EventKeyValue eventKeyValue = new EventKeyValue();
	eventKeyValue.setEventId(savedEvent.getId().toString());
	eventKeyValue.setKeyPair("applicantName");
	eventKeyValue.setValue(applicantFromReq.getFirstName());
	eventKeyValueRepository.save(eventKeyValue);
	eventKeyValues.add(eventKeyValue);
	EventKeyValue eventKeyValue1 = new EventKeyValue();
	eventKeyValue1.setEventId(savedEvent.getId().toString());
	eventKeyValue1.setKeyPair("incidentName");
	eventKeyValue1.setValue(incident.getIncidentName());
	eventKeyValueRepository.save(eventKeyValue1);
	eventKeyValues.add(eventKeyValue1);
	EventKeyValue eventKeyValue2 = new EventKeyValue();
	eventKeyValue2.setEventId(savedEvent.getId().toString());
	eventKeyValue2.setKeyPair("interviewEligibleDt");
	eventKeyValue2.setValue(applicantFromReq.getInterviewEligibleDt().toString());
	eventKeyValueRepository.save(eventKeyValue2);
	eventKeyValues.add(eventKeyValue2);
	event.setEventKeyValues(eventKeyValues);
	eventPublisherService.pushEvent(savedEvent);
}

//----------------------------------------------------------------------------------------------------------------

@Async("asyncThreadPoolTaskExecutor")
public void triggerJobRequestApprovalEvent(VacancyRequest jobRequestFromDb,AuthUser loggedInUser)throws SmartOfficeException{
	
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	Event event = new Event();
	Event savedEvent =new Event();
	
	List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
	BusinessKey busKey = new BusinessKey();
	EventKeyValue keyValue = new EventKeyValue();
	List<EventKeyValue> ekvs = new ArrayList<>();
	
	
	event.setName(Event.EventTypes.JobRequestDirApprovalEvent);
	event.setCategory(Event.EventCategory.NotificationEvent);
//	event.setAppToken(commonUtils.getLoggedinAppToken());
	event.setContextAuthUserId(loggedInUser.getId());
	savedEvent = eventRepository.save(event);
	busKeys = new ArrayList<BusinessKey>();
	busKey = new BusinessKey();
	busKey.setDirUserGroupId(jobRequestFromDb.getApprovalUserGroupId());
	businessKeyRepository.save(busKey);
	busKeys.add(busKey);
	savedEvent.setBusinessKeys(busKeys); 
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "jrCode", jobRequestFromDb.getVrCode())); 
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "dirEmpName", jobRequestFromDb.getApprovalUserGroupName()));
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "summary", jobRequestFromDb.getSummary()));
	ekvs.forEach((kv)->{
		kv = eventKeyValueRepository.save(kv);
	});
	savedEvent.setEventKeyValues(ekvs);
	eventPublisherService.pushEvent(savedEvent);
	
	
}
@Async("asyncThreadPoolTaskExecutor")
public void triggerJobRequestHrApprovalEvent(VacancyRequest jobRequestFromDb,AuthUser loggedInUser)throws SmartOfficeException{
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	Event event = new Event();
	Event savedEvent =new Event();
	
	List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
	BusinessKey busKey = new BusinessKey();
	EventKeyValue keyValue = new EventKeyValue();
	List<EventKeyValue> ekvs = new ArrayList<>();
	event.setName(Event.EventTypes.JobRequestHRApprovalEvent);
	event.setCategory(Event.EventCategory.NotificationEvent);
//	event.setAppToken(commonUtils.getLoggedinAppToken());
	event.setContextAuthUserId(loggedInUser.getId());
	savedEvent = eventRepository.save(event);
	busKeys = new ArrayList<BusinessKey>();
	busKey = new BusinessKey();
	busKey.setHr1UserGroupId(jobRequestFromDb.getHr1UsrGrpId());
	businessKeyRepository.save(busKey);
	busKeys.add(busKey);
	savedEvent.setBusinessKeys(busKeys);
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "jrCode", jobRequestFromDb.getVrCode()));
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "hr1UsrGrpName", jobRequestFromDb.getHr1UsrGroupName()));
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "summary", jobRequestFromDb.getSummary()));
	ekvs.add(new EventKeyValue(savedEvent.getId().toString(), "dirEmpName", jobRequestFromDb.getApprovalUserGroupName()));
	ekvs.forEach((kv)->{
		kv = eventKeyValueRepository.save(kv);
	});
	savedEvent.setEventKeyValues(ekvs);
	eventPublisherService.pushEvent(savedEvent);
	
}

@Async("asyncThreadPoolTaskExecutor")
public void triggerIncidentHrEvent(String id,Incident incident,AuthUser loggedInUser)throws SmartOfficeException{
	try {
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		Event event = new Event();
		Event savedEvent =new Event();
		
		List<BusinessKey> busKeys = new ArrayList<BusinessKey>();
		BusinessKey busKey = new BusinessKey();
		EventKeyValue keyValue = new EventKeyValue();
		List<EventKeyValue> ekvs = new ArrayList<>();
		event.setName(Event.EventTypes.IncidentHrApprovalEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
//		event.setAppToken(commonUtils.getLoggedinAppToken());
		event.setContextAuthUserId(loggedInUser.getId());

		savedEvent = eventRepository.save(event);
		busKeys = new ArrayList<BusinessKey>();
		busKey = new BusinessKey();
		busKey.setHr1UserGroupId(incident.getHr1UsrGrpId());
		businessKeyRepository.save(busKey);
		busKeys.add(busKey);
		savedEvent.setBusinessKeys(busKeys);
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "incidentType", incident.getIncidentType());
		ekvs.add(keyValue);
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "incidentName", incident.getIncidentName());
		ekvs.add(keyValue);
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1UsrGrpName", incident.getHr1UsrGrpName());
		ekvs.add(keyValue);
		keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "dirEmpName", incident.getApproveUsrGrpName());
		ekvs.add(keyValue);
		ekvs.forEach((kv)->{
			kv = eventKeyValueRepository.save(kv);
		});
		savedEvent.setEventKeyValues(ekvs);
		eventPublisherService.pushEvent(savedEvent);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		incident.setErrorCode("Error");
		incident.setErrorMessage(e.getLocalizedMessage());
		incidentRepo.save(incident);
	}
	
}
	

}
