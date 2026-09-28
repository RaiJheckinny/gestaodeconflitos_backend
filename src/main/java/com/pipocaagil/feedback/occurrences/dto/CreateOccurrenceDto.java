package com.pipocaagil.feedback.occurrences.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateOccurrenceDto(
        UUID protocol,
        LocalDateTime dateEvent,
        String location,
        List<String> involvedEmployee,
        String description,
        List<FileDTO> listFile,
        String email,
        String title
) {
}
