package com.example.project_template.student.repository;

import com.example.project_template.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findAll(Pageable pageable);


    Optional<Student> findUserByStudentEmail(String studentEmail);

    @Query("SELECT s FROM Student s WHERE s.studentUserName = :studentUserName")
    Optional<Student> findStudentByStudentUserName(String studentUserName);

}
