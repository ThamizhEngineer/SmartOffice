package com.ss.smartoffice.soservice.transaction.expenseclaim;

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

@Service
public class ExpenseClaimEventGenerator {
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
	public ExpenseClaim triggerExpenseClaimEvents(ExpenseClaim expenseClaim, String action, AuthUser loggedInUser)
			throws SmartOfficeException {

		commonUtils.setAuthenticationContext(loggedInUser,"async");
		switch (action) {

		case ("apply"):
			Event event = new Event();
			event.setName(Event.EventTypes.ExpenseAppliedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setAppToken(event.getAppToken());
			Event savedEvent = eventRepository.save(event);
			int expenseId = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById = expenseQuery.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setHr1EmpId(expenseClaimBK.getSettleEmpId());
				businessKey.setAcc1UserGroupId(expenseClaimBK.getValidateUserGroupId());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				event.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent.getId()));
					eventKeyValue.setKeyPair("employeeFullName");
					eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent.getId()));
					eventKeyValue1.setKeyPair("reqAdvAmount");
					eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue2.setKeyPair("acc1UsrGrpId");
				eventKeyValue2.setValue(expenseClaimBK.getValidateUserGroupName());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent.getId()));
					eventKeyValue3.setKeyPair("hrEmpName");
					eventKeyValue3.setValue(expenseClaimBK.getSettleEmpName());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue4 = new EventKeyValue();
				eventKeyValue4.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue4.setKeyPair("n1EmpCode");

				eventKeyValue4.setValue(expenseClaimBK.getN1EmpCode());
				
				eventKeyValueRepository.save(eventKeyValue4);
				eventKeyValues.add(eventKeyValue4);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue5.setKeyPair("n1EmpFullName");
				eventKeyValue5.setValue(expenseClaimBK.getN1EmpFName()+expenseClaimBK.getN1EmpLName());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				event.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(event);
			}
			break;
		case ("validate"):
			System.out.println(action +"came into event");
			Event validateEvent = new Event();
			validateEvent.setName(Event.EventTypes.ExpenseValidatedEvent);
			validateEvent.setCategory(Event.EventCategory.NotificationEvent);
			validateEvent.setAppToken(validateEvent.getAppToken());
			Event savedValidateEvent = eventRepository.save(validateEvent);
			int expenseValidateID = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseNativeQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseValidateID,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseBysId = expenseNativeQuery.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseBysId) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setAcc1UserGroupId(expenseClaimBK.getValidateUserGroupId());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				validateEvent.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedValidateEvent.getId()));
					eventKeyValue.setKeyPair("employeeFullName");
					eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedValidateEvent.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedValidateEvent.getId()));
				eventKeyValue2.setKeyPair("acc1UsrGrpId");
				eventKeyValue2.setValue(expenseClaimBK.getValidateUserGroupName());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue4 = new EventKeyValue();
				eventKeyValue4.setEventId(String.valueOf(savedValidateEvent.getId()));
				eventKeyValue4.setKeyPair("n1EmpCode");

				eventKeyValue4.setValue(expenseClaimBK.getN1EmpCode());
				
				eventKeyValueRepository.save(eventKeyValue4);
				eventKeyValues.add(eventKeyValue4);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedValidateEvent.getId()));
				eventKeyValue5.setKeyPair("n1EmpFullName");
				eventKeyValue5.setValue(expenseClaimBK.getN1EmpFName()+expenseClaimBK.getN1EmpLName());
				eventKeyValueRepository.save(eventKeyValue5);
				validateEvent.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(validateEvent);
			}
			break;
		case ("validation-reject"):
			Event validateRejEvent = new Event();
			validateRejEvent.setName(Event.EventTypes.ExpenseValidationRejectedEvent);
			validateRejEvent.setCategory(Event.EventCategory.NotificationEvent);
			validateRejEvent.setAppToken(validateRejEvent.getAppToken());
			Event savedValidateRejEvent = eventRepository.save(validateRejEvent);
			int expenseValidateRejID = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseNativeRejQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseValidateRejID,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseByRejId = expenseNativeRejQuery.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseByRejId) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc1UserGroupId(expenseClaimBK.getValidateUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				validateRejEvent.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedValidateRejEvent.getId()));
					eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedValidateRejEvent.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedValidateRejEvent.getId()));
				eventKeyValue2.setKeyPair("acc1UsrGrpId");
				eventKeyValue2.setValue(expenseClaimBK.getValidateUserGroupName());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue4 = new EventKeyValue();
				eventKeyValue4.setEventId(String.valueOf(savedValidateRejEvent.getId()));
				eventKeyValue4.setKeyPair("n1EmpCode");

				eventKeyValue4.setValue(expenseClaimBK.getN1EmpCode());
				
				eventKeyValueRepository.save(eventKeyValue4);
				eventKeyValues.add(eventKeyValue4);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedValidateRejEvent.getId()));
				eventKeyValue5.setKeyPair("n1EmpFullName");
				eventKeyValue5.setValue(expenseClaimBK.getN1EmpFName()+expenseClaimBK.getN1EmpLName());
				eventKeyValueRepository.save(eventKeyValue5);
				validateRejEvent.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(validateRejEvent);
			}
			break;

		case ("n1-approve"):
			Event event1 = new Event();
			event1.setName(Event.EventTypes.ExpenseN1ApprovedEvent);
			event1.setCategory(Event.EventCategory.NotificationEvent);
			event1.setEntityStatus(expenseClaim.getExpenseClaimStatus());
			event1.setAppToken(event1.getAppToken());
			Event savedEvent1 = eventRepository.save(event1);
			int expenseId1 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery1 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId1,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById1 = expenseQuery1.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById1) {

				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKey.setN2EmpId(expenseClaimBK.getN2EmpId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent1.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue2.setKeyPair("n1EmpCode");

				eventKeyValue2.setValue(expenseClaimBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue3.setKeyPair("n1EmpFullName");
				eventKeyValue3.setValue(expenseClaimBK.getN1EmpFName()+expenseClaimBK.getN1EmpLName());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue21 = new EventKeyValue();
				eventKeyValue21.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue21.setKeyPair("acc2UsrGrpId");
				eventKeyValue21.setValue(expenseClaimBK.getSettleUserGroupName());
				eventKeyValueRepository.save(eventKeyValue21);
				eventKeyValues.add(eventKeyValue21);
				EventKeyValue eventKeyValue5 = new EventKeyValue();
				eventKeyValue5.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue5.setKeyPair("n2EmpCode");

				eventKeyValue5.setValue(expenseClaimBK.getN2EmpCode());
				eventKeyValueRepository.save(eventKeyValue5);
				eventKeyValues.add(eventKeyValue5);
				EventKeyValue eventKeyValue6 = new EventKeyValue();
				eventKeyValue6.setEventId(String.valueOf(savedEvent1.getId()));
				eventKeyValue6.setKeyPair("n2EmpFullName");
				eventKeyValue6.setValue(expenseClaimBK.getN2EmpFName()+expenseClaimBK.getN2EmpLName());
				eventKeyValueRepository.save(eventKeyValue6);
				eventKeyValues.add(eventKeyValue6);
				savedEvent1.setEventKeyValues(eventKeyValues);
				savedEvent1.setBusinessKeys(businessKeys);
				eventPublisherService.pushEvent(event1);
			}
			break;

		case ("n1-reject"):
			Event event2 = new Event();
			event2.setName(Event.EventTypes.ExpenseN1RejectedEvent);
			event2.setCategory(Event.EventCategory.NotificationEvent);
			event2.setAppToken(event2.getAppToken());
			Event savedEvent2 = eventRepository.save(event2);
			int expenseId11 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery11 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId11,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById11 = expenseQuery11.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById11) {

				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();

				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent2.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue2.setKeyPair("n1EmpCode");

				eventKeyValue2.setValue(expenseClaimBK.getN1EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue3.setKeyPair("n1EmpFullName");
				eventKeyValue3.setValue(expenseClaimBK.getN1EmpFName()+expenseClaimBK.getN1EmpLName());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue21 = new EventKeyValue();
				eventKeyValue21.setEventId(String.valueOf(savedEvent2.getId()));
				eventKeyValue21.setKeyPair("acc2UsrGrpId");
				eventKeyValue21.setValue(expenseClaimBK.getSettleUserGroupName());
				eventKeyValueRepository.save(eventKeyValue21);
				eventKeyValues.add(eventKeyValue21);
			
				savedEvent2.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(event2);
				savedEvent2.setBusinessKeys(businessKeys);
				eventPublisherService.pushEvent(event2);
			}
			break;

		case ("n2-approve"):
			Event event3 = new Event();
			event3.setName(Event.EventTypes.ExpenseN2ApprovedEvent);
			event3.setCategory(Event.EventCategory.NotificationEvent);
			event3.setAppToken(event3.getAppToken());
			event3.setEntityStatus(expenseClaim.getExpenseClaimStatus());
			Event savedEvent3 = eventRepository.save(event3);
			int expenseId111 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery111 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId111,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById111 = expenseQuery111.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById111) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setN2EmpId(expenseClaimBK.getN2EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent3.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue2.setKeyPair("n2EmpCode");

				eventKeyValue2.setValue(expenseClaimBK.getN2EmpCode());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue3.setKeyPair("n2EmpFullName");
				eventKeyValue3.setValue(expenseClaimBK.getN2EmpFName()+expenseClaimBK.getN2EmpLName());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue21 = new EventKeyValue();
				eventKeyValue21.setEventId(String.valueOf(savedEvent3.getId()));
				eventKeyValue21.setKeyPair("acc2UsrGrpId");
				eventKeyValue21.setValue(expenseClaimBK.getSettleUserGroupName());
				eventKeyValueRepository.save(eventKeyValue21);
				eventKeyValues.add(eventKeyValue21);
				savedEvent3.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(event3);
			}
			break;
		case ("n2-reject"):
			Event event4 = new Event();
			event4.setName(Event.EventTypes.ExpenseN2RejectedEvent);
			event4.setCategory(Event.EventCategory.NotificationEvent);
			event4.setAppToken(event4.getAppToken());
			Event savedEvent4 = eventRepository.save(event4);
			int expenseId1111 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery1111 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId1111,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById1111 = expenseQuery1111.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById1111) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setN2EmpId(expenseClaimBK.getN2EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent4.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
					eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
					eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue2.setKeyPair("n2EmpCode");

				eventKeyValue2.setValue(expenseClaimBK.getN2EmpCode());
					eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue3.setKeyPair("n2EmpFullName");
				eventKeyValue3.setValue(expenseClaimBK.getN2EmpFName()+expenseClaimBK.getN2EmpLName());
					eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue21 = new EventKeyValue();
				eventKeyValue21.setEventId(String.valueOf(savedEvent4.getId()));
				eventKeyValue21.setKeyPair("acc2UsrGrpId");
				eventKeyValue21.setValue(expenseClaimBK.getSettleUserGroupName());
				eventKeyValueRepository.save(eventKeyValue21);
				eventKeyValues.add(eventKeyValue21);
				savedEvent4.setEventKeyValues(eventKeyValues);
				savedEvent4.setBusinessKeys(businessKeys);
				eventPublisherService.pushEvent(savedEvent4);
			}
			break;
		case ("cancel"):
			Event event5 = new Event();
			event5.setName(Event.EventTypes.ExpenseCancelledEvent);
			event5.setCategory(Event.EventCategory.NotificationEvent);
			event5.setAppToken(event5.getAppToken());
			Event savedEvent5 = eventRepository.save(event5);
			int expenseId11111 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery11111 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId11111,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById11111 = expenseQuery11111.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById11111) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setHr1EmpId(expenseClaimBK.getSettleEmpId());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent5.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent5.getId()));
					eventKeyValue.setKeyPair("employeeFullName");
					eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent5.getId()));
					eventKeyValue1.setKeyPair("reqAdvAmount");
					eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent5.getId()));
					eventKeyValue3.setKeyPair("hrEmpName");
					eventKeyValue3.setValue(expenseClaimBK.getSettleEmpName());
				eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				
				savedEvent5.setBusinessKeys(businessKeys);
				savedEvent5.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent5);
			}
			break;
		case ("settle"):
			System.out.println(action);
			Event event6 = new Event();
			event6.setName(Event.EventTypes.ExpenseSettledEvent);
			event6.setCategory(Event.EventCategory.NotificationEvent);
			event6.setAppToken(event6.getAppToken());
			Event savedEvent6 = eventRepository.save(event6);
			int expenseId111111 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery111111 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId111111,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById111111 = expenseQuery111111.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById111111) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKey.setN2EmpId(expenseClaimBK.getN2EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent6.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(event6.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
					eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(event6.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
					eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(event6.getId()));
				eventKeyValue2.setKeyPair("n2EmpCode");

				eventKeyValue2.setValue(expenseClaimBK.getN2EmpCode());
					eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(event6.getId()));
				eventKeyValue3.setKeyPair("n2EmpFullName");
				eventKeyValue3.setValue(expenseClaimBK.getN2EmpFName()+expenseClaimBK.getN2EmpLName());
					eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue21 = new EventKeyValue();
				eventKeyValue21.setEventId(String.valueOf(event6.getId()));
				eventKeyValue21.setKeyPair("acc2UsrGrpId");
				eventKeyValue21.setValue(expenseClaimBK.getSettleUserGroupName());
				eventKeyValueRepository.save(eventKeyValue21);
				eventKeyValues.add(eventKeyValue21);
				savedEvent6.setBusinessKeys(businessKeys);
				savedEvent6.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent6);
			}
			break;
		case ("settle-reject"):
			Event event61 = new Event();
			event61.setName(Event.EventTypes.ExpenseSettlementRejectedEvent);
			event61.setCategory(Event.EventCategory.NotificationEvent);
			event61.setAppToken(event61.getAppToken());
			Event savedEvent61 = eventRepository.save(event61);
			int expenseId1111111 = expenseClaim.getId();
			TypedQuery<ExpenseClaim> expenseQuery1111111 = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.expenseclaim.ExpenseClaim s where id="
							+ expenseId1111111,
					ExpenseClaim.class);
			List<ExpenseClaim> expenseById1111111 = expenseQuery1111111.getResultList();
			for (ExpenseClaim expenseClaimBK : expenseById1111111) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setExpenseClaimCode(expenseClaimBK.getExpenseClaimCode());
				businessKey.setEmployeeCode(expenseClaimBK.getEmployeeCode());
				businessKey.setEmployeeId(expenseClaimBK.getEmployeeId());
				businessKey.setN1EmpId(expenseClaimBK.getN1EmpId());
				businessKey.setN2EmpId(expenseClaimBK.getN2EmpId());
				businessKey.setHr1EmpId(expenseClaimBK.getValidateEmpId());
				businessKey.setAcc2UserGroupId(expenseClaimBK.getSettleUserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				savedEvent61.setBusinessKeys(businessKeys);
				List<EventKeyValue> eventKeyValues = new ArrayList<>();
				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(event61.getId()));
				eventKeyValue.setKeyPair("employeeFullName");
				eventKeyValue.setValue(expenseClaimBK.getEmployeeFName()+expenseClaimBK.getEmployeeLName());
					eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);
				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(event61.getId()));
				eventKeyValue1.setKeyPair("reqAdvAmount");
				eventKeyValue1.setValue(String.valueOf(expenseClaimBK.getExpenseClaimAmount()));
					eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(event61.getId()));
				eventKeyValue2.setKeyPair("n2EmpCode");

				eventKeyValue2.setValue(expenseClaimBK.getN2EmpCode());
					eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(event61.getId()));
				eventKeyValue3.setKeyPair("n2EmpFullName");
				eventKeyValue3.setValue(expenseClaimBK.getN2EmpFName()+expenseClaimBK.getN2EmpLName());
					eventKeyValueRepository.save(eventKeyValue3);
				eventKeyValues.add(eventKeyValue3);
				EventKeyValue eventKeyValue21 = new EventKeyValue();
				eventKeyValue21.setEventId(String.valueOf(event61.getId()));
				eventKeyValue21.setKeyPair("acc2UsrGrpId");
				eventKeyValue21.setValue(expenseClaimBK.getSettleUserGroupName());
				eventKeyValueRepository.save(eventKeyValue21);
				eventKeyValues.add(eventKeyValue21);
				savedEvent61.setBusinessKeys(businessKeys);
				savedEvent61.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent61);
			}
			break;
			

		}
		return expenseClaim;
	}

}
