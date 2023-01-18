package com.customer.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.service.entity.Customer;
import com.customer.service.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Customer> getAll(){
		log.info("Have been called the getAll method on the CustomerService class");
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(int id) {
		log.info("Have been called the getCustomerById method on the CustomerService class");
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.isPresent() ? customer.get() : null;
	}
	
	public Customer save(Customer customer) {
		log.info("Have been called the save method on the CustomerService class");
		customer.setId(sequenceGenerator.generateSequence(Customer.SEQUENCE_NAME));
		return customerRepository.save(customer);
	}
	
	public Customer update(int id, Customer customer) {
		log.info("Have been called the update method on the CustomerService class");
		Customer toUpdate =  getCustomerById(id);
		toUpdate.setName(customer.getName());
		toUpdate.setSurname(customer.getSurname());
		toUpdate.setBudget(customer.getBudget());
		return customerRepository.save(toUpdate);
	}
	
	public void delete(int id) {
		log.info("Have been called the delete method on the CustomerService class");
		customerRepository.deleteById(id);
	}
	
}
