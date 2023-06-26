package com.ss.smartoffice.soservice.transaction.appraisalservice;

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
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.BusinessKey;
import com.ss.smartoffice.soservice.transaction.event.BusinessKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValue;
import com.ss.smartoffice.soservice.transaction.event.EventKeyValueRepository;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.NotificationKeyRepository;
import com.ss.smartoffice.soservice.transaction.event.Event.EventCategory;
import com.ss.smartoffice.soservice.transaction.event.Event.EventTypes;
import com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest;
@Service
public class AppraisalEventGenerator {
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
   public void triggerAppraisalEvents(AppraisalHdr savedAppraisalHsr,String action,AuthUser loggedInUser)throws SmartOfficeException{
	   commonUtils.setAuthenticationContext(loggedInUser,"async");
	   
	   switch (action) {
	case "initiate":
		Event event = new Event();
		event.setName(Event.EventTypes.InitiateAppraisal);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setAppToken(event.getAppToken());
		Event savedEvent = eventRepository.save(event);
		int appraisalHdrId=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQuery = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrId,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrId = appHdrQuery.getResultList();
		System.out.println("appHdrId=" + appHdrId);
		for (AppraisalHdr appraisalHdrBK : appHdrId) {
			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
			BusinessKey businessKey= new BusinessKey();
			businessKey=formBusinessKey(appraisalHdrBK);
			businessKeys.add(businessKey);
			event.setBusinessKeys(businessKeys);
			List<EventKeyValue>ekvs= new ArrayList<>();
			EventKeyValue keyValue= new EventKeyValue();
			keyValue=formKeyValuePair(appraisalHdrBK, savedEvent);
			ekvs.add(keyValue);
			savedEvent.setEventKeyValues(ekvs);
			eventRepository.save(savedEvent);
			eventPublisherService.pushEvent(savedEvent);
		}
		break;
	case"start":
		Event event1 = new Event();
		event1.setName(Event.EventTypes.StartAppraisal);
		event1.setCategory(Event.EventCategory.NotificationEvent);
		event1.setAppToken(event1.getAppToken());
		Event savedEvent1 = eventRepository.save(event1);
		int appraisalHdrId1=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQuery1 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrId1,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrId1 = appHdrQuery1.getResultList();
		System.out.println("appHdrId=" + appHdrId1);
		for (AppraisalHdr appraisalHdrBK : appHdrId1) {
			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
			BusinessKey businessKey= new BusinessKey();
			businessKey=formBusinessKey(appraisalHdrBK);
			businessKeys.add(businessKey);
			event1.setBusinessKeys(businessKeys);
			List<EventKeyValue>ekvs= new ArrayList<>();
			EventKeyValue keyValue= new EventKeyValue();
			keyValue=formKeyValuePair(appraisalHdrBK, savedEvent1);
			ekvs.add(keyValue);
			savedEvent1.setEventKeyValues(ekvs);
			eventRepository.save(savedEvent1);
			eventPublisherService.pushEvent(savedEvent1);
		}
		
		break;
	case"update":
		Event event11 = new Event();
		event11.setName(Event.EventTypes.UpdateAppraisal);
		event11.setCategory(Event.EventCategory.NotificationEvent);
		event11.setAppToken(event11.getAppToken());
		Event savedEvent11 = eventRepository.save(event11);
		int appraisalHdrId11=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQuery11 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrId11,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrId11 = appHdrQuery11.getResultList();
		System.out.println("appHdrId=" + appHdrId11);
		for (AppraisalHdr appraisalHdrBK : appHdrId11) {
			List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
			BusinessKey businessKey= new BusinessKey();
			businessKey=formBusinessKey(appraisalHdrBK);
			businessKeys.add(businessKey);
			event11.setBusinessKeys(businessKeys);
			List<EventKeyValue>ekvs= new ArrayList<>();
			EventKeyValue keyValue= new EventKeyValue();
			keyValue=formKeyValuePair(appraisalHdrBK, savedEvent11);
			ekvs.add(keyValue);
			savedEvent11.setEventKeyValues(ekvs);
			eventRepository.save(savedEvent11);
			eventPublisherService.pushEvent(savedEvent11);
		}
		
		break;
	case"submit":
		Event submitEvent=new Event();
		submitEvent.setName(Event.EventTypes.SubmitAppraisal);
		submitEvent.setCategory(Event.EventCategory.NotificationEvent);
		submitEvent.setAppToken(submitEvent.getAppToken());
		Event savedSubmitEvent= eventRepository.save(submitEvent);
		int appraisalId=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQuer = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId,
				AppraisalHdr.class);
		List<AppraisalHdr> savedAppHDrId = appHdrQuer.getResultList();
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey= new BusinessKey();
		businessKey=formBusinessKey(savedAppHDrId.get(0));
		businessKeys.add(businessKey);
		submitEvent.setBusinessKeys(businessKeys);
		List<EventKeyValue>ekvs= new ArrayList<>();
		EventKeyValue keyValue= new EventKeyValue();
		keyValue=formKeyValuePair(savedAppHDrId.get(0), submitEvent);
		ekvs.add(keyValue);
		submitEvent.setEventKeyValues(ekvs);
		eventRepository.save(submitEvent);
		eventPublisherService.pushEvent(submitEvent);	
		break;
		
	case"n1-review":
		Event n1ReviewEvent=new Event();
		n1ReviewEvent.setName(Event.EventTypes.N1reviewAppraisal);
		n1ReviewEvent.setCategory(Event.EventCategory.NotificationEvent);
		n1ReviewEvent.setAppToken(n1ReviewEvent.getAppToken());
		Event savedN1ReviewEvent=eventRepository.save(n1ReviewEvent);
		int appraisalId1=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appraisalHdrQuery = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId1,
				AppraisalHdr.class);
		List<AppraisalHdr> savedAppraisalHdr = appraisalHdrQuery.getResultList();
		List<BusinessKey> businessKeyList = new ArrayList<BusinessKey>();
		BusinessKey businessKey1= new BusinessKey();
		businessKey1=formBusinessKey(savedAppraisalHdr.get(0));
		businessKeyList.add(businessKey1);
		n1ReviewEvent.setBusinessKeys(businessKeyList);
		List<EventKeyValue>ekvsList= new ArrayList<>();
		EventKeyValue keyValue1= new EventKeyValue();
		keyValue1=formKeyValuePair(savedAppraisalHdr.get(0), n1ReviewEvent);
		ekvsList.add(keyValue1);
		n1ReviewEvent.setEventKeyValues(ekvsList);
		eventRepository.save(n1ReviewEvent);
		eventPublisherService.pushEvent(n1ReviewEvent);	
		break;
	
	case"n1-reject":
		Event n1RejectEvent = new Event();
		n1RejectEvent.setName(Event.EventTypes.N1rejectAppraisal);
		n1RejectEvent.setCategory(Event.EventCategory.NotificationEvent);
		n1RejectEvent.setAppToken(n1RejectEvent.getAppToken());
		Event savedN1RejectEvent=eventRepository.save(n1RejectEvent);
		int appraisalId2=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appraisalHdrQuery1 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId2,
				AppraisalHdr.class);
		List<AppraisalHdr> savedAppraisalHdr1 = appraisalHdrQuery1.getResultList();
		List<BusinessKey> businessKeyList2 = new ArrayList<BusinessKey>();
		BusinessKey businessKey2= new BusinessKey();
		businessKey2=formBusinessKey(savedAppraisalHdr1.get(0));
		businessKeyList2.add(businessKey2);
		n1RejectEvent.setBusinessKeys(businessKeyList2);
		List<EventKeyValue>ekvsList1= new ArrayList<>();
		EventKeyValue keyValue2= new EventKeyValue();
		keyValue2=formKeyValuePair(savedAppraisalHdr1.get(0), n1RejectEvent);
		ekvsList1.add(keyValue2);
		n1RejectEvent.setEventKeyValues(ekvsList1);
		eventRepository.save(n1RejectEvent);
		eventPublisherService.pushEvent(n1RejectEvent);	
		break;
	case"emp-accept":
		Event empAccept=new Event();
		empAccept.setName(Event.EventTypes.EmpAcceptAppraisalEvent);
		empAccept.setCategory(Event.EventCategory.NotificationEvent);
		empAccept.setAppToken(empAccept.getAppToken());
		Event savedEmpAcceptEvent=eventRepository.save(empAccept);
		int appraisalId3=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appraisalHdrQuery2 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId3,
						AppraisalHdr.class);
		List<AppraisalHdr> savedAppraisalHdr2 = appraisalHdrQuery2.getResultList();
		List<BusinessKey> businessKeyList3 = new ArrayList<BusinessKey>();
		BusinessKey businessKey3= new BusinessKey();
		businessKey3=formBusinessKey(savedAppraisalHdr2.get(0));
		businessKeyList3.add(businessKey3);
		empAccept.setBusinessKeys(businessKeyList3);
		List<EventKeyValue>ekvsList2= new ArrayList<>();
		EventKeyValue keyValue3= new EventKeyValue();
		keyValue2=formKeyValuePair(savedAppraisalHdr2.get(0), empAccept);
		ekvsList2.add(keyValue2);
		empAccept.setEventKeyValues(ekvsList2);
		eventRepository.save(empAccept);
		eventPublisherService.pushEvent(empAccept);	
		break;
	
	case"emp-reject":
		Event empReject=new Event();
		empReject.setName(Event.EventTypes.EmpRejectAppraisalEvent);
		empReject.setCategory(Event.EventCategory.NotificationEvent);
		empReject.setAppToken(empReject.getAppToken());
		Event savedEmpRejectEvent=eventRepository.save(empReject);
		int appraisalId4=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appraisalHdrQuery3 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId4,
						AppraisalHdr.class);
		List<AppraisalHdr> savedAppraisalHdr3 = appraisalHdrQuery3.getResultList();
		List<BusinessKey> businessKeyList4 = new ArrayList<BusinessKey>();
		BusinessKey businessKey4= new BusinessKey();
		businessKey4=formBusinessKey(savedAppraisalHdr3.get(0));
		businessKeyList4.add(businessKey4);
		empReject.setBusinessKeys(businessKeyList4);
		List<EventKeyValue>ekvsList3= new ArrayList<>();
		EventKeyValue keyValue4= new EventKeyValue();
		keyValue2=formKeyValuePair(savedAppraisalHdr3.get(0), empReject);
		ekvsList3.add(keyValue2);
		empReject.setEventKeyValues(ekvsList3);
		eventRepository.save(empReject);
		eventPublisherService.pushEvent(empReject);	
		break;
	case"n2-review":
		Event n2Review=new Event();
		n2Review.setName(Event.EventTypes.N2reviewAppraisalEvent);
		n2Review.setCategory(Event.EventCategory.NotificationEvent);
		n2Review.setAppToken(n2Review.getAppToken());
		Event savedN2ReviewEvent=eventRepository.save(n2Review);
		int appraisalId5=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appraisalHdrQuery5 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId5,
						AppraisalHdr.class);
		List<AppraisalHdr> savedAppraisalHdr6 = appraisalHdrQuery5.getResultList();
		List<BusinessKey> businessKeyList6 = new ArrayList<BusinessKey>();
		BusinessKey businessKey5= new BusinessKey();
		businessKey5=formBusinessKey(savedAppraisalHdr6.get(0));
		businessKeyList6.add(businessKey5);
		n2Review.setBusinessKeys(businessKeyList6);
		List<EventKeyValue>ekvsList5= new ArrayList<>();
		EventKeyValue keyValue5= new EventKeyValue();
		keyValue5=formKeyValuePair(savedAppraisalHdr6.get(0), n2Review);
		ekvsList5.add(keyValue5);
		n2Review.setEventKeyValues(ekvsList5);
		eventRepository.save(n2Review);
		eventPublisherService.pushEvent(n2Review);	
		break;
	case"dir-approve":
		Event dirApprove=new Event();
		dirApprove.setName(Event.EventTypes.DirApprovalAppraisalEvent);
		dirApprove.setCategory(Event.EventCategory.NotificationEvent);
		dirApprove.setAppToken(dirApprove.getAppToken());
		Event savedEmpAcceptEvent7=eventRepository.save(dirApprove);
		int appraisalId7=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appraisalHdrQuery7 = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalId7,
						AppraisalHdr.class);
		List<AppraisalHdr> savedAppraisalHdr7 = appraisalHdrQuery7.getResultList();
		List<BusinessKey> businessKeyList5 = new ArrayList<BusinessKey>();
		BusinessKey businessKey6= new BusinessKey();
		businessKey6=formBusinessKey(savedAppraisalHdr7.get(0));
		businessKeyList5.add(businessKey6);
		dirApprove.setBusinessKeys(businessKeyList5);
		List<EventKeyValue>ekvsList6= new ArrayList<>();
		EventKeyValue keyValue31= new EventKeyValue();
		keyValue2=formKeyValuePair(savedAppraisalHdr7.get(0), dirApprove);
		ekvsList6.add(keyValue2);
		dirApprove.setEventKeyValues(ekvsList6);
		eventRepository.save(dirApprove);
		eventPublisherService.pushEvent(dirApprove);	
	break;
	default:
		break;
	}
   
   }


public BusinessKey formBusinessKey(AppraisalHdr appraisalHdrBK)throws SmartOfficeException{
	BusinessKey businessKey = new BusinessKey();
	businessKey.setHr1UserGroupId(appraisalHdrBK.getSettleUserGroupId());
	businessKey.setHr2UserGroupId(appraisalHdrBK.getSettleUserGroup2Id());
	businessKey.setN1EmpId(appraisalHdrBK.getN1EmpId());
	businessKey.setDirUserGroupId(appraisalHdrBK.getDirUserGroupId());
	businessKey.setEmployeeId(appraisalHdrBK.getEmpId());
	
	return businessKeyRepository.save(businessKey);
}
public EventKeyValue formKeyValuePair(AppraisalHdr appraisalHdrBK,Event savedEvent)throws SmartOfficeException{
	List<EventKeyValue>ekvs= new ArrayList<>();
	EventKeyValue keyValue= new EventKeyValue();
	
	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1Name", appraisalHdrBK.getSettleEmpName());
	ekvs.add(keyValue);

	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "n1Name", appraisalHdrBK.getN1EmpFName()+appraisalHdrBK.getN1EmpLName());
	ekvs.add(keyValue);
	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "n2Name", appraisalHdrBK.getN2EmpFName()+appraisalHdrBK.getN2EmpLName());
	ekvs.add(keyValue);
	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "dirName", appraisalHdrBK.getDirEmpName());
	ekvs.add(keyValue);
	
	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empName", appraisalHdrBK.getEmpName());
	ekvs.add(keyValue);
	eventKeyValueRepository.save(keyValue);
	ekvs.add(keyValue);
	return keyValue;
}

@Async("asyncThreadPoolTaskExecutor")
public void triggerAppraialAcheivmentEvents(AppraisalHdr savedAppraisalHsr,String action,AuthUser loggedInUser)throws SmartOfficeException{
	commonUtils.setAuthenticationContext(loggedInUser,"async");
	switch (action) {
	case "initiate":
		Event event = new Event();
		event.setName(Event.EventTypes.InitiateAppraisalAchvmtEvent);
		event.setCategory(Event.EventCategory.NotificationEvent);
		event.setAppToken(event.getAppToken());
		eventRepository.save(event);
		int appraisalHdrId=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQuery = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrId,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrId = appHdrQuery.getResultList();
		System.out.println("appHdrId=" + appHdrId);
		List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
		BusinessKey businessKey= new BusinessKey();
		businessKey=formBusinessKey(appHdrId.get(0));
		businessKeys.add(businessKey);
		event.setBusinessKeys(businessKeys);
		List<EventKeyValue>ekvs= new ArrayList<>();
		EventKeyValue keyValue= new EventKeyValue();
		keyValue=formKeyValuePair(appHdrId.get(0), event);
		ekvs.add(keyValue);
		event.setEventKeyValues(ekvs);
		eventRepository.save(event);
		eventPublisherService.pushEvent(event);
		break;
	case "start":
		Event startEvent= new Event();
		startEvent.setName(Event.EventTypes.StartAppraisalAchvmtEvent);
		startEvent.setCategory(Event.EventCategory.NotificationEvent);
		startEvent.setAppToken(startEvent.getAppToken());
		eventRepository.save(startEvent);
		int appraisalHdrIdStart=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQueryStart = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrIdStart,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrIdStart = appHdrQueryStart.getResultList();
		System.out.println("appHdrId=" + appHdrIdStart);
		List<BusinessKey> businessKeysStart = new ArrayList<BusinessKey>();
		BusinessKey businessKeyStart11= new BusinessKey();
		businessKeyStart11=formBusinessKey(appHdrIdStart.get(0));
		businessKeysStart.add(businessKeyStart11);
		startEvent.setBusinessKeys(businessKeysStart);
		List<EventKeyValue>ekvs1= new ArrayList<>();
		EventKeyValue keyValue1= new EventKeyValue();
		keyValue1=formKeyValuePair(appHdrIdStart.get(0), startEvent);
		ekvs1.add(keyValue1);
		startEvent.setEventKeyValues(ekvs1);
		eventRepository.save(startEvent);
		eventPublisherService.pushEvent(startEvent);
		break;
	case "submit":
		Event submitEvent= new Event();
		submitEvent.setName(Event.EventTypes.SubmitAppraisalAchvmtEvent);
		submitEvent.setCategory(Event.EventCategory.NotificationEvent);
		submitEvent.setAppToken(submitEvent.getAppToken());
		eventRepository.save(submitEvent);
		int appraisalHdrIdSubmit=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQuerySubmit = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrIdSubmit,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrIdSubmit = appHdrQuerySubmit.getResultList();
		System.out.println("appHdrId=" + appHdrIdSubmit);
		List<BusinessKey> businessKeysSubmit = new ArrayList<BusinessKey>();
		BusinessKey businessKeySubmit11= new BusinessKey();
		businessKeySubmit11=formBusinessKey(appHdrIdSubmit.get(0));
		businessKeysSubmit.add(businessKeySubmit11);
		submitEvent.setBusinessKeys(businessKeysSubmit);
		List<EventKeyValue>ekvs11= new ArrayList<>();
		EventKeyValue keyValue12= new EventKeyValue();
		keyValue12=formKeyValuePair(appHdrIdSubmit.get(0), submitEvent);
		ekvs11.add(keyValue12);
		submitEvent.setEventKeyValues(ekvs11);
		eventRepository.save(submitEvent);
		eventPublisherService.pushEvent(submitEvent);
		break;
	case "n1-update":
		Event n1Event= new Event();
		n1Event.setName(Event.EventTypes.N1UpdateAppraisalAchvmtEvent);
		n1Event.setCategory(Event.EventCategory.NotificationEvent);
		n1Event.setAppToken(n1Event.getAppToken());
		eventRepository.save(n1Event);
		int appraisalHdrIdN1Update=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQueryN1Update = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrIdN1Update,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrIdN1Update = appHdrQueryN1Update.getResultList();
		System.out.println("appHdrId=" + appHdrIdN1Update);
		List<BusinessKey> businessKeysN1Update = new ArrayList<BusinessKey>();
		BusinessKey businessKeyN1Update1= new BusinessKey();
		businessKeyN1Update1=formBusinessKey(appHdrIdN1Update.get(0));
		businessKeysN1Update.add(businessKeyN1Update1);
		n1Event.setBusinessKeys(businessKeysN1Update);
		List<EventKeyValue>ekvs111= new ArrayList<>();
		EventKeyValue keyValue121= new EventKeyValue();
		keyValue121=formKeyValuePair(appHdrIdN1Update.get(0), n1Event);
		ekvs111.add(keyValue121);
		n1Event.setEventKeyValues(ekvs111);
		eventRepository.save(n1Event);
		eventPublisherService.pushEvent(n1Event);
		
		break;
	case "dir-approve":
		Event dirApprove = new Event();
		dirApprove.setName(Event.EventTypes.DirApprovalAppraisalAchvmtEvent);
		dirApprove.setCategory(Event.EventCategory.NotificationEvent);
		dirApprove.setAppToken(dirApprove.getAppToken());
		eventRepository.save(dirApprove);
		int appraisalHdrIdDir=savedAppraisalHsr.getId();
		TypedQuery<AppraisalHdr> appHdrQueryDir = entityManager.createQuery(
				"SELECT s from com.ss.smartoffice.soservice.transaction.appraisalservice.AppraisalHdr s where id="
						+ appraisalHdrIdDir,
				AppraisalHdr.class);
		List<AppraisalHdr> appHdrIdN1Dir = appHdrQueryDir.getResultList();
		System.out.println("appHdrId=" + appHdrIdN1Dir);
		List<BusinessKey> businessKeysDirList = new ArrayList<BusinessKey>();
		BusinessKey businessKeyDir= new BusinessKey();
		businessKeyDir=formBusinessKey(appHdrIdN1Dir.get(0));
		businessKeysDirList.add(businessKeyDir);
		dirApprove.setBusinessKeys(businessKeysDirList);
		List<EventKeyValue>ekvsDir= new ArrayList<>();
		EventKeyValue keyValue1211= new EventKeyValue();
		keyValue1211=formKeyValuePair(appHdrIdN1Dir.get(0), dirApprove);
		ekvsDir.add(keyValue1211);
		dirApprove.setEventKeyValues(ekvsDir);
		eventRepository.save(dirApprove);
		eventPublisherService.pushEvent(dirApprove);
		break;
	default:
		break;
	}
}


public BusinessKey formBusinessKeyForAchvmts(AppraisalHdr appraisalHdrBK)throws SmartOfficeException{
	BusinessKey businessKey = new BusinessKey();
	businessKey.setHr1UserGroupId(appraisalHdrBK.getSettleUserGroupId());	
	businessKey.setN1EmpId(appraisalHdrBK.getN1EmpId());
	businessKey.setDirUserGroupId(appraisalHdrBK.getDirUserGroupId());
	businessKey.setEmployeeId(appraisalHdrBK.getEmpId());
	
	return businessKeyRepository.save(businessKey);
}
public EventKeyValue formKeyValuePairForAchvmts(AppraisalHdr appraisalHdrBK,Event savedEvent)throws SmartOfficeException{
	List<EventKeyValue>ekvs= new ArrayList<>();
	EventKeyValue keyValue= new EventKeyValue();
	
	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "hr1Name", appraisalHdrBK.getSettleEmpName());
	ekvs.add(keyValue);

	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "n1Name", appraisalHdrBK.getN1EmpFName()+appraisalHdrBK.getN1EmpLName());
	ekvs.add(keyValue);

	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "dirName", appraisalHdrBK.getDirEmpName());
	ekvs.add(keyValue);
	
	keyValue = new EventKeyValue(null, savedEvent.getId().toString(), "empName", appraisalHdrBK.getEmpName());
	ekvs.add(keyValue);
	eventKeyValueRepository.save(keyValue);
	ekvs.add(keyValue);
	return keyValue;
}
}
