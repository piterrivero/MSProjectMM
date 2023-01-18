package com.payment.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payment.service.entity.Payment;
import com.payment.service.service.PaymentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping
	public ResponseEntity<List<Payment>> listPayment(){
		log.info("Have been called the listPayment method on the PaymentController class");
		List<Payment> payment = paymentService.getAll();
		if (payment.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(payment);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable("id") int id){
		log.info("Have been called the getPayment method on the PaymentController class");
		Payment payment = paymentService.getPaymentById(id);
		if (payment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(payment);
	}
	
}