package com.example.userservice.application.dto.response;

import java.time.LocalDate;

public record UserResponse(String name, String lastName, Integer identification, String phone, LocalDate birthDate, String email, String password, RoleResponse role) {
}
