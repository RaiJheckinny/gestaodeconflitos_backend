package com.pipocaagil.feedback.users.dto;

import com.pipocaagil.feedback.security.Role;
import com.pipocaagil.feedback.users.User;

import java.time.LocalDateTime;
import java.util.List;

public record RecoveryUserDto(
        String email,
        String name,
        String department,
        String position,
        LocalDateTime last_accessed,
        String url_photo,
        List<Role> roles
) {
    public RecoveryUserDto(User user) {
        this(
                user.getEmail(),
                user.getName(),
                user.getDepartment(),
                user.getPosition(),
                user.getLast_accessed(),
                user.getUrl_photo(),
                user.getRoles()
        );
    }
}
