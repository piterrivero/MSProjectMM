package com.notification.service.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private long id;

    private String description;

    private LocalDateTime notificationDate;

}
