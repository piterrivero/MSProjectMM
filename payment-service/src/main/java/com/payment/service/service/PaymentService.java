package com.payment.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payment.service.entity.Payment;
import com.payment.service.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Payment> getAll(){
		return paymentRepository.findAll();
	}
	
	public Payment getPaymentById(int id) {
		return paymentRepository.findById(id).orElse(null);
	}
	
	public Payment save(Payment payment) {
		payment.setId(sequenceGenerator.generateSequence(Payment.SEQUENCE_NAME));
		Payment newPayment = paymentRepository.save(payment);
		return newPayment;
	}
	
	public Payment update(int id, Payment payment) {
		Payment toUpdate =  getPaymentById(id);
		Payment updated = paymentRepository.save(toUpdate);
		return updated;
	}
	
	public void delete(int id) {
		paymentRepository.deleteById(id);
	}
	
}
