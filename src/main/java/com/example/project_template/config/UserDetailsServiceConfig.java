package com.example.project_template.config;

import com.example.project_template.student.model.Student;
import com.example.project_template.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class UserDetailsServiceConfig {
    private final StudentRepository studentRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return studentUserNameOrEmail -> {
            // Check if the input is an email (contains "@")
            boolean isEmail = studentUserNameOrEmail.contains("@");

            // Query the database based on whether the input is a username or email
            Optional<Student> student = isEmail
                    ? studentRepository.findUserByStudentEmail(studentUserNameOrEmail)
                    : studentRepository.findStudentByStudentUserName(studentUserNameOrEmail);

            // Throw an exception if no user is found
            Student foundStudent = student.orElseThrow(() ->
                    new UsernameNotFoundException("User not found with " + (isEmail ? "email" : "username") + ": " + studentUserNameOrEmail)
            );
            return new User(foundStudent.getUsername(), foundStudent.getPassword(), foundStudent.getAuthorities());
        };
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
