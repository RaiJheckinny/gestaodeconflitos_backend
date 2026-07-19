package com.pipocaagil.feedback.occurrences.dto;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.users.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RecoveryOccurrenceDto(
        LocalDateTime dateEvent,
        String location,
        List<String> involvedEmployee,
        String description,
        List<String> urlFile,
        LocalDateTime datenow,
        UUID protocol,
        String status,
        String title
) {
    public RecoveryOccurrenceDto(Occurrence occurrence) {
        this(
                occurrence.getDateEvent(),
                occurrence.getLocation(),
                occurrence.getInvolvedEmployee(),
                occurrence.getDescription(),
                occurrence.getUrlFile(),
                occurrence.getDateNow(),
                occurrence.getProtocol(),
                occurrence.getStatus(),
                occurrence.getTitle()

        );
    }
}
