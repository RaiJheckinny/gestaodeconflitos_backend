package com.pipocaagil.feedback.occurrences.dto;

import com.pipocaagil.feedback.occurrences.Occurrence;
import com.pipocaagil.feedback.users.User;

import java.util.UUID;

public record RecoveryUUIDDto (
        UUID protocolo
){
    public RecoveryUUIDDto(UUID protocolo) {
        this.protocolo = protocolo;
    }
}
