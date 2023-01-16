package com.order.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.order.service.model.Payment;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaListeners {
	
	// Listening a complex type
	@KafkaListener(topics = "processPaymentTopic", groupId = "group1", containerFactory = "objectKafkaListenerContainerFactory")
	public void listenerPayment(Payment payment) {
		log.info("topic: Received Message of topic: topicObject on listenerPayment. Payment status: "+payment.isStatus());
	}
	
}
