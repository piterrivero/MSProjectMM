package com.order.service.service;

import com.order.service.entity.Order;
import com.order.service.feignclients.DiscFeignClient;
import com.order.service.kafka.KafkaSender;
import com.order.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private DiscFeignClient discFeignClient;

    public List<Order> getAll() {
        log.info("Have been called the getAll method on the OrderService class");
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        log.info("Have been called the getOrderById method on the OrderService class");
        return orderRepository.findById(id).orElse(null);
    }

    public Order save(Order order) {
        log.info("Have been called the save method on the OrderService class");
        order.setId(sequenceGenerator.generateSequence(Order.SEQUENCE_NAME));

        // TODO check the availability
        // If there are availability update the disc to rest the quantity

        order.getDetails().forEach
                (x -> x.setTotalDetail(discFeignClient.findById(x.getDiscId()).getPrice() * x.getQuantity()));
        order.setTotalOrder(order.getDetails().stream().mapToLong(x -> x.getTotalDetail()).sum());
        Order newOrder = orderRepository.save(order);
        processOrder(newOrder);
        return newOrder;
    }

    public void processOrder(Order order) {
        log.info("Have been called the processOrder method on the OrderService class");
        kafkaSender.sendMessage("processOrderTopic", order);
    }

}
