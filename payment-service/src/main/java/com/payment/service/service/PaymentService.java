package com.payment.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.service.entity.Payment;
import com.payment.service.kafka.KafkaSender;
import com.payment.service.model.OrderDTO;
import com.payment.service.repository.PaymentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	@Autowired
	private KafkaSender kafkaSender;
	
	public List<Payment> getAll(){
		log.info("Have been called the getAll method on the PaymentService class");
		return paymentRepository.findAll();
	}
	
	public Payment getPaymentById(int id) {
		log.info("Have been called the getPaymentById method on the PaymentService class");
		return paymentRepository.findById(id).orElse(null);
	}
	
	public void processPayment(OrderDTO order) {
		log.info("Have been called the processPayment method on the PaymentService class");
//		TODO verify budget of the client
		Payment payment = Payment.builder().totalPayment(order.getTotalOrder()).status(false).build();
		payment.setId(sequenceGenerator.generateSequence(Payment.SEQUENCE_NAME));
		paymentRepository.save(payment);
		kafkaSender.sendMessageObject("processPaymentTopic", payment);
	}
	
}
