package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(orderRepository.findAll()).hasSize(5);
    }

}
