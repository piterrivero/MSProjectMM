package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.PaymentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentTests {

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(paymentRepository.findAll()).hasSize(5);
    }
}
