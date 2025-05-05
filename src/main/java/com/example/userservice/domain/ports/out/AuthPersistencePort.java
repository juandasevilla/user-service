package com.example.userservice.domain.ports.out;

import com.example.userservice.domain.model.UserModel;

public interface AuthPersistencePort {
    UserModel authenticate(String email, String password);
    String generateToken(String email, String role);
}
