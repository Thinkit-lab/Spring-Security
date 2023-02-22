package com.devlon.springsecurityamigos.service;

import com.devlon.springsecurityamigos.repository.UserRepository;
import com.devlon.springsecurityamigos.student.Student;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Student createUser(Student student) {
        return userRepository.save(student);
    }
}
