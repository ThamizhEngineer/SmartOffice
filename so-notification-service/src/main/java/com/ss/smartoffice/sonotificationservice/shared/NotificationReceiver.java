//package com.ss.smartoffice.sonotificationservice.shared;
//
//import java.io.IOException;
//import java.util.Map;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.stereotype.Service;
//import org.springframework.amqp.support.AmqpHeaders;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.rabbitmq.client.Channel;
//import com.ss.smartoffice.shared.common.CommonUtils;
//import com.ss.smartoffice.shared.model.event.Event;
//import com.ss.smartoffice.sonotificationservice.JobEvent.JobConfirmationEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeAcc1ManagerUpdateEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeAcc2ManagerUpdateEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeHr1ManagerUpdateEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeHr2ManagerUpdateEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeLeaveApplyingEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeN1ManagerUpdateProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeN2ManagerUpdateProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeePasswordResetEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.EmployeeUpdateEventProcessor;
//import com.ss.smartoffice.sonotificationservice.employee.NewUserEventProcessor;
////import com.ss.smartoffice.sonotificationservice.applicant.NewApplicantEventProcess;
//import com.ss.smartoffice.sonotificationservice.event.EventService;
//
//import com.ss.smartoffice.sonotificationservice.order.OrderCreationEventProcessor;
//import com.ss.smartoffice.sonotificationservice.payout.EmployeeSalaryCreatedEventProcessor;
//import com.ss.smartoffice.sonotificationservice.purchaseorder.PurchaseOrderEmailProcessor;
//import com.ss.smartoffice.sonotificationservice.saleorder.OrderAckProcessor;
//import com.ss.smartoffice.sonotificationservice.test.TestAckEmailProcess;
//import com.ss.smartoffice.sonotificationservice.test.TestCompleteEmailProcess;
//import com.ss.smartoffice.sonotificationservice.test.TestFailEmailProcess;
//
//@Service
//public class NotificationReceiver {
//
//	@Autowired
//	OrderCreationEventProcessor orderCreationEventProcessor;
//	@Autowired
//	EventService eventService;
//
//	@Autowired
//	EmployeeSalaryCreatedEventProcessor employeeSalaryCreatedEventProcessor;
//
//	@Autowired
//	NewUserEventProcessor newUserEventProcessor;
//
//	@Autowired
//	EmployeeUpdateEventProcessor employeeUpdateEventProcessor;
//	@Autowired
//	EmployeePasswordResetEventProcessor employeePasswordResetEventProcessor;
//	@Autowired
//	EmployeeLeaveApplyingEventProcessor employeeLeaveApplyingEventProcessor;
//	
//	@Autowired
//	JobConfirmationEventProcessor jobConfirmationEventProcessor;
//	
//	@Autowired
//	EmployeeN1ManagerUpdateProcessor employeeN1ManagerUpdateProcessor;
//	
//	@Autowired
//	EmployeeN2ManagerUpdateProcessor employeeN2ManagerUpdateProcessor;
//	
//	@Autowired
//	EmployeeHr1ManagerUpdateEventProcessor employeeHr1ManagerUpdateEventProcessor;
//	
//	@Autowired
//	EmployeeHr2ManagerUpdateEventProcessor employeeHr2ManagerUpdateEventProcessor;
//	
//	@Autowired
//	EmployeeAcc1ManagerUpdateEventProcessor employeeAcc1ManagerUpdateEventProcessor;
//	
//	@Autowired
//	EmployeeAcc2ManagerUpdateEventProcessor employeeAcc2ManagerUpdateEventProcessor;
//	
//	@Autowired
//	TestCompleteEmailProcess testCompleteEmailProcess;
//	
////	@Autowired
////	NewApplicantEventProcess newApplicantEventProcess;
////	
//	@Autowired
//	OrderAckProcessor orderAckProcessor;
//	
//	@Autowired
//	TestFailEmailProcess testFailEmailProcess;
//	
//	@Autowired
//	TestAckEmailProcess testAckEmailProcess;
//	
//	@Autowired
//	PurchaseOrderEmailProcessor purchaseOrderEmailProcessor;
//
//	@Autowired
//	CommonUtils commonUtils;
//	// FIXME - have to get queues name from application.properties file
////	private static final String notificationQueue = "notification-queue";
//	
//
//	@RabbitListener(queues = notificationQueue)
//	public void receiveNotificationQueue(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
//			throws IOException {
//		Event event = null;
//		try {
////			Object objHeader = message.getMessageProperties().getHeaders().get("Heading");
////			String header = (String) objHeader;
////			String str = new String(message.getBody());
////			event = new ObjectMapper().readValue(str, Event.class);
////			Map<String, String> keyValues = event.getKeyValues();
////			System.out.println(keyValues);
//			String appToken=event.getNotificationKeys().get(0).getAppToken();
//			System.out.println(appToken);
//			commonUtils.setAuthenticationContext(appToken);
//			if (event.getName().equals(Event.EventTypes.NewUserEvent)) {
//				newUserEventProcessor.processNewUser(event);
////			} else if (event.getName().equals(Event.EventTypes.EmployeeSalaryCreatedEvent)) {
////				employeeSalaryCreatedEventProcessor.processPayout(event);
////			} else if (event.getName().equals(Event.EventTypes.NewUserEvent)) {
////				newUserEventProcessor.processNewUser(event);
////			} else if (event.getName().equals(Event.EventTypes.EmployeeUpdateEvent)) {
////				employeeUpdateEventProcessor.processEmployeeUpdate(event);
////			} else if (event.getName().equals(Event.EventTypes.EmployeePasswordResetEvent)) {
////				employeePasswordResetEventProcessor.processEmployeePasswordUpdate(event);
////				System.out.println(event);
////			}else if (event.getName().equals(Event.EventTypes.Manager1UpdateEvent)) {
////				employeeN1ManagerUpdateProcessor.processEmployeeN1Update(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.Hr1UpdateEvent)) {
////				employeeHr1ManagerUpdateEventProcessor.processEmployeeHr1Update(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.Hr2UpdateEvent)) {
////				employeeHr2ManagerUpdateEventProcessor.processEmployeeHr2Update(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.Acc1UpdateEvent)) {
////				employeeAcc1ManagerUpdateEventProcessor.processEmployeeAcc1Update(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.Acc2UpdateEvent)) {
////				employeeAcc2ManagerUpdateEventProcessor.processEmployeeAcc2Update(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.EmployeeLeaveApplyingEvent)) {
////				employeeLeaveApplyingEventProcessor.processEmployeeLeave(event);
////			}else if (event.getName().equals(Event.EventTypes.JobConfirmationEvent)) {
////				jobConfirmationEventProcessor.processJobConfUser(event);
////				
////			} else if (event.getName().equals(Event.EventTypes.OrderAcknowlegmentProcessorEvent)) {
////				orderAckProcessor.process(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.VendorPoEmailEvent)) {
////				purchaseOrderEmailProcessor.process(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.ApplicantAddEvent)) {
////				newApplicantEventProcess.processNewApplicant(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.TestCompleteEvent)) {
////				testCompleteEmailProcess.process(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.PassEvent)) {
////				testAckEmailProcess.ackProcess(event);
////			}
////			else if (event.getName().equals(Event.EventTypes.FailEvent)) {
////				testFailEmailProcess.Process(event);
////			}
//			
//			else {
//				System.out.println("Unrecognized Event" + event);
//
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			if(event!=null) {
////				eventService.addErrorEvent(event);	
//			}
//
//		} finally {
//			channel.basicAck(tag, true);
//		}
////	}
//
//}
//
//
