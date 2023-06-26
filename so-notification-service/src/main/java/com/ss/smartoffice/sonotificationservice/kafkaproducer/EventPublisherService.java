package com.ss.smartoffice.sonotificationservice.kafkaproducer;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.model.SmartOfficeException;

import com.ss.smartoffice.shared.model.eventrules.EventRule;
import com.ss.smartoffice.shared.model.eventrules.EventRuleRepository;
import com.ss.smartoffice.sonotificationservice.kafkaConsumer.KafkaNotificationEventConsumer;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
import com.ss.smartoffice.sonotificationservice.transaction.event.EventService;


@Service
@RequestMapping("transaction/event-publish-service")
public class EventPublisherService {
//	String topicName="test-topic";
	@Autowired
	EventRuleRepository eventRuleRepository;
	
	
	@Autowired
	EventService eventService;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	private static Logger log = LoggerFactory.getLogger(EventPublisherService.class);
	
	@PostMapping
	public Event pushEvent(@RequestBody Event event)throws SmartOfficeException {
		List<EventRule> eventRuleForNameAndCategory= new ArrayList<EventRule>();
		try {
		boolean searchByNameAndCategory=false;
		
		if(event.getName()!=null&&event.getCategory()!=null) {
			searchByNameAndCategory=true;
		}
		if(searchByNameAndCategory) {
			String name=event.getName().toString();
			String category=event.getCategory().toString();
			eventRuleForNameAndCategory=eventRuleRepository.findByEventTypeAndEventCategory(name, category);
			log.debug("searchByNameAndCategory",eventRuleForNameAndCategory);
		}
		if(eventRuleForNameAndCategory!=null&&!eventRuleForNameAndCategory.isEmpty()) {
		if(eventRuleForNameAndCategory.get(0).getTopic().equals("business-topic")) {
			event.setEventDesc(eventRuleForNameAndCategory.get(0).getEventDesc());
			eventService.addEventStructure(event); 
			ObjectMapper om = new ObjectMapper();
			String eventString = om.writeValueAsString(event);

			
			kafkaTemplate.send(eventRuleForNameAndCategory.get(0).getTopic(), eventString);
		
		}else if (eventRuleForNameAndCategory.get(0).getTopic().equals("notification-topic")) {
			event.setEventDesc(eventRuleForNameAndCategory.get(0).getEventDesc());
			eventService.addEventStructure(event);
			ObjectMapper om = new ObjectMapper();
			String eventString = om.writeValueAsString(event); 
			
			kafkaTemplate.send(eventRuleForNameAndCategory.get(0).getTopic(),eventString);
		}
		}else {
			throw new SmartOfficeException("No Event Rule found for this type Event");
		}
		}catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+")",e);
		}
		return event;		
	}
	public Event pushEventDirectly(@RequestBody Event event,String topicName)throws SmartOfficeException{
		try {
			ObjectMapper om = new ObjectMapper();
			String eventString = om.writeValueAsString(event);
			if(topicName!=null&&!topicName.isEmpty()&&topicName.equals("sms-topic")) { 
				kafkaTemplate.send("sms-topic",eventString);
			}
			if(topicName!=null&&!topicName.isEmpty()&&topicName.equals("email-topic")) { 
				kafkaTemplate.send("email-topic",eventString);
			}
			if(topicName!=null&&!topicName.isEmpty()&&topicName.equals("inapp-notfn-topic")) { 
				kafkaTemplate.send("inapp-notfn-topic",eventString);
			}
			if(topicName!=null&&!topicName.isEmpty()&&topicName.equals("dash-metric-topic")) { 
				kafkaTemplate.send("dash-metric-topic",eventString);
			}
			
			
		}catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+")",e);
		}
		return event;
	}
	
}
