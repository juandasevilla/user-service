package com.example.userservice.application.services;

import com.example.userservice.application.dto.request.SaveUserRequest;
import com.example.userservice.application.dto.response.SaveUserResponse;

public interface UserService {
    SaveUserResponse save(SaveUserRequest request);
}
