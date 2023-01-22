package com.genre.service.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationDTO {
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("notificationDate")
	private LocalDateTime notificationDate;

}
