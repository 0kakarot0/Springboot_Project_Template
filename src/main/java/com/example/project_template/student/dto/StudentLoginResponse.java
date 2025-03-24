package com.example.project_template.student.dto;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentLoginResponse {
    private String token;
    private String studentUserName;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private LocalDate dob;
    private int age;
}
