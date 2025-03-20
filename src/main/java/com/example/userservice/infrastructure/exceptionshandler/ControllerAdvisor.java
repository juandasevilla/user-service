package com.example.userservice.infrastructure.exceptionshandler;

import com.example.userservice.domain.exceptions.RoleAlreadyExistsException;
import com.example.userservice.domain.exceptions.RoleNullOrSpaceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(RoleAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleRoleAlreadyExistsException(RoleAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.ROLE_EXISTS_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(RoleNullOrSpaceException.class)
    public ResponseEntity<ExceptionResponse> handleRoleNullOrSpaceException(RoleNullOrSpaceException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.ROLE_NULL_OR_SPACE_EXCEPTION, LocalDateTime.now()));
    }
}
