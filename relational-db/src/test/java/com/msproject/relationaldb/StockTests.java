package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StockTests {

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(stockRepository.findAll()).hasSize(11);
    }
}
