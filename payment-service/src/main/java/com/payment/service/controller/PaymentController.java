package com.payment.service.controller;

import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		log.info("Have been called the listPayment method");
		List<Payment> payment = paymentService.getAll();
		if (payment.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(payment);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable("id") int id){
		log.info("Have been called the getPayment method");
		Payment payment = paymentService.getPaymentById(id);
		if (payment == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(payment);
	}
	
	@PostMapping
	public ResponseEntity<Payment> savePayment(@RequestBody Payment payment){
		log.info("Have been called the savePayment method");
		Payment newPayment = paymentService.save(payment);
		return ResponseEntity.ok(newPayment);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable("id") int id, @RequestBody Payment payment){
		log.info("Have been called the updatePayment method");
		if (payment == null) {
			return ResponseEntity.notFound().build();
		}
		Payment newPayment = paymentService.update(id, payment);
		return ResponseEntity.ok(newPayment);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePayment(@PathVariable("id") int id){
		log.info("Have been called the deletePayment method");
		Payment payment = paymentService.getPaymentById(id);
		if (payment == null) {
			return ResponseEntity.notFound().build();
		}
		paymentService.delete(id);
		return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
	}
	
}
