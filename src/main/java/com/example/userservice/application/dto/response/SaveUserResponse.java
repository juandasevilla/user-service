package com.example.userservice.application.dto.response;

import java.time.LocalDateTime;

public record SaveUserResponse(String message, LocalDateTime time) {
}
