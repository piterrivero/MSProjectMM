package com.payment.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.service.KafkaSender;
import com.payment.service.entity.Payment;
import com.payment.service.model.Order;
import com.payment.service.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	@Autowired
	private KafkaSender kafkaSender;
	
	public List<Payment> getAll(){
		return paymentRepository.findAll();
	}
	
	public Payment getPaymentById(int id) {
		return paymentRepository.findById(id).orElse(null);
	}
	
	public void processPayment(Order order) {
//		TODO verify budget of the client
		Payment payment = Payment.builder().totalPayment(order.getTotalOrder()).status(false).build();
		payment.setId(sequenceGenerator.generateSequence(Payment.SEQUENCE_NAME));
		paymentRepository.save(payment);
		kafkaSender.sendMessageObject("processPaymentTopic", payment);
	}
	
}
