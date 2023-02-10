package com.payment.service.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class NotificationDTO {

    private String description;

    private LocalDateTime notificationDate;

}
