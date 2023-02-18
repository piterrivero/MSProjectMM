package com.payment.service.service;

import com.payment.service.entity.Payment;
import com.payment.service.feignclients.CustomerFeignClient;
import com.payment.service.kafka.KafkaSender;
import com.payment.service.model.CustomerDTO;
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

    private CustomerFeignClient customerFeignClient;

    public PaymentService(PaymentRepository paymentRepository,
                          SequenceGeneratorService sequenceGenerator,
                          KafkaSender kafkaSender,
                          CustomerFeignClient customerFeignClient) {
        this.paymentRepository = paymentRepository;
        this.sequenceGenerator = sequenceGenerator;
        this.kafkaSender = kafkaSender;
        this.customerFeignClient = customerFeignClient;
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

        CustomerDTO customer = customerFeignClient.getCustomer(order.getCustomerId());
        Payment payment = new Payment();
        payment.setIdOrder(order.getId());
        if (customer.getBudget() < order.getTotalOrder()){
            payment.setStatus(false);
            payment.setStatusMessage("The customer have not enough budget to proceed with the bought");
        } else {
            payment.setStatus(true);
            payment.setTotalPayment(order.getTotalOrder());
            log.info("The budget of the customer "+customer.getId()+" was modified");
            customer.setBudget(customer.getBudget() - order.getTotalOrder());
            customerFeignClient.updateCustomer(customer.getId(), customer);
        }
        payment.setId(sequenceGenerator.generateSequence(Payment.SEQUENCE_NAME));
        paymentRepository.save(payment);
        kafkaSender.sendMessage("processPaymentTopic", payment);
    }

}
