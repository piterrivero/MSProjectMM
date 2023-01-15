package com.payment.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.payment.service.entity.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Integer> {

}
