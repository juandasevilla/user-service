package com.example.userservice.application.services;

import com.example.userservice.application.dto.request.SaveRoleRequest;
import com.example.userservice.application.dto.response.RoleResponse;
import com.example.userservice.application.dto.response.SaveRoleResponse;

import java.util.List;

public interface RoleService {
    SaveRoleResponse save(SaveRoleRequest request);
    List <RoleResponse> getRoles();
}
