package com.order.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.KafkaSender;
import com.order.service.entity.Order;
import com.order.service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	@Autowired
	private KafkaSender kafkaSender;
	
	public List<Order> getAll(){
		return orderRepository.findAll();
	}
	
	public Order getOrderById(int id) {
		return orderRepository.findById(id).orElse(null);
	}
	
	public Order save(Order order) {
		order.setId(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));
		Order newOrder = orderRepository.save(order);
		processOrder(newOrder);
		return newOrder;
	}
	
	public void processOrder(Order order) {
		kafkaSender.sendMessageObject("processOrderTopic", order);
	}
	
}
