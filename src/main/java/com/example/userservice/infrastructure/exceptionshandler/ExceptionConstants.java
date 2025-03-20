package com.example.userservice.infrastructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants() {
    }

    public static final String ROLE_EXISTS_EXCEPTION = "The role already exists";
    public static final String ROLE_NULL_OR_SPACE_EXCEPTION = "The name and description of the role can not be null or empty";
}
