package com.customer.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.customer.service.entity.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {

}
