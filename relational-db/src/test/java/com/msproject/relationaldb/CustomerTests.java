package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(customerRepository.findAll()).hasSize(4);
    }
}
