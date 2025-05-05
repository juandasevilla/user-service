package com.example.userservice.domain.ports.in;

import com.example.userservice.domain.utils.LoginDomainResponse;

public interface AuthServicePort {
     LoginDomainResponse login(String email, String password);
}
