package com.ss.smartoffice.soauthservice.kafkaproducer;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.ss.smartoffice.soauthservice.transaction.event.Event;




@Configuration
public class KafkaConfigr {

	@Value("${kafka.broker.url}")
	private String KAFKA_BROKER_URL;
	
	@Bean
	public ProducerFactory<String, Event> ProducerFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory(config);
		
	}
	
	@Bean
	public KafkaTemplate<String, Event> kafkaTemplate() {
		
		return new KafkaTemplate<String, Event>(ProducerFactory());
	}

	@Bean
	public ProducerFactory<String, String> ProducerStringFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaProducerFactory(config);
		
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaStringTemplate() {
		
		return new KafkaTemplate<String, String>(ProducerStringFactory());
	}
}
