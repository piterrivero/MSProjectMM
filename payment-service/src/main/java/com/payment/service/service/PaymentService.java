package com.payment.service.service;

import com.payment.service.entity.Payment;
import com.payment.service.kafka.KafkaSender;
import com.payment.service.model.OrderDTO;
import com.payment.service.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SequenceGeneratorService sequenceGenerator;
    private final KafkaSender kafkaSender;

    public PaymentService(PaymentRepository paymentRepository, SequenceGeneratorService sequenceGenerator, KafkaSender kafkaSender) {
        this.paymentRepository = paymentRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.kafkaSender = kafkaSender;
    }

    public List<Payment> getAll() {
        log.info("Have been called the getAll method on the PaymentService class");
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(int id) {
        log.info("Have been called the getPaymentById method on the PaymentService class");
        return paymentRepository.findById(id).orElse(null);
    }

    public void processPayment(OrderDTO order) {
        log.info("Have been called the processPayment method on the PaymentService class");
//		TODO verify budget of the client respect al total order
        Payment payment = Payment.builder().totalPayment(order.getTotalOrder()).status(false).build();
        payment.setId(sequenceGenerator.generateSequence(Payment.SEQUENCE_NAME));
        paymentRepository.save(payment);
        kafkaSender.sendMessage("processPaymentTopic", payment);
    }

}
