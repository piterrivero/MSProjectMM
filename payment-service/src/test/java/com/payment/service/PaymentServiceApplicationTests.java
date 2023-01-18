package com.payment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.payment.service.entity.Payment;
import com.payment.service.repository.PaymentRepository;
import com.payment.service.service.PaymentService;
import com.payment.service.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
class PaymentServiceApplicationTests {

	// There are created the mocks object that are used inside the service
	@Mock
	private PaymentRepository paymentRepository;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	
	// There is created an instance of the service and injected the mocks declared before
	@InjectMocks
	private PaymentService paymentService;

	private static List<Payment> paymentListMock;
	
	@BeforeAll
	static void init() {
		paymentListMock = new ArrayList<>();
		paymentListMock.add(Payment.builder().id(1).build());
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
