package com.devlon.springsecurityamigos.repository;

import com.devlon.springsecurityamigos.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Student, Long> {
    Student findByUsername(String username);

//    Student findByUser(String email);
}
