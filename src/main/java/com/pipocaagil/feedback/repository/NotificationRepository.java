package com.pipocaagil.feedback.repository;

import com.pipocaagil.feedback.notification.Notification;
import com.pipocaagil.feedback.occurrences.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
}
