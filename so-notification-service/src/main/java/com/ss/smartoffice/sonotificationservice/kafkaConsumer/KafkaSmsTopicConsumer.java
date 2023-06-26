package com.ss.smartoffice.sonotificationservice.kafkaConsumer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.smartoffice.sonotificationservice.common.SmsHelper;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;

public class KafkaSmsTopicConsumer {
	@Autowired
	SmsHelper smsHelper;
	
	@KafkaListener(topics = "sms-topic", groupId = "sms-consumer-grp1", containerFactory = "kafkaListenerStringContainerFactory")
	public void consumer(String message, String eventString)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			System.out.println("hi");
			Event event = new ObjectMapper().readValue(eventString, Event.class);
			String smsSessage =event.getNotificationKeys().get(0).getSmsMessage();
			System.out.println(smsSessage);
			//			smsHelper.sendSms(event.getBusinessKeys().get(0)., smsSessage);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			
			
		}
	}
}
