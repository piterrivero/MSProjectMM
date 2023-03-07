package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.DiscRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DiscTests {

    @Autowired
    private DiscRepository discRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(discRepository.findAll()).hasSize(11);
    }

}
