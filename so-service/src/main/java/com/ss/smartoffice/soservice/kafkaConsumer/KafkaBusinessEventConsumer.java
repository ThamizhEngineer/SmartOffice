package com.ss.smartoffice.soservice.kafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.soservice.event.attendance.CheckInEventProcessor;
import com.ss.smartoffice.soservice.event.attendance.CheckOutEventProcessor;
import com.ss.smartoffice.soservice.event.employee.EmployeeCreatedEventProcessor;
import com.ss.smartoffice.soservice.event.employee.NewUserEventProcessor;
import com.ss.smartoffice.soservice.event.uploadpayslip.ManualPaySlipProcessEventProcessor;
import com.ss.smartoffice.soservice.kafkaproducer.EventPublisherService;
import com.ss.smartoffice.soservice.transaction.event.Event;
@Service
public class KafkaBusinessEventConsumer {
	@Autowired
	NewUserEventProcessor newUserEventProcessor;
	
	@Autowired
	EmployeeCreatedEventProcessor employeeCreatedEventProcessor;
	
	@Autowired
	CheckInEventProcessor checkInEventProcessor;
	
	@Autowired
	CheckOutEventProcessor checkOutEventProcessor;
	@Autowired
	CommonUtils commonUtils;
	@Autowired
	ManualPaySlipProcessEventProcessor manualPaySlipProcessEventProessor;

	private static Logger log = LoggerFactory.getLogger(KafkaBusinessEventConsumer.class);
	
	@KafkaListener(topics="business-topic", groupId ="so-service-consumer-grp1",containerFactory="kafkaListenerStringContainerFactory")
	public void consumer(String message,String eventString) throws Exception {
		Event event =new ObjectMapper().readValue(eventString, Event.class);
		
		String apptoken  =	commonUtils.setAuthenticationContext(event.getContextAuthUserId(),"async");
		event.setAppToken(apptoken);
		
		log.debug("Consuming business-topic -- "+event.getName()+"("+event.getId()+")", message);
		
		try {
			if (event.getName().equals(Event.EventTypes.NewUserEvent)) {
				newUserEventProcessor.process(event);	
			}else if (event.getName().equals(Event.EventTypes.EmployeeCreatedEvent)) {
				employeeCreatedEventProcessor.process(event);
			}else if (event.getName().equals(Event.EventTypes.EmployeeUpdatedByHrEvent)) {
				employeeCreatedEventProcessor.process(event);
			}else if (event.getName().equals(Event.EventTypes.CheckInEvent)) {
				checkInEventProcessor.process(event);
			}else if (event.getName().equals(Event.EventTypes.CheckOutEvent)) {
				checkOutEventProcessor.process(event);
			}
		} catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+")",e);
		}

	}
}
