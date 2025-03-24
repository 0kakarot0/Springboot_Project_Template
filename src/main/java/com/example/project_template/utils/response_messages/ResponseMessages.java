package com.example.project_template.utils.response_messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessages {
    STUDENT_LOGIN_SUCCESS("Student login successfully"),

    STUDENT_DELETED_SUCCESS("Student deleted successfully ");


    private final String message;
}
