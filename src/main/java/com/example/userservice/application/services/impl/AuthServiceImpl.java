package com.example.userservice.application.services.impl;

import com.example.userservice.application.dto.request.LoginRequest;
import com.example.userservice.application.dto.response.LoginResponse;
import com.example.userservice.application.services.AuthService;
import com.example.userservice.domain.ports.in.AuthServicePort;
import com.example.userservice.domain.utils.LoginDomainResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthServicePort authServicePort;

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginDomainResponse domainResponse = authServicePort.login(request.email(), request.password());
        return new LoginResponse(domainResponse.getToken(), domainResponse.getRole());
    }


}
