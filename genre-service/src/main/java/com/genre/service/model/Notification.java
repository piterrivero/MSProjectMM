package com.genre.service.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Notification {
	
	private String description;
	
	private LocalDateTime notificationDate;

}
