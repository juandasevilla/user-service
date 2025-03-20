package com.example.userservice.domain.usecases;

import com.example.userservice.domain.exceptions.RoleAlreadyExistsException;
import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.domain.ports.in.RoleServicePort;
import com.example.userservice.domain.ports.out.RolePersistencePort;

import java.util.List;

public class RoleUseCase implements RoleServicePort {
    private final RolePersistencePort rolePersistencePort;

    public RoleUseCase(RolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public void saveRole(RoleModel roleModel) {
        RoleModel role = rolePersistencePort.getRoleByName(roleModel.getName());
        if (role != null) {
            throw new RoleAlreadyExistsException();
        }
        rolePersistencePort.saveRole(roleModel);
    }

    @Override
    public List<RoleModel> getRoles() {
        return rolePersistencePort.getRoles();
    }
}
