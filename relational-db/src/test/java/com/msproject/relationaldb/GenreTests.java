package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.GenreRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GenreTests {

    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(genreRepository.findAll()).hasSize(4);
    }

}
