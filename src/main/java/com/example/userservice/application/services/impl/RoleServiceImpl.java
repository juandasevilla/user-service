package com.example.userservice.application.services.impl;

import com.example.userservice.application.dto.request.SaveRoleRequest;
import com.example.userservice.application.dto.response.RoleResponse;
import com.example.userservice.application.dto.response.SaveRoleResponse;
import com.example.userservice.application.mappers.RoleDtoMapper;
import com.example.userservice.application.services.RoleService;
import com.example.userservice.domain.ports.in.RoleServicePort;
import com.example.userservice.domain.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleServicePort roleServicePort;
    private final RoleDtoMapper roleDtoMapper;

    @Override
    public SaveRoleResponse save(SaveRoleRequest request) {
        roleServicePort.saveRole(roleDtoMapper.requestToModel(request));
        return new SaveRoleResponse(Constants.SAVE_ROLE_RESPONSE_MESSAGE, LocalDateTime.now());
    }

    @Override
    public List<RoleResponse> getRoles() {
        return roleDtoMapper.modelListToResponseList(roleServicePort.getRoles());
    }
}
