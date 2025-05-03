package com.example.userservice.infrastructure.exceptionshandler;

public class ExceptionConstants {
    private ExceptionConstants() {
    }

    public static final String ROLE_EXISTS_EXCEPTION = "The role already exists";
    public static final String ROLE_NULL_OR_SPACE_EXCEPTION = "The name and description of the role can not be null or empty";
    public static final String USER_BIRTH_DATE_EXCEPTION = "The user must be at least 18 years old";
    public static final String USER_EMAIL_EXCEPTION = "The email is not valid";
    public static final String USER_PHONE_EXCEPTION = "The phone number is not valid";
    public static final String USER_NULL_OR_SPACE_EXCEPTION = "The name, email, phone number and birth date of the user can not be null or empty";
    public static final String ROLE_IS_REQUIRED_EXCEPTION = "The role is required";
}
