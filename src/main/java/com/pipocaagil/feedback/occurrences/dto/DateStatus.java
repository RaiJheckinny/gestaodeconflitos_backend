package com.pipocaagil.feedback.occurrences.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="date_Status")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DateStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DateStatusName name;

    private LocalDateTime date;
}
