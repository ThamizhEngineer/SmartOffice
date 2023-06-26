package com.ss.smartoffice.sonotificationservice.kafkaConsumer;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener; 
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.sonotificationservice.inappNotificationservice.InAppNotificationService;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

@Service
public class KafkaInAppNotfnTopicConsumer {
	@Autowired
	CommonUtils commonUtils;

	@Autowired
	InAppNotificationService inAppNotificationService;
	

	private static Logger log = LoggerFactory.getLogger(KafkaInAppNotfnTopicConsumer.class);
	
	@KafkaListener(topics = "inapp-notfn-topic", groupId = "inapp-consumer-grp1", containerFactory = "kafkaListenerStringContainerFactory")
	public void consumer(String message, String eventString)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			Event event = new ObjectMapper().readValue(eventString, Event.class);
			inAppNotificationService.create(event);
		}catch (Exception e) {
			log.error("InApp Notifn consumer -- Failed -- "+ eventString, e); 
			
		}
}
}
