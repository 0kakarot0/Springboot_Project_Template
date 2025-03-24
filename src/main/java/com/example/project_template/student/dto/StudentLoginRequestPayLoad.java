package com.example.project_template.student.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentLoginRequestPayLoad {
    private String userNameOrEmail;
    private String password;
}
