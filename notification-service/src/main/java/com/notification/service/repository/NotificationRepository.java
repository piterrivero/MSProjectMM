package com.notification.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.notification.service.entity.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, Integer> {

}
