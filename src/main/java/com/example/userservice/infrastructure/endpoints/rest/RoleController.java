package com.example.userservice.infrastructure.endpoints.rest;

import com.example.userservice.application.dto.request.SaveRoleRequest;
import com.example.userservice.application.dto.response.RoleResponse;
import com.example.userservice.application.dto.response.SaveRoleResponse;
import com.example.userservice.application.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<SaveRoleResponse> saveRole(@RequestBody SaveRoleRequest saveRoleRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.save(saveRoleRequest));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }
}
