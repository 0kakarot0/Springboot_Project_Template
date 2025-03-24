package com.example.project_template.student.service;

import com.example.project_template.security.JwtService;
import com.example.project_template.student.dto.StudentLoginRequestPayLoad;
import com.example.project_template.student.dto.StudentLoginResponse;
import com.example.project_template.student.model.Student;
import com.example.project_template.student.repository.StudentRepository;
import com.example.project_template.utils.generic_response.ApiResponse;
import com.example.project_template.utils.response_messages.error_messages.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.example.project_template.utils.response_messages.ResponseMessages.*;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;


    public ResponseEntity<ApiResponse<StudentLoginResponse>> studentLoginWithEmailOrUserNameAndPassword(StudentLoginRequestPayLoad loginRequestPayLoad) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestPayLoad.getUserNameOrEmail(),
                        loginRequestPayLoad.getPassword()
                )
        );

        Optional<Student> student = loginRequestPayLoad.getUserNameOrEmail().contains("@")
                ? studentRepository.findUserByStudentEmail(loginRequestPayLoad.getUserNameOrEmail())
                : studentRepository.findStudentByStudentUserName(loginRequestPayLoad.getUserNameOrEmail());
        String jwtToken =  jwtService.generateToken(student.get());

        StudentLoginResponse studentLoginResponse;
        studentLoginResponse = student.map(value -> new StudentLoginResponse(
                jwtToken,
                value.getStudentUserName(),
                value.getStudentFirstName(),
                value.getStudentLastName(),
                value.getStudentEmail(),
                value.getDob(),
                value.getAge()
        )).orElseGet(StudentLoginResponse::new);


        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponse.<StudentLoginResponse>builder().timeStamp(LocalDateTime.now())
                        .message(STUDENT_LOGIN_SUCCESS.getMessage())
                        .data(studentLoginResponse).build()
        );
    }


    public ResponseEntity<ApiResponse<?>> deleteStudent(Long studentId) throws AuthenticationException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            studentRepository.deleteById(studentId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    ApiResponse.builder().timeStamp(LocalDateTime.now()).message(STUDENT_DELETED_SUCCESS.getMessage()).build()
            );
        }else{
            throw new AuthenticationException(ErrorMessage.AUTHENTICATION_FAILED.getMessage());
        }
    }

    public Page<Student> getStudents(int page, int size) {
        return studentRepository.findAll(PageRequest.of(page, size));
    }
}
