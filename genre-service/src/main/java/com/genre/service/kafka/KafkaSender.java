package com.genre.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaSender {

	@Autowired
	@Qualifier("objectKafkaTemplate")
	private KafkaTemplate<String, Object> objectKafkaTemplate;
	
	public void sendMessageObject(String topicName, Object object) {
		log.info("Sent a message to the topic: "+topicName);
		objectKafkaTemplate.send(topicName, object);
	}
	
}
