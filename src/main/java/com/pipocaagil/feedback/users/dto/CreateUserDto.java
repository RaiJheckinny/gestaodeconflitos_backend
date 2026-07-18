package com.pipocaagil.feedback.users.dto;

import com.pipocaagil.feedback.security.RoleName;

public record CreateUserDto(

        String email,
        String password,
        RoleName role,
        String name,
        String department,
        String position

) {
}