package com.order.service.controller;

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

import com.order.service.entity.Order;
import com.order.service.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public ResponseEntity<List<Order>> listOrder(){
		log.info("Have been called the listOrder method");
		List<Order> order = orderService.getAll();
		if (order.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(order);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") int id){
		log.info("Have been called the getOrder method");
		Order order = orderService.getOrderById(id);
		if (order == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(order);
	}
	
	@PostMapping
	public ResponseEntity<Order> saveOrder(@RequestBody Order order){
		log.info("Have been called the saveOrder method");
		Order newOrder = orderService.save(order);
		return ResponseEntity.ok(newOrder);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") int id, @RequestBody Order order){
		log.info("Have been called the updateOrder method");
		if (order == null) {
			return ResponseEntity.notFound().build();
		}
		Order newOrder = orderService.update(id, order);
		return ResponseEntity.ok(newOrder);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("id") int id){
		log.info("Have been called the deleteOrder method");
		Order order = orderService.getOrderById(id);
		if (order == null) {
			return ResponseEntity.notFound().build();
		}
		orderService.delete(id);
		return new ResponseEntity<>(org.springframework.http.HttpStatus.OK);
	}
}
