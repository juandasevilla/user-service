package com.example.userservice.application.services.impl;

import com.example.userservice.application.dto.request.SaveUserRequest;
import com.example.userservice.application.dto.response.SaveUserResponse;
import com.example.userservice.application.mappers.UserDtoMapper;
import com.example.userservice.application.services.UserService;
import com.example.userservice.domain.exceptions.RoleIsRequiredException;
import com.example.userservice.domain.model.RoleModel;
import com.example.userservice.domain.model.UserModel;
import com.example.userservice.domain.ports.in.UserServicePort;
import com.example.userservice.domain.ports.out.RolePersistencePort;
import com.example.userservice.domain.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;
    private final RolePersistencePort rolePersistencePort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SaveUserResponse save(SaveUserRequest request) {
        UserModel userModel = userDtoMapper.requestToModel(request);
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        RoleModel roleModel = rolePersistencePort.findById(request.getRoleId())
                .orElseThrow(() -> new RoleIsRequiredException());
        userModel.setRole(roleModel);
        userServicePort.saveUser(userModel);
        return new SaveUserResponse(Constants.SAVE_USER_RESPONSE_MESSAGE, LocalDateTime.now());
    }

}
