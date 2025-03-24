package com.example.project_template.student.controller;

import com.example.project_template.student.dto.StudentLoginRequestPayLoad;
import com.example.project_template.student.dto.StudentLoginResponse;
import com.example.project_template.student.model.Student;
import com.example.project_template.student.service.StudentService;
import com.example.project_template.utils.generic_response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<StudentLoginResponse>> loginStudentWithUserNameOrEmail(
            @RequestBody StudentLoginRequestPayLoad studentLoginRequestPayLoad
    ) {
        return studentService.studentLoginWithEmailOrUserNameAndPassword(studentLoginRequestPayLoad);
    }


    @DeleteMapping(path = "deleteStudent/{studentId}")
    public ResponseEntity<ApiResponse<?>> deleteStudentById(
            @PathVariable("studentId") Long studentId
    ) throws AuthenticationException {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/paginated")
    public Page<Student> getStudents(@RequestParam int page, @RequestParam int size) {
        return studentService.getStudents(page, size);
    }


}
