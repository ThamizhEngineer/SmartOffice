package com.ss.smartoffice.sonotificationservice.kafkaConsumer;

import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import org.springframework.stereotype.Service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.shared.common.CommonUtils;
import com.ss.smartoffice.sonotificationservice.common.EmailHelper;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

@Service
public class KafkaEmailTopicConsumer {

	@Autowired
	CommonUtils commonUtils;

	@Autowired
	EmailHelper emailHelper;

	private static Logger log = LoggerFactory.getLogger(KafkaEmailTopicConsumer.class);

	@KafkaListener(topics = "email-topic", groupId = "email-consumer-grp1", containerFactory = "kafkaListenerStringContainerFactory")
	public void consumer(String message, String eventString)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			Event event = new ObjectMapper().readValue(eventString, Event.class);

			log.debug("Email consumer -"+ event.getName()+"("+event.getId()+")."); 
			emailHelper.sendEventByEmail(event);
		} catch (Exception e) {
			log.error("Email consumer -- Email Send Failed -"+ eventString, e); 
		}
	}
	

	
}
