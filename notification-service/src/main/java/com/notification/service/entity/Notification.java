package com.notification.service.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "notification")
@Getter
@Setter
@Builder
public class Notification {

    @Transient
    public static final String SEQUENCE_NAME = "notification_sequence";

    @Id
    private long id;

    private String description;

    private LocalDateTime notificationDate;


}
