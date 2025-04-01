package com.example.userservice.domain.ports.in;

import com.example.userservice.domain.model.UserModel;

public interface UserServicePort {
    void saveUser(UserModel userModel);
}
