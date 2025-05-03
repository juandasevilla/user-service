package com.example.userservice.infrastructure.mappers;

import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.infrastructure.entities.RoleEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {
    RoleEntity modelToEntity(RoleModel roleModel);
    RoleModel entityToModel(RoleEntity roleEntity);
    List<RoleModel> entityListToModelList(List<RoleEntity> roles);
}
