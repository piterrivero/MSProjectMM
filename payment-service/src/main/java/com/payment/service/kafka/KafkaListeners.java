package com.payment.service.kafka;

import com.payment.service.model.OrderDTO;
import com.payment.service.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListeners {

    private final PaymentService paymentService;

    public KafkaListeners(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "processOrderTopic", groupId = "group1", containerFactory = "orderDTOListenerContainerFactory")
    public void listenerPayment(OrderDTO order) {
        log.info("Have been called the listenerPayment method on the KafkaListeners class");
        log.info("Received Message of topic: processOrderTopic on listenerPayment. Payment status: " + order.isStatus());
        paymentService.processPayment(order);
    }

}
