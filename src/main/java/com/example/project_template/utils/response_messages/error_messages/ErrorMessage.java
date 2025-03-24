package com.example.project_template.utils.response_messages.error_messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    INVALID_CREDENTIALS("Invalid email or password"),
    AUTHENTICATION_FAILED("Authentication failed"),
    AUTHORIZATION_FAILED("You do not have permission to access this resource."),
    STUDENT_NOT_FOUND("Student not found"),
    RESOURCE_NOT_FOUND("Requested resource not found"),
    GENERIC_ERROR_MESSAGE("An unexpected error occurred: "),
    VALIDATION_ERROR("Invalid request parameters");


    private final String message;
}
