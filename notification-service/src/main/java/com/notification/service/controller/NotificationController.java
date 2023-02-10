package com.notification.service.controller;

import com.notification.service.entity.Notification;
import com.notification.service.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

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
