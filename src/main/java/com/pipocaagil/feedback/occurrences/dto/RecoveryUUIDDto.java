package com.pipocaagil.feedback.occurrences.dto;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.users.User;

import java.util.UUID;

public record RecoveryUUIDDto (
        UUID protocol
){
    public RecoveryUUIDDto(UUID protocol) {
        this.protocol = protocol;
    }
}
