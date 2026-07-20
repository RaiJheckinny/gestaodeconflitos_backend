package com.pipocaagil.feedback.occurrences.dto;

import com.pipocaagil.feedback.occurrences.Occurrence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record RecoveryOccurrenceDto(
        LocalDateTime dateEvent,
        String location,
        List<String> involvedEmployee,
        String description,
        List<FileDTO> listFile,
        LocalDateTime datenow,
        Long protocol,
        String status,
        String title
) {
    public RecoveryOccurrenceDto(Occurrence occurrence) {
        this(
                occurrence.getDateEvent(),
                occurrence.getLocation(),
                occurrence.getInvolvedEmployee(),
                occurrence.getDescription(),
                occurrence.getListFile().stream()
                        .map(file -> new FileDTO(
                                file.getUrlFile(),
                                file.getUrlName()
                        ))
                        .toList(),
                occurrence.getDateNow(),
                occurrence.getProtocol(),
                occurrence.getStatus(),
                occurrence.getTitle()
        );
    }
}
