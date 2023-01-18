package com.order.service;

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

import com.order.service.entity.Detail;
import com.order.service.entity.Order;
import com.order.service.repository.OrderRepository;
import com.order.service.service.OrderService;
import com.order.service.service.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
class OrderServiceApplicationTests {

	// There are created the mocks object that are used inside the service
	@Mock
	private OrderRepository orderRepository;
	@Mock
	private SequenceGeneratorService sequenceGeneratorService;
	
	// There is created an instance of the service and injected the mocks declared before
	@InjectMocks
	private OrderService orderService;

	private static List<Order> genreListMock;
	
	@BeforeAll
	static void init() {
		genreListMock = new ArrayList<>();
		List<Detail> detailList = new ArrayList<>();
		detailList.add(Detail.builder().id(1).orderId(1).discId(1).quantity(2).build());
		genreListMock.add(Order.builder().id(1).details(detailList).status(false).totalOrder(0).build());
	}
	
	@Test
	public void shouldListAllOrders() {
		// GIVEN
		when(orderRepository.findAll()).thenReturn(genreListMock);
		// WHEN
		List<Order> orderList = orderService.getAll();
		// THEN
		assertThat(orderList).isNotEmpty();
		assertThat(orderList.size()).isEqualTo(1);
	}
	
	@Test
	public void shouldGetOrderById() {
		// GIVEN
		Optional<Order> orderMock = Optional.of(genreListMock.get(0));
		when(orderRepository.findById(1)).thenReturn(orderMock);
		// WHEN
		Order order = orderService.getOrderById(1);
		// THEN
		assertThat(order).isNotNull();
	}
	
	@Test
	public void shouldSaveOrder() {
		// GIVEN
		List<Detail> detailList = new ArrayList<>();
		detailList.add(Detail.builder().id(1).orderId(1).discId(1).quantity(2).build());
		Order orderMock = Order.builder().id(2).details(detailList).status(false).totalOrder(0).build();
		
		when(orderRepository.save(orderMock)).thenReturn(orderMock);
		when(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME)).thenReturn(2L);
		// WHEN
		Order genre = orderService.save(orderMock);
		// THEN
		assertThat(genre).isNotNull();
		assertThat(genre.getId()).isEqualTo(2);
	}
	
	@Test
	public void shouldUpdateOrder() {
		
	}
	
	@Test
	public void shouldDeleteOrder() {
		
	}
}