package com.example.userservice.domain.ports.out;

import com.example.userservice.domain.model.RoleModel;
import java.util.List;

public interface RolePersistencePort {
    void saveRole(RoleModel roleModel);
    RoleModel getRoleByName(String roleName);
    List<RoleModel> getRoles();
}
