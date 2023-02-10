package com.order.service;

import com.order.service.entity.Detail;
import com.order.service.entity.Order;
import com.order.service.feignclients.DiscFeignClient;
import com.order.service.kafka.KafkaSender;
import com.order.service.model.DiscDTO;
import com.order.service.repository.OrderRepository;
import com.order.service.service.OrderService;
import com.order.service.service.SequenceGeneratorService;
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
class OrderServiceApplicationTests {

    private static List<Order> genreListMock;
    // There are created the mocks object that are used inside the service
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private SequenceGeneratorService sequenceGeneratorService;
    @Mock
    private KafkaSender kafkaSender;
    @Mock
    private DiscFeignClient discFeignClient;
    // There is created an instance of the service and injected the mocks declared before
    @InjectMocks
    private OrderService orderService;

    @BeforeAll
    static void init() {
        genreListMock = new ArrayList<>();
        List<Detail> detailList = new ArrayList<>();

        Detail detail = new Detail();
        detail.setDiscId(1);
        detail.setQuantity(2);
        detailList.add(detail);

        Order order = new Order();
        order.setId(1);
        order.setDetails(detailList);
        order.setStatus(false);
        order.setTotalOrder(0);

        genreListMock.add(order);
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

        Detail detail = new Detail();
        detail.setDiscId(1);
        detail.setQuantity(2);
        detailList.add(detail);

        Order orderMock = new Order();
        orderMock.setId(2);
        orderMock.setDetails(detailList);
        orderMock.setStatus(false);
        orderMock.setTotalOrder(0);

        DiscDTO discDTO = new DiscDTO();
        discDTO.setId(1);
        discDTO.setPrice(10);
        discDTO.setStock(5);

        when(orderRepository.save(orderMock)).thenReturn(orderMock);
        when(sequenceGeneratorService.generateSequence(Order.SEQUENCE_NAME)).thenReturn(2L);
        when(discFeignClient.findById(1)).thenReturn(discDTO);

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
