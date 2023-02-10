package com.order.service.controller;

import com.order.service.entity.Order;
import com.order.service.mapper.OrderMapper;
import com.order.service.model.OrderDTO;
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

    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> listOrder() {
        log.info("Have been called the listOrder method on the OrderController class");
        List<Order> order = orderService.getAll();
        if (order.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orderMapper.modelsToDto(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") int id) {
        log.info("Have been called the getOrder method on the OrderController class");
        Order order = orderService.getOrderById(id);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orderMapper.modelToDto(order));
    }

    @PostMapping
    public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTO order) {
        log.info("Have been called the saveOrder method on the OrderController class");
        Order newOrder = orderService.save(orderMapper.dtoToModel(order));
        return ResponseEntity.ok(orderMapper.modelToDto(newOrder));
    }
}
