package com.example.userservice.domain.ports.in;

import com.example.userservice.domain.model.RoleModel;
import java.util.List;

public interface RoleServicePort {
    void saveRole(RoleModel roleModel);
    List<RoleModel> getRoles();
}
