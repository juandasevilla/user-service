package com.example.userservice.infrastructure.endpoints.rest;

import com.example.userservice.application.dto.request.SaveUserRequest;
import com.example.userservice.application.dto.response.SaveUserResponse;
import com.example.userservice.application.services.UserService;
import com.example.userservice.domain.ports.in.UserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SaveUserResponse> saveUser(@RequestBody SaveUserRequest saveUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(saveUserRequest));
    }
}
