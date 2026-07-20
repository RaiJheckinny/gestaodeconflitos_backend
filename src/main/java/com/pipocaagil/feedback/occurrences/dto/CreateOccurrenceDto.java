package com.pipocaagil.feedback.occurrences.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOccurrenceDto(
        LocalDateTime dateEvent,
        String location,
        List<String> involvedEmployee,
        String description,
        List<FileDTO> listFile,
        String email,
        String title
) {
}
