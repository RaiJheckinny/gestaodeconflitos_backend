package com.pipocaagil.feedback.users.dto;

import com.pipocaagil.feedback.users.User;

public record RecoveryUserEmailNameDTO(
        String email,
        String name

){
    public RecoveryUserEmailNameDTO(User user) {
        this(
                user.getEmail(),
                user.getName()
        );
    }
}
