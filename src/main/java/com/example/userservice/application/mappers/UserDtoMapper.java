package com.example.userservice.application.mappers;

import com.example.userservice.application.dto.request.SaveUserRequest;
import com.example.userservice.application.dto.response.UserResponse;
import com.example.userservice.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    UserModel requestToModel(SaveUserRequest saveUserRequest);
    UserResponse modelToResponse(UserModel userModel);
}
