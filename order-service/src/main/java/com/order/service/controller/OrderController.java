package com.order.service.controller;

import com.order.service.entity.Order;
import com.order.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> listOrder() {
        log.info("Have been called the listOrder method on the OrderController class");
        List<Order> order = orderService.getAll();
        if (order.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") int id) {
        log.info("Have been called the getOrder method on the OrderController class");
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        log.info("Have been called the saveOrder method on the OrderController class");
        Order newOrder = orderService.save(order);
        return ResponseEntity.ok(newOrder);
    }
}
