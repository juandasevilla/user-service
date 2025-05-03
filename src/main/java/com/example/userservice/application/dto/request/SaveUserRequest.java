package com.example.userservice.application.dto.request;

import java.time.LocalDate;

public record SaveUserRequest(String name, String lastName, Integer identification, String phone, LocalDate birthDate, String email, String password, Long roleId) {

    public Long getRoleId() {
        return roleId;
    }
}
