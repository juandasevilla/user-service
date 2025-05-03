package com.example.userservice.domain.ports.out;

import com.example.userservice.domain.model.UserModel;

public interface UserPersistencePort {
    void saveUser(UserModel userModel);
}
