package com.pipocaagil.feedback.occurrences.dto;

import com.pipocaagil.feedback.users.User;

import java.time.LocalDateTime;
import java.util.List;

public record CreateOccurrenceDto (
        LocalDateTime dateEvent,
        String location,
        List<String> involvedEmployee,
        String description,
        List<String> urlFile,
        String email
){
}
