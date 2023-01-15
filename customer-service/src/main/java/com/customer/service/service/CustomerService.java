package com.customer.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.service.entity.Customer;
import com.customer.service.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Customer> getAll(){
		return customerRepository.findAll();
	}
	
	public Customer getCustomerById(int id) {
		return customerRepository.findById(id).orElse(null);
	}
	
	public Customer save(Customer customer) {
		customer.setId(sequenceGenerator.generateSequence(Customer.SEQUENCE_NAME));
		Customer newCustomer = customerRepository.save(customer);
		return newCustomer;
	}
	
	public Customer update(int id, Customer customer) {
		Customer toUpdate =  getCustomerById(id);
		toUpdate.setName(customer.getName());
		toUpdate.setSurname(customer.getSurname());
		toUpdate.setBudget(customer.getBudget());
		Customer updated = customerRepository.save(toUpdate);
		return updated;
	}
	
	public void delete(int id) {
		customerRepository.deleteById(id);
	}
	
}
