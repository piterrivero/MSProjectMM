package com.order.service.kafka;

import com.order.service.entity.Order;
import com.order.service.model.PaymentDTO;
import com.order.service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListeners {

    private OrderService orderService;

    public KafkaListeners(OrderService orderService) {
        this.orderService = orderService;
    }

    @KafkaListener(topics = "processPaymentTopic", groupId = "group1", containerFactory = "paymentDTOListenerContainerFactory")
    public void listenerPayment(PaymentDTO payment) {
        log.info("Have been called the listenerPayment method on the KafkaListeners class");
        log.info("topic: Received Message of topic: processPaymentTopic on listenerPayment. Payment status: " + payment.isStatus());
        orderService.updateOrderStatus(payment);
    }
}
