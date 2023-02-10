package com.notification.service.mapper;

import com.notification.service.entity.Notification;
import com.notification.service.model.NotificationDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDTO modelToDto(Notification notification);

    Notification dtoToModel(NotificationDTO notificationDTO);

    List<NotificationDTO> modelsToDto(List<Notification> notifications);

    List<Notification> dtoToModel(List<NotificationDTO> notificationDTOS);

}
