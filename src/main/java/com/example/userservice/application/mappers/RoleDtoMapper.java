package com.example.userservice.application.mappers;

import com.example.userservice.application.dto.request.SaveRoleRequest;
import com.example.userservice.application.dto.response.RoleResponse;
import com.example.userservice.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {
    RoleModel requestToModel(SaveRoleRequest saveRoleRequest);
    RoleResponse modelToResponse(RoleModel roleModel);
    List<RoleResponse> modelListToResponseList(List<RoleModel> roles);
}
