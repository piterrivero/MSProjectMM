package com.customer.service.controller;

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

import com.customer.service.entity.Customer;
import com.customer.service.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>> listCustomer(){
		log.info("Have been called the listCustomer method");
		List<Customer> customer = customerService.getAll();
		if (customer.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(customer);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("id") int id){
		log.info("Have been called the getCustomer method");
		Customer customer = customerService.getCustomerById(id);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping
	public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
		log.info("Have been called the saveCustomer method");
		Customer newCustomer = customerService.save(customer);
		return ResponseEntity.ok(newCustomer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") int id, @RequestBody Customer customer){
		log.info("Have been called the updateCustomer method");
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		Customer newCustomer = customerService.update(id, customer);
		return ResponseEntity.ok(newCustomer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") int id){
		log.info("Have been called the deleteCustomer method");
		Customer customer = customerService.getCustomerById(id);
		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		customerService.delete(id);
		return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
	}
	
}
