package com.order.service.service;

import com.order.service.entity.Order;
import com.order.service.feignclients.DiscFeignClient;
import com.order.service.kafka.KafkaSender;
import com.order.service.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final SequenceGeneratorService sequenceGenerator;

    private final KafkaSender kafkaSender;

    private final DiscFeignClient discFeignClient;

    public OrderService(OrderRepository orderRepository, SequenceGeneratorService sequenceGenerator, KafkaSender kafkaSender, DiscFeignClient discFeignClient) {
        this.orderRepository = orderRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.kafkaSender = kafkaSender;
        this.discFeignClient = discFeignClient;
    }

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
