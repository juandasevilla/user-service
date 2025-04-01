package com.example.userservice.infrastructure.exceptionshandler;

import com.example.userservice.domain.exceptions.*;
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

    @ExceptionHandler(UserBirthDateException.class)
    public ResponseEntity<ExceptionResponse> handleUserBirthDateException(UserBirthDateException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.USER_BIRTH_DATE_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(UserEmailException.class)
    public ResponseEntity<ExceptionResponse> handleUserEmailException(UserEmailException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.USER_EMAIL_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(UserPhoneException.class)
    public ResponseEntity<ExceptionResponse> handleUserPhoneException(UserPhoneException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.USER_PHONE_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(UserNullOrSpaceException.class)
    public ResponseEntity<ExceptionResponse> handleUserNullOrSpaceException(UserNullOrSpaceException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.USER_NULL_OR_SPACE_EXCEPTION, LocalDateTime.now()));
    }

    @ExceptionHandler(RoleIsRequiredException.class)
    public ResponseEntity<ExceptionResponse> handleRoleIsRequiredException(RoleIsRequiredException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(ExceptionConstants.ROLE_IS_REQUIRED_EXCEPTION, LocalDateTime.now()));
    }
}
