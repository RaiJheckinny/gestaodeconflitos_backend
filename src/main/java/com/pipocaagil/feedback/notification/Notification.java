package com.pipocaagil.feedback.notification;

import com.pipocaagil.feedback.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "notification")
@Entity(name = "Notification")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true,nullable = false)
    private String message;

    @Column(nullable = false)
    private boolean read;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
