package com.order.service.service;

import com.order.service.entity.Order;
import com.order.service.feignclients.DiscFeignClient;
import com.order.service.kafka.KafkaSender;
import com.order.service.model.DiscDTO;
import com.order.service.model.PaymentDTO;
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
        order.setStatus(true);

        log.info("Checking the availability of the whole order on stock");
        order.getDetails().forEach
                (x -> {
                    DiscDTO disc = discFeignClient.findById(x.getDiscId());
                    if (disc.getStock() < x.getQuantity()) {
                        log.info("The quantity " + x.getQuantity() + " of the disc " + disc.getTitle() + " was not available on stock");
                        order.setStatus(false);
                        order.setStatusMessage("One or more articles were not available on stock");
                    }
                });

        if (order.isStatus()) {
            Order orderProcessed = updateProductsStock(order, true);
            orderProcessed.setTotalOrder(order.getDetails().stream().mapToLong(x -> x.getTotalDetail()).sum());
            return processOrder(orderProcessed);
        }
        return processOrder(order);
    }

    public Order processOrder(Order order) {
        log.info("Have been called the processOrder method on the OrderService class");
        Order newOrder = orderRepository.save(order);
        if (order.isStatus()) {
            kafkaSender.sendMessage("processOrderTopic", order);
        }
        return newOrder;
    }

    public void updateOrderStatus(PaymentDTO payment) {
        Order order = getOrderById(Math.toIntExact(payment.getIdOrder()));
        if (payment.isStatus()) {
            order.setStatus(true);
            order.setStatusMessage("Order processed successfully");
        } else {
            order.setStatus(false);
            order.setStatusMessage(payment.getStatusMessage());
            log.info("The payment to the order " + order.getId() + " was refused. The products will return to stock");
            rollBackStock(Math.toIntExact(order.getId()));
        }
        orderRepository.save(order);
    }

    public void rollBackStock(int orderId) {
        updateProductsStock(getOrderById(orderId), false);
    }

    private Order updateProductsStock(Order order, boolean removeFromStock) {
        order.getDetails().forEach
                (x -> {
                    DiscDTO disc = discFeignClient.findById(x.getDiscId());
                    if (removeFromStock) {
                        // remove of stock
                        disc.setStock(disc.getStock() - x.getQuantity());
                        x.setTotalDetail(disc.getPrice() * x.getQuantity());
                    } else {
                        // reintegrate to stock
                        disc.setStock(disc.getStock() + x.getQuantity());
                    }
                    discFeignClient.updateDisc(disc.getId(), disc);
                });
        return order;
    }

}
