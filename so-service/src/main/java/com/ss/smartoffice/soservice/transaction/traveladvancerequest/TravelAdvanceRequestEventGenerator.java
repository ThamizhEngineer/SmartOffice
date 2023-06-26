package com.ss.smartoffice.soservice.transaction.traveladvancerequest;

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
public class TravelAdvanceRequestEventGenerator {

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
	public void triggerTarEvents(TravelAdvanceRequest savedTar, String action, AuthUser loggedInUser)
			throws SmartOfficeException {

    	commonUtils.setAuthenticationContext(loggedInUser,"async");
		if (action.equals("apply")) {
			Event event = new Event();
			event.setName(Event.EventTypes.TarAppliedEvent);
			event.setCategory(Event.EventCategory.NotificationEvent);
			event.setAppToken(event.getAppToken());
			Event savedEvent = eventRepository.save(event);
			int tarId = savedTar.getId();
			TypedQuery<TravelAdvanceRequest> tarQuery = entityManager.createQuery(
					"SELECT s from com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest s where id="
							+ tarId,
					TravelAdvanceRequest.class);
			List<TravelAdvanceRequest> tarById = tarQuery.getResultList();

			System.out.println("tarById=" + tarById);
			for (TravelAdvanceRequest travelAdvanceRequestBK : tarById) {
				List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
				BusinessKey businessKey = new BusinessKey();
				businessKey.setEventId(String.valueOf(savedEvent.getId()));
				businessKey.setTarCode(savedTar.getTarCode());
				businessKey.setEmployeeId(travelAdvanceRequestBK.getEmployeeId());
				businessKey.setEmployeeCode(travelAdvanceRequestBK.getEmployeeCode());
				businessKey.setN1EmpId(travelAdvanceRequestBK.getN1EmployeeId());
				// businessKey.setAcc2UserGroupId(travelAdvanceRequestBK.getAcc2UserGroupId());
				businessKeyRepository.save(businessKey);
				businessKeys.add(businessKey);
				event.setBusinessKeys(businessKeys);

				List<EventKeyValue> eventKeyValues = new ArrayList<>();

				EventKeyValue eventKeyValue = new EventKeyValue();
				eventKeyValue.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue.setKeyPair("reqAdvAmount");
				String reqAdvAmt = String.valueOf(travelAdvanceRequestBK.getReqAdvAmt());
				eventKeyValue.setValue(reqAdvAmt);
				eventKeyValueRepository.save(eventKeyValue);
				eventKeyValues.add(eventKeyValue);

				EventKeyValue eventKeyValue1 = new EventKeyValue();
				eventKeyValue1.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue1.setKeyPair("n1EmpCode");

				eventKeyValue1.setValue(travelAdvanceRequestBK.getN1EmployeeCode());
				eventKeyValueRepository.save(eventKeyValue1);
				eventKeyValues.add(eventKeyValue1);
				EventKeyValue eventKeyValue2 = new EventKeyValue();
				eventKeyValue2.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue2.setKeyPair("n1EmpFullName");
				eventKeyValue2.setValue(travelAdvanceRequestBK.getN1EmployeeName());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue2);
				EventKeyValue eventKeyValue3 = new EventKeyValue();
				eventKeyValue3.setEventId(String.valueOf(savedEvent.getId()));
				eventKeyValue3.setKeyPair("employeeFullName");
				eventKeyValue3.setValue(travelAdvanceRequestBK.getEmployeeName());
				eventKeyValueRepository.save(eventKeyValue2);
				eventKeyValues.add(eventKeyValue3);

				savedEvent.setEventKeyValues(eventKeyValues);
				eventPublisherService.pushEvent(savedEvent);

				System.out.println("notification event pushed  in notification receiver" + "=" + "" + savedEvent);
			}
		}
			else if (action.equals("n1-approve")) {
				
				Event event1 = new Event();
				event1.setName(Event.EventTypes.TarN1ApprovedEvent);
				event1.setCategory(Event.EventCategory.NotificationEvent);
				event1.setAppToken(event1.getAppToken());
				Event savedEvent1 = eventRepository.save(event1);
				int tarId1 = savedTar.getId();
				TypedQuery<TravelAdvanceRequest> tarQuery1 = entityManager.createQuery(
						"SELECT s from com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest s where id="
								+ tarId1,
						TravelAdvanceRequest.class);
				List<TravelAdvanceRequest> tarById1 = tarQuery1.getResultList();

				System.out.println("tarById=" + tarById1);
				for (TravelAdvanceRequest travelAdvanceRequestBK : tarById1) {
					List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
					BusinessKey businessKey = new BusinessKey();
					businessKey.setEventId(String.valueOf(savedEvent1.getId()));
					businessKey.setTarCode(savedTar.getTarCode());
					businessKey.setEmployeeId(travelAdvanceRequestBK.getEmployeeId());
					businessKey.setEmployeeCode(travelAdvanceRequestBK.getEmployeeCode());
					businessKey.setN1EmpId(travelAdvanceRequestBK.getN1EmployeeId());
					businessKey.setAcc2UserGroupId(travelAdvanceRequestBK.getAcc2UserGroupId());
					businessKeyRepository.save(businessKey);
					businessKeys.add(businessKey);
					event1.setBusinessKeys(businessKeys);

					List<EventKeyValue> eventKeyValues = new ArrayList<>();

					EventKeyValue eventKeyValue = new EventKeyValue();
					eventKeyValue.setEventId(String.valueOf(savedEvent1.getId()));
					eventKeyValue.setKeyPair("reqAdvAmount");
					String reqAdvAmt = String.valueOf(travelAdvanceRequestBK.getReqAdvAmt());
					eventKeyValue.setValue(reqAdvAmt);
					eventKeyValueRepository.save(eventKeyValue);
					eventKeyValues.add(eventKeyValue);

					EventKeyValue eventKeyValue1 = new EventKeyValue();
					eventKeyValue1.setEventId(String.valueOf(savedEvent1.getId()));
					eventKeyValue1.setKeyPair("n1EmpCode");

					eventKeyValue1.setValue(travelAdvanceRequestBK.getN1EmployeeCode());
					eventKeyValueRepository.save(eventKeyValue1);
					eventKeyValues.add(eventKeyValue1);
					EventKeyValue eventKeyValue2 = new EventKeyValue();
					eventKeyValue2.setEventId(String.valueOf(savedEvent1.getId()));
					eventKeyValue2.setKeyPair("n1EmpFullName");
					eventKeyValue2.setValue(travelAdvanceRequestBK.getN1EmployeeName());
					eventKeyValueRepository.save(eventKeyValue2);
					eventKeyValues.add(eventKeyValue2);
					EventKeyValue eventKeyValue3 = new EventKeyValue();
					eventKeyValue3.setEventId(String.valueOf(savedEvent1.getId()));
					eventKeyValue3.setKeyPair("employeeFullName");
					eventKeyValue3.setValue(travelAdvanceRequestBK.getEmployeeName());
					eventKeyValueRepository.save(eventKeyValue2);
					eventKeyValues.add(eventKeyValue3);
					EventKeyValue eventKeyValue4 = new EventKeyValue();
					eventKeyValue4.setEventId(String.valueOf(savedEvent1.getId()));
					eventKeyValue4.setKeyPair("acc2usergrp");
					eventKeyValue4.setValue(travelAdvanceRequestBK.getAcc2UserGroupName());
					eventKeyValueRepository.save(eventKeyValue4);
					eventKeyValues.add(eventKeyValue4);
					savedEvent1.setEventKeyValues(eventKeyValues);
					eventPublisherService.pushEvent(savedEvent1);
					System.out.println("notification event pushed  in notification receiver" + "=" + "" + savedEvent1);
				}
			}
				else if (action.equals("acc2-approve")) {
					Event event11 = new Event();
					event11.setName(Event.EventTypes.TarAcc2ApprovedEvent);
					event11.setCategory(Event.EventCategory.NotificationEvent);
					event11.setAppToken(event11.getAppToken());
					Event savedEvent11 = eventRepository.save(event11);
					int tarId11 = savedTar.getId();
					TypedQuery<TravelAdvanceRequest> tarQuery11 = entityManager.createQuery(
							"SELECT s from com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest s where id="
									+ tarId11,
							TravelAdvanceRequest.class);
					List<TravelAdvanceRequest> tarById11 = tarQuery11.getResultList();

				
					for (TravelAdvanceRequest travelAdvanceRequestBK : tarById11) {
						List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
						BusinessKey businessKey = new BusinessKey();
						businessKey.setEventId(String.valueOf(savedEvent11.getId()));
						businessKey.setTarCode(savedTar.getTarCode());
						businessKey.setEmployeeId(travelAdvanceRequestBK.getEmployeeId());
						businessKey.setEmployeeCode(travelAdvanceRequestBK.getEmployeeCode());
						businessKey.setN1EmpId(travelAdvanceRequestBK.getN1EmployeeId());
						businessKey.setAcc2UserGroupId(travelAdvanceRequestBK.getAcc2UserGroupId());
						businessKeyRepository.save(businessKey);
						businessKeys.add(businessKey);
						event11.setBusinessKeys(businessKeys);

						List<EventKeyValue> eventKeyValues = new ArrayList<>();

						EventKeyValue eventKeyValue = new EventKeyValue();
						eventKeyValue.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue.setKeyPair("reqAdvAmount");
						String reqAdvAmt = String.valueOf(travelAdvanceRequestBK.getReqAdvAmt());
						eventKeyValue.setValue(reqAdvAmt);
						eventKeyValueRepository.save(eventKeyValue);
						eventKeyValues.add(eventKeyValue);

						EventKeyValue eventKeyValue1 = new EventKeyValue();
						eventKeyValue1.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue1.setKeyPair("n1EmpCode");

						eventKeyValue1.setValue(travelAdvanceRequestBK.getN1EmployeeCode());
						eventKeyValueRepository.save(eventKeyValue1);
						eventKeyValues.add(eventKeyValue1);
						EventKeyValue eventKeyValue2 = new EventKeyValue();
						eventKeyValue2.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue2.setKeyPair("n1EmpFullName");
						eventKeyValue2.setValue(travelAdvanceRequestBK.getN1EmployeeName());
						eventKeyValueRepository.save(eventKeyValue2);
						eventKeyValues.add(eventKeyValue2);
						EventKeyValue eventKeyValue3 = new EventKeyValue();
						eventKeyValue3.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue3.setKeyPair("employeeFullName");
						eventKeyValue3.setValue(travelAdvanceRequestBK.getEmployeeName());
						eventKeyValueRepository.save(eventKeyValue2);
						eventKeyValues.add(eventKeyValue3);
						EventKeyValue eventKeyValue4 = new EventKeyValue();
						eventKeyValue4.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue4.setKeyPair("acc2usergrp");
						System.out.println(travelAdvanceRequestBK.getAcc2UserGroupName());
						eventKeyValue4.setValue(travelAdvanceRequestBK.getAcc2UserGroupName());
						eventKeyValueRepository.save(eventKeyValue4);
						eventKeyValues.add(eventKeyValue4);
						savedEvent11.setEventKeyValues(eventKeyValues);
						eventPublisherService.pushEvent(savedEvent11);
						System.out.println(
								"notification event pushed  in notification receiver" + "=" + "" + savedEvent11);

					}
				}
				else if (action.equals("n1-reject")) {
					Event event11 = new Event();
					event11.setName(Event.EventTypes.TarN1RejectedEvent);
					event11.setCategory(Event.EventCategory.NotificationEvent);
					event11.setAppToken(event11.getAppToken());
					Event savedEvent11 = eventRepository.save(event11);
					int tarId11 = savedTar.getId();
					TypedQuery<TravelAdvanceRequest> tarQuery11 = entityManager.createQuery(
							"SELECT s from com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest s where id="
									+ tarId11,
							TravelAdvanceRequest.class);
					List<TravelAdvanceRequest> tarById11 = tarQuery11.getResultList();

					
					for (TravelAdvanceRequest travelAdvanceRequestBK : tarById11) {
						List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
						BusinessKey businessKey = new BusinessKey();
						businessKey.setEventId(String.valueOf(savedEvent11.getId()));
						businessKey.setTarCode(savedTar.getTarCode());
						businessKey.setEmployeeId(travelAdvanceRequestBK.getEmployeeId());
						businessKey.setEmployeeCode(travelAdvanceRequestBK.getEmployeeCode());
						businessKey.setN1EmpId(travelAdvanceRequestBK.getN1EmployeeId());
						businessKey.setAcc2UserGroupId(travelAdvanceRequestBK.getAcc2UserGroupId());
						businessKeyRepository.save(businessKey);
						businessKeys.add(businessKey);
						event11.setBusinessKeys(businessKeys);

						List<EventKeyValue> eventKeyValues = new ArrayList<>();

						EventKeyValue eventKeyValue = new EventKeyValue();
						eventKeyValue.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue.setKeyPair("reqAdvAmount");
						String reqAdvAmt = String.valueOf(travelAdvanceRequestBK.getReqAdvAmt());
						eventKeyValue.setValue(reqAdvAmt);
						eventKeyValueRepository.save(eventKeyValue);
						eventKeyValues.add(eventKeyValue);

						EventKeyValue eventKeyValue1 = new EventKeyValue();
						eventKeyValue1.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue1.setKeyPair("n1EmpCode");

						eventKeyValue1.setValue(travelAdvanceRequestBK.getN1EmployeeCode());
						eventKeyValueRepository.save(eventKeyValue1);
						eventKeyValues.add(eventKeyValue1);
						EventKeyValue eventKeyValue2 = new EventKeyValue();
						eventKeyValue2.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue2.setKeyPair("n1EmpFullName");
						eventKeyValue2.setValue(travelAdvanceRequestBK.getN1EmployeeName());
						eventKeyValueRepository.save(eventKeyValue2);
						eventKeyValues.add(eventKeyValue2);
						EventKeyValue eventKeyValue3 = new EventKeyValue();
						eventKeyValue3.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue3.setKeyPair("employeeFullName");
						eventKeyValue3.setValue(travelAdvanceRequestBK.getEmployeeName());
						eventKeyValueRepository.save(eventKeyValue2);
						eventKeyValues.add(eventKeyValue3);
						EventKeyValue eventKeyValue4 = new EventKeyValue();
						eventKeyValue4.setEventId(String.valueOf(savedEvent11.getId()));
						eventKeyValue4.setKeyPair("acc2usergrp");
						System.out.println(travelAdvanceRequestBK.getAcc2UserGroupName());
						eventKeyValue4.setValue(travelAdvanceRequestBK.getAcc2UserGroupName());
						eventKeyValueRepository.save(eventKeyValue4);
						eventKeyValues.add(eventKeyValue4);
						savedEvent11.setEventKeyValues(eventKeyValues);
						eventPublisherService.pushEvent(savedEvent11);
						System.out.println(
								"notification event pushed  in notification receiver" + "=" + "" + savedEvent11);
					}
				}
			
				
					else if (action.equals("acc2-reject")) {
						Event event111 = new Event();
						event111.setName(Event.EventTypes.TarAcc2RejectedEvent);
						event111.setCategory(Event.EventCategory.NotificationEvent);
						event111.setAppToken(event111.getAppToken());
						Event savedEvent111 = eventRepository.save(event111);
						int tarId111 = savedTar.getId();
						TypedQuery<TravelAdvanceRequest> tarQuery111 = entityManager.createQuery(
								"SELECT s from com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest s where id="
										+ tarId111,
								TravelAdvanceRequest.class);
						List<TravelAdvanceRequest> tarById111 = tarQuery111.getResultList();

						
						for (TravelAdvanceRequest travelAdvanceRequestBK : tarById111) {
							List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
							BusinessKey businessKey = new BusinessKey();
							businessKey.setEventId(String.valueOf(savedEvent111.getId()));
							businessKey.setTarCode(savedTar.getTarCode());
							businessKey.setEmployeeId(travelAdvanceRequestBK.getEmployeeId());
							businessKey.setEmployeeCode(travelAdvanceRequestBK.getEmployeeCode());
							businessKey.setN1EmpId(travelAdvanceRequestBK.getN1EmployeeId());
							businessKey.setAcc2UserGroupId(travelAdvanceRequestBK.getAcc2UserGroupId());
							
							businessKeyRepository.save(businessKey);
							businessKeys.add(businessKey);
							event111.setBusinessKeys(businessKeys);

							List<EventKeyValue> eventKeyValues = new ArrayList<>();

							EventKeyValue eventKeyValue = new EventKeyValue();
							eventKeyValue.setEventId(String.valueOf(savedEvent111.getId()));
							eventKeyValue.setKeyPair("reqAdvAmount");
							String reqAdvAmt = String.valueOf(travelAdvanceRequestBK.getReqAdvAmt());
							eventKeyValue.setValue(reqAdvAmt);
							eventKeyValueRepository.save(eventKeyValue);
							eventKeyValues.add(eventKeyValue);

							EventKeyValue eventKeyValue1 = new EventKeyValue();
							eventKeyValue1.setEventId(String.valueOf(savedEvent111.getId()));
							eventKeyValue1.setKeyPair("n1EmpCode");

							eventKeyValue1.setValue(travelAdvanceRequestBK.getN1EmployeeCode());
							eventKeyValueRepository.save(eventKeyValue1);
							eventKeyValues.add(eventKeyValue1);
							EventKeyValue eventKeyValue2 = new EventKeyValue();
							eventKeyValue2.setEventId(String.valueOf(savedEvent111.getId()));
							eventKeyValue2.setKeyPair("n1EmpFullName");
							eventKeyValue2.setValue(travelAdvanceRequestBK.getN1EmployeeName());
							eventKeyValueRepository.save(eventKeyValue2);
							eventKeyValues.add(eventKeyValue2);
							EventKeyValue eventKeyValue3 = new EventKeyValue();
							eventKeyValue3.setEventId(String.valueOf(savedEvent111.getId()));
							eventKeyValue3.setKeyPair("employeeFullName");
							eventKeyValue3.setValue(travelAdvanceRequestBK.getEmployeeName());
							eventKeyValueRepository.save(eventKeyValue2);
							eventKeyValues.add(eventKeyValue3);
							EventKeyValue eventKeyValue4 = new EventKeyValue();
							eventKeyValue4.setEventId(String.valueOf(savedEvent111.getId()));
							eventKeyValue4.setKeyPair("acc2usergrp");
							System.out.println("accUserGrpName"+travelAdvanceRequestBK.getAcc2UserGroupName());
							eventKeyValue4.setValue(travelAdvanceRequestBK.getAcc2UserGroupName());
							eventKeyValues.add(eventKeyValue4);
							eventKeyValueRepository.save(eventKeyValue4);
							savedEvent111.setEventKeyValues(eventKeyValues);
							eventPublisherService.pushEvent(savedEvent111);
							System.out.println(
									"notification event pushed  in notification receiver" + "=" + "" + savedEvent111);
						}
					}
						else if (action.equals("cancel")) {
							Event event1111 = new Event();
							event1111.setName(Event.EventTypes.TarCancelledEvent);
							event1111.setCategory(Event.EventCategory.NotificationEvent);
							event1111.setAppToken(event1111.getAppToken());
							Event savedEvent1111 = eventRepository.save(event1111);
							int tarId1111 = savedTar.getId();
							TypedQuery<TravelAdvanceRequest> tarQuery1111 = entityManager.createQuery(
									"SELECT s from com.ss.smartoffice.soservice.transaction.traveladvancerequest.TravelAdvanceRequest s where id="
											+ tarId1111,
									TravelAdvanceRequest.class);
							List<TravelAdvanceRequest> tarById1111 = tarQuery1111.getResultList();

							System.out.println("tarById=" + tarById1111);
							for (TravelAdvanceRequest travelAdvanceRequestBK : tarById1111) {
								List<BusinessKey> businessKeys = new ArrayList<BusinessKey>();
								BusinessKey businessKey = new BusinessKey();
								businessKey.setEventId(String.valueOf(savedEvent1111.getId()));
								businessKey.setTarCode(savedTar.getTarCode());
								businessKey.setEmployeeId(travelAdvanceRequestBK.getEmployeeId());
								businessKey.setEmployeeCode(travelAdvanceRequestBK.getEmployeeCode());
								businessKey.setN1EmpId(travelAdvanceRequestBK.getN1EmployeeId());
								businessKey.setAcc2UserGroupId(travelAdvanceRequestBK.getAcc2UserGroupId());
								businessKeyRepository.save(businessKey);
								businessKeys.add(businessKey);
								event1111.setBusinessKeys(businessKeys);

								List<EventKeyValue> eventKeyValues = new ArrayList<>();

								EventKeyValue eventKeyValue = new EventKeyValue();
								eventKeyValue.setEventId(String.valueOf(savedEvent1111.getId()));
								eventKeyValue.setKeyPair("reqAdvAmount");
								String reqAdvAmt = String.valueOf(travelAdvanceRequestBK.getReqAdvAmt());
								eventKeyValue.setValue(reqAdvAmt);
								eventKeyValueRepository.save(eventKeyValue);
								eventKeyValues.add(eventKeyValue);

								EventKeyValue eventKeyValue1 = new EventKeyValue();
								eventKeyValue1.setEventId(String.valueOf(savedEvent1111.getId()));
								eventKeyValue1.setKeyPair("n1EmpCode");

								eventKeyValue1.setValue(travelAdvanceRequestBK.getN1EmployeeCode());
								eventKeyValueRepository.save(eventKeyValue1);
								eventKeyValues.add(eventKeyValue1);
								EventKeyValue eventKeyValue2 = new EventKeyValue();
								eventKeyValue2.setEventId(String.valueOf(savedEvent1111.getId()));
								eventKeyValue2.setKeyPair("n1EmpFullName");
								eventKeyValue2.setValue(travelAdvanceRequestBK.getN1EmployeeName());
								eventKeyValueRepository.save(eventKeyValue2);
								eventKeyValues.add(eventKeyValue2);
								EventKeyValue eventKeyValue3 = new EventKeyValue();
								eventKeyValue3.setEventId(String.valueOf(savedEvent1111.getId()));
								eventKeyValue3.setKeyPair("employeeFullName");
								eventKeyValue3.setValue(travelAdvanceRequestBK.getEmployeeName());
								eventKeyValueRepository.save(eventKeyValue2);
								eventKeyValues.add(eventKeyValue3);
								EventKeyValue eventKeyValue4 = new EventKeyValue();
								eventKeyValue4.setEventId(String.valueOf(savedEvent1111.getId()));
								eventKeyValue4.setKeyPair("acc2usergrp");
								System.out.println("acc2usergrp"+travelAdvanceRequestBK.getAcc2UserGroupName());
								eventKeyValue4.setValue(travelAdvanceRequestBK.getAcc2UserGroupName());
								eventKeyValueRepository.save(eventKeyValue4);
								eventKeyValues.add(eventKeyValue4);
								savedEvent1111.setEventKeyValues(eventKeyValues);
								eventPublisherService.pushEvent(savedEvent1111);
								System.out.println("notification event pushed  in notification receiver" + "=" + ""
										+ savedEvent1111);
							}
						}

					}

			
		
	}


