package com.example.project_template.config;

import com.example.project_template.student.model.Student;
import com.example.project_template.student.repository.StudentRepository;
import com.example.project_template.utils.role.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // List of students to be added to the database
            List<Student> students = List.of(
                    Student.builder()
                            .studentUserName("john_doe")
                            .studentFirstName("John")
                            .studentLastName("Doe")
                            .studentEmail("john.doe@example.com")
                            .studentPassword(passwordEncoder.encode("password123"))
                            .dob(LocalDate.of(1990, 5, 15))
                            .role(Role.STUDENT)
                            .build(),
                    Student.builder()
                            .studentUserName("jane_smith")
                            .studentFirstName("Jane")
                            .studentLastName("Smith")
                            .studentEmail("jane.smith@example.com")
                            .studentPassword(passwordEncoder.encode("securepass"))
                            .dob(LocalDate.of(1985, 8, 25))
                            .role(Role.STUDENT)
                            .build(),
                    Student.builder()
                            .studentUserName("alice_jones")
                            .studentFirstName("Alice")
                            .studentLastName("Jones")
                            .studentEmail("alice.jones@example.com")
                            .studentPassword(passwordEncoder.encode("alice123"))
                            .dob(LocalDate.of(1995, 3, 10))
                            .role(Role.STUDENT)
                            .build(),
                    Student.builder()
                            .studentUserName("bob_brown")
                            .studentFirstName("Bob")
                            .studentLastName("Brown")
                            .studentEmail("bob.brown@example.com")
                            .studentPassword(passwordEncoder.encode("bobpass"))
                            .dob(LocalDate.of(2000, 11, 20))
                            .role(Role.STUDENT)
                            .build()
            );

            // Save all students to the database
            studentRepository.saveAll(students);

            System.out.println("Database seeded with " + students.size() + " students.");
        };
    }
}
