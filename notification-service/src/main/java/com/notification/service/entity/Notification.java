package com.notification.service.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
