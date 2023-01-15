package com.order.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.order.service.entity.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, Integer> {

}
