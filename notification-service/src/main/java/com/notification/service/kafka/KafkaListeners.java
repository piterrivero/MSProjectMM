package com.notification.service.kafka;

import com.notification.service.entity.Notification;
import com.notification.service.mapper.NotificationMapper;
import com.notification.service.model.NotificationDTO;
import com.notification.service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaListeners {

    private final NotificationService notificationService;

    private final NotificationMapper notificationMapper;

    public KafkaListeners(NotificationService notificationService, NotificationMapper notificationMapper) {
        this.notificationService = notificationService;
        this.notificationMapper = notificationMapper;
    }

    @KafkaListener(topics = "processNotificationTopic", groupId = "group1", containerFactory = "notificationDTOListenerContainerFactory")
    public void listenerNotification(NotificationDTO notificationDTO) {
        log.info("Have been called the listenerNotification method on the class KafkaListeners");
        log.info("topic: Received Message of topic: processNotificationTopic.");
        notificationService.save(notificationMapper.dtoToModel(notificationDTO));
    }

}
