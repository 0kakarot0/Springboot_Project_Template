package com.example.project_template.exception;


import com.example.project_template.utils.response_messages.error_messages.ErrorMessage;
import com.example.project_template.utils.generic_response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(NoUserFoundWithEmailProvided.class)
    public ResponseEntity<ApiResponse<?>> handleNoUserFoundWithEmailProvided(
            NoUserFoundWithEmailProvided exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ApiResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .message(ErrorMessage.RESOURCE_NOT_FOUND.getMessage() + ": " + exception.getMessage()).build()
                );
    }


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<?>> handleAuthenticationException(
            AuthenticationException exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .message(ErrorMessage.AUTHENTICATION_FAILED.getMessage()).build());

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentialsException(
            BadCredentialsException exception
    ) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(
                        ApiResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .message(ErrorMessage.INVALID_CREDENTIALS.getMessage()).build());

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(
            AccessDeniedException exception
    ) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(
                        ApiResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .message(ErrorMessage.AUTHORIZATION_FAILED.getMessage()).build());

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(
            Exception exception
    ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ApiResponse.builder()
                                .timeStamp(LocalDateTime.now())
                                .message(ErrorMessage.GENERIC_ERROR_MESSAGE.getMessage() + exception.getMessage()).build());

    }

}
