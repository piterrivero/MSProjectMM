package com.payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.payment.service.model.Order;
import com.payment.service.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaListeners {
	
	@Autowired 
	private PaymentService paymentService;
	
	// Listening a complex type
	@KafkaListener(topics = "processOrderTopic", groupId = "group1", containerFactory = "objectKafkaListenerContainerFactory")
	public void listenerPayment(Order order) {
		log.info("topicAlbum: Received Message of topic: topicObject on listenerPayment. Payment status: "+order.isStatus());
		paymentService.processPayment(order);
	}
	
}
