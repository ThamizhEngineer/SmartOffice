package com.ss.smartoffice.soauthservice.kafkaproducer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.model.SmartOfficeException;

import com.ss.smartoffice.shared.model.eventrules.EventRule;
import com.ss.smartoffice.shared.model.eventrules.EventRuleRepository;
import com.ss.smartoffice.soauthservice.transaction.event.Event;
import com.ss.smartoffice.soauthservice.transaction.event.EventService;

@Service
//@RequestMapping("transaction/event-publish-service")
public class EventPublisherService {
//	String topicName="test-topic";
	@Autowired
	EventRuleRepository eventRuleRepository;
	
	
	@Autowired
	EventService eventService;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
//	@PostMapping
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
			System.out.println(eventRuleForNameAndCategory);
		}
		if(eventRuleForNameAndCategory!=null&&!eventRuleForNameAndCategory.isEmpty()) {
		if(eventRuleForNameAndCategory.get(0).getTopic().equals("business-topic")) {
			event.setEventDesc(eventRuleForNameAndCategory.get(0).getEventDesc());
			eventService.addEventStructure(event);
			System.out.println(event);
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
			e.printStackTrace();
		}
		return event;		
	}
	
	
}
