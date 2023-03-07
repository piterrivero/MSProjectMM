package com.msproject.relationaldb;

import com.msproject.relationaldb.repository.NotificationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NotificationTests {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    public void shouldFindAllTest() {
        Assertions.assertThat(notificationRepository.findAll()).hasSize(6);
    }

}
