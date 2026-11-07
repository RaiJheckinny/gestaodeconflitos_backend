package com.pipocaagil.feedback.notification;

import com.pipocaagil.feedback.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    @Column(nullable = false)
    private UUID occurenceProtocol;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String descrition;

    @Column(nullable = false)
    private boolean sent;

    @Column(nullable = false)
    private boolean sentStartup;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
