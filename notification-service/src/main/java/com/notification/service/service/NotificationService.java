package com.notification.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notification.service.entity.Notification;
import com.notification.service.repository.NotificationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private SequenceGeneratorService sequenceGenerator;

	public List<Notification> getAll() {
		log.info("Have been called the getAll method on the NotificationService class");
		return notificationRepository.findAll();
	}

	public Notification getOrderById(int id) {
		log.info("Have been called the getOrderById method on the NotificationService class");
		return notificationRepository.findById(id).orElse(null);
	}

	public Notification save(Notification notification) {
		log.info("Have been called the save method on the NotificationService class");
		notification.setId(sequenceGenerator.generateSequence(Notification.SEQUENCE_NAME));
		return notificationRepository.save(notification);
	}

}
