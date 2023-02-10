package com.payment.service;

import com.payment.service.entity.Payment;
import com.payment.service.kafka.KafkaSender;
import com.payment.service.repository.PaymentRepository;
import com.payment.service.service.PaymentService;
import com.payment.service.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceApplicationTests {

    private static List<Payment> paymentListMock;
    // There are created the mocks object that are used inside the service
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;
    @Mock
    private KafkaSender kafkaSender;
    // There is created an instance of the service and injected the mocks declared before
    @InjectMocks
    private PaymentService paymentService;

    @BeforeAll
    static void init() {
        paymentListMock = new ArrayList<>();
        Payment payment = new Payment();
        payment.setId(1);
        paymentListMock.add(payment);
    }

    @Test
    public void shouldListAllPayments() {
        // GIVEN
        when(paymentRepository.findAll()).thenReturn(paymentListMock);
        // WHEN
        List<Payment> paymentList = paymentService.getAll();
        // THEN
        assertThat(paymentList).isNotEmpty();
        assertThat(paymentList.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetPaymentById() {
        // GIVEN
        Optional<Payment> paymentMock = Optional.of(paymentListMock.get(0));
        when(paymentRepository.findById(1)).thenReturn(paymentMock);
        // WHEN
        Payment payment = paymentService.getPaymentById(1);
        // THEN
        assertThat(payment).isNotNull();
    }

    @Test
    public void shouldProcessPayment() {
        // GIVEN
        // WHEN
        // THEN
    }

}
