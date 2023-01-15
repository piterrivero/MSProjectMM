package com.order.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.service.entity.Order;
import com.order.service.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGenerator;
	
	public List<Order> getAll(){
		return orderRepository.findAll();
	}
	
	public Order getOrderById(int id) {
		return orderRepository.findById(id).orElse(null);
	}
	
	public Order save(Order order) {
		order.setId(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));
		Order newOrder = orderRepository.save(order);
		return newOrder;
	}
	
	public Order update(int id, Order order) {
		Order toUpdate =  getOrderById(id);
		toUpdate.setDetails(order.getDetails());
		toUpdate.setStatus(order.isStatus());
		toUpdate.setTotalOrder(order.getTotalOrder());
		Order updated = orderRepository.save(toUpdate);
		return updated;
	}
	
	public void delete(int id) {
		orderRepository.deleteById(id);
	}
	
}
