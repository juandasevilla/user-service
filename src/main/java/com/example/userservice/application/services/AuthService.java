package com.example.userservice.application.services;

import com.example.userservice.application.dto.request.LoginRequest;
import com.example.userservice.application.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
