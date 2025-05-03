package com.example.userservice.domain.usecases;

import com.example.userservice.domain.exceptions.RoleIsRequiredException;
import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.in.UserServicePort;
import com.example.userservice.domain.ports.out.RolePersistencePort;
import com.example.userservice.domain.ports.out.UserPersistencePort;

public class UserUseCase implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final RolePersistencePort rolePersistencePort;

    public UserUseCase(UserPersistencePort userPersistencePort, RolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveUser(UserModel userModel) {
        if(!rolePersistencePort.findById(userModel.getRole().getId()).isPresent()) {
            throw new RoleIsRequiredException();
        }
        userPersistencePort.saveUser(userModel);
    }
}
