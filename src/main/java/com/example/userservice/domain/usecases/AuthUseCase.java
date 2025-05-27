package com.example.userservice.domain.usecases;

import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.in.AuthServicePort;
import com.example.userservice.domain.ports.out.AuthPersistencePort;
import com.example.userservice.domain.utils.LoginDomainResponse;

public class AuthUseCase implements AuthServicePort {
    private final AuthPersistencePort authPersistencePort;

    public AuthUseCase(AuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public LoginDomainResponse login(String email, String password) {
        UserModel user = authPersistencePort.authenticate(email, password);
        String token = authPersistencePort.generateToken(user.getEmail(), user.getRole().getName(), user.getId());
        return new LoginDomainResponse(token, user.getRole().getName());
    }
}
