package com.ss.smartoffice.soservice.kafkaproducer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.shared.model.SmartOfficeException;
import com.ss.smartoffice.shared.model.AuthUser;
import com.ss.smartoffice.shared.model.eventrules.EventRule;
import com.ss.smartoffice.shared.model.eventrules.EventRuleRepository;
import com.ss.smartoffice.soservice.transaction.event.Event;
import com.ss.smartoffice.soservice.transaction.event.EventRepository;
import com.ss.smartoffice.soservice.transaction.event.EventService;
import com.ss.smartoffice.soservice.transaction.incident.IncidentAsyncHelper;

@Service
//@RequestMapping("transaction/event-publish-service")
public class EventPublisherService {
	@Autowired
	EventRuleRepository eventRuleRepository;

	@Autowired
	EventRepository eventRepository;
	@Autowired
	EventService eventService;

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	private static Logger log = LoggerFactory.getLogger(EventPublisherService.class);
	
	@Async("asyncThreadPoolTaskExecutor")
	public CompletableFuture<Event> pushEvent(@RequestBody Event event, AuthUser loggedInUser)
			throws SmartOfficeException {
		commonUtils.setAuthenticationContext(loggedInUser,"async");
		return CompletableFuture.completedFuture(pushEventSynchronously(event));
	}

	public Event pushEvent(@RequestBody Event event) throws SmartOfficeException {
		return pushEventSynchronously(event);
	}

	public Event pushEventSynchronously(@RequestBody Event event) throws SmartOfficeException {
		AuthUser loggedInUser = (AuthUser) commonUtils.getAuthenticatedUser().getDetails();
		event.setContextAuthUserId(loggedInUser.getId());
		
		log.debug("pushEventSynchronously - "+event.getName()+"("+event.getId()+")");
		List<EventRule> eventRuleForNameAndCategory = new ArrayList<EventRule>();
		try {
			boolean searchByNameAndCategory = false;

			if (event.getName() != null && event.getCategory() != null) {
				searchByNameAndCategory = true;
			}
			if (searchByNameAndCategory) {
				String name = event.getName().toString();
				String category = event.getCategory().toString();
				eventRuleForNameAndCategory = eventRuleRepository.findByEventTypeAndEventCategory(name, category);
				System.out.println("37899 - "+eventRuleForNameAndCategory);
			}
			if (eventRuleForNameAndCategory != null && !eventRuleForNameAndCategory.isEmpty()) {
				if (eventRuleForNameAndCategory.get(0).getTopic().equals("business-topic")) {
					event.setEventDesc(eventRuleForNameAndCategory.get(0).getEventDesc());
					eventService.addEventStructure(event);
					System.out.println("37892 - " + event);
					ObjectMapper om = new ObjectMapper();
					String eventString = om.writeValueAsString(event);
					kafkaTemplate.send(eventRuleForNameAndCategory.get(0).getTopic(), eventString);

				} else if (eventRuleForNameAndCategory.get(0).getTopic().equals("notification-topic")) {
					System.out.println(eventRuleForNameAndCategory);
					System.out.println("37893");
					event.setEventDesc(eventRuleForNameAndCategory.get(0).getEventDesc());
					eventService.addEventStructure(event);
					ObjectMapper om = new ObjectMapper();
					String eventString = om.writeValueAsString(event);
					System.out.println("37894 - " + eventString);
					kafkaTemplate.send(eventRuleForNameAndCategory.get(0).getTopic(), eventString);
				}
			} else {
				log.warn(event.getName()+"("+event.getId()+") No Event Rule found for this type Event");
				throw new SmartOfficeException("No Event Rule found for this type Event");
			}
		} catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+")",e);
		}
		return event;
	}

	public Event pushEventDirectly(@RequestBody Event event, String topicName) throws SmartOfficeException {
		try {
			log.debug("pushEventDirectly - "+event.getName()+"("+event.getId()+") ,topic--> "+topicName);
			if (topicName != null && !topicName.isEmpty() && topicName.equals("sms-topic")) {
				ObjectMapper om = new ObjectMapper();
				String eventString = om.writeValueAsString(event);
				kafkaTemplate.send("sms-topic", eventString);
			}
			if (topicName != null && !topicName.isEmpty() && topicName.equals("email-topic")) {
				ObjectMapper om = new ObjectMapper();
				String eventString = om.writeValueAsString(event);
				kafkaTemplate.send("email-topic", eventString);
			}
			if (topicName != null && !topicName.isEmpty() && topicName.equals("inapp-notfn-topic")) {
				ObjectMapper om = new ObjectMapper();
				String eventString = om.writeValueAsString(event);
				kafkaTemplate.send("sms-topic", eventString);
			}
			log.debug("pushEventDirectly - "+event.getName()+"("+event.getId()+") ,topic--> "+topicName + " -- message sent to channel topics");

		} catch (Exception e) {
			log.error(event.getName()+"("+event.getId()+") ,topic--> "+topicName,e);
		}
		return event;
	}

}
