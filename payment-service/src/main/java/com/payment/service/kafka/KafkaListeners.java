package com.payment.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.payment.service.model.OrderDTO;
import com.payment.service.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaListeners {
	
	@Autowired 
	private PaymentService paymentService;
	
	// Listening a complex type
	@KafkaListener(topics = "processOrderTopic", groupId = "group1", containerFactory = "objectKafkaListenerContainerFactory")
	public void listenerPayment(OrderDTO order) {
		log.info("Have been called the listenerPayment method on the KafkaListeners class");
		log.info("Received Message of topic: processOrderTopic on listenerPayment. Payment status: "+order.isStatus());
		paymentService.processPayment(order);
	}
	
}
