package com.notification.service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.notification.service.entity.Notification;
import com.notification.service.model.NotificationDTO;
import com.notification.service.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaListeners {

	@Autowired
	NotificationService notificationService;

	// Listening a complex type
	@KafkaListener(topics = "processNotificationTopic", groupId = "group1", containerFactory = "objectKafkaListenerContainerFactory")
	public void listenerNotification(NotificationDTO notificationDTO) {
		log.info("Have been called the listenerNotification method on the class KafkaListeners");
		log.info("topic: Received Message of topic: processNotificationTopic.");
		Notification notification = Notification.builder().description(notificationDTO.getDescription()).notificationDate(notificationDTO.getNotificationDate()).build(); 
		notificationService.save(notification);
	}

}
