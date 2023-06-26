package com.ss.smartoffice.soservice.kafkaConsumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.ss.smartoffice.soservice.transaction.event.Event;


@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${kafka.broker.url}")
	private String KAFKA_BROKER_URL;
	
	@Bean
	public ConsumerFactory<String, String> consumerStringFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "so-service-consumer");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory(config);
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerStringContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerStringFactory());
		return factory;
	}

	@Bean
	public ConsumerFactory<String, Event> consumerFactory(){
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BROKER_URL);
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "so-service-consumer");
		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.ss.smartoffice.soservice.transaction.event.Event");
		
		return new DefaultKafkaConsumerFactory(config, new StringDeserializer(), new JsonDeserializer<>(Event.class));
		
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Event> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
