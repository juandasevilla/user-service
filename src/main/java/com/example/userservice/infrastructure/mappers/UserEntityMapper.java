package com.example.userservice.infrastructure.mappers;

import java.util.List;

import com.example.userservice.domain.model.UserModel;
import com.example.userservice.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleEntityMapper.class})
public interface UserEntityMapper {
    UserEntity modelToEntity(UserModel userModel);
    UserModel entityToModel(UserEntity userEntity);
    List<UserModel> entityListToModelList(List<UserEntity> users);
}
