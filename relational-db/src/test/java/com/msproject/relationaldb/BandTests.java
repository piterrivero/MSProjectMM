package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.BandRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BandTests {

    @Autowired
    private BandRepository bandRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(bandRepository.findAll()).hasSize(6);
    }

}
