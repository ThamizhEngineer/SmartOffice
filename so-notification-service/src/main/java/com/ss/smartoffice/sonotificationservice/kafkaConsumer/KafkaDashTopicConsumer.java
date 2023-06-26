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
import com.ss.smartoffice.sonotificationservice.dashboardalert.DashboardAlertService;
import com.ss.smartoffice.sonotificationservice.transaction.event.Event;
@Service
public class KafkaDashTopicConsumer {
	
	@Autowired
	DashboardAlertService dashService;
	
	@Autowired
	CommonUtils commonUtils;
	
	private static Logger log = LoggerFactory.getLogger(KafkaDashTopicConsumer.class);

	@KafkaListener(topics = "dash-metric-topic", groupId = "dash-consumer-grp1", containerFactory = "kafkaListenerStringContainerFactory")
	public void consumer(String message, String eventString)
			throws JsonParseException, JsonMappingException, IOException {
		try {
			System.out.println("hi dash metric");
			Event event = new ObjectMapper().readValue(eventString, Event.class);
			commonUtils.setAuthenticationContext(event.getContextAuthUserId(),"async");
			String employeeId = commonUtils.getLoggedinEmployeeId();
			dashService.formingDashboard(employeeId);

		}catch (Exception e) {
			log.error("Dash Metric Notifn consumer -- Failed --> "+eventString, e); 
			
			
			
		}
	}
}
