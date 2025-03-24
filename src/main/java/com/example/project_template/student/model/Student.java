package com.example.project_template.student.model;

import com.example.project_template.utils.role.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
@SQLDelete(sql = "UPDATE student set is_student_deleted = true WHERE id=?")
@SQLRestriction("is_student_deleted  = false")
public class Student implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;


    private String studentUserName;
    private String studentFirstName;
    private String studentLastName;
    private String studentEmail;
    private String studentPassword;

    private LocalDate dob;

    @Transient
    private int age;

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    private Role role;

    @Column(name = "is_student_deleted")
    @Builder.Default
    private boolean studentDeleted = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return studentPassword;
    }

    @Override
    public String getUsername() {
        return studentEmail;
    }
}
