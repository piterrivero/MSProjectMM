package com.notification.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.service.entity.Notification;
import com.notification.service.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;

	@GetMapping
	public ResponseEntity<List<Notification>> listNotification() {
		log.info("Have been called the listNotification method on the class NotificationController");
		List<Notification> notification = notificationService.getAll();
		if (notification.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(notification);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Notification> getNotification(@PathVariable("id") int id) {
		log.info("Have been called the getNotification method on the class NotificationController");
		Notification notification = notificationService.getOrderById(id);
		if (notification == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(notification);
	}

}
