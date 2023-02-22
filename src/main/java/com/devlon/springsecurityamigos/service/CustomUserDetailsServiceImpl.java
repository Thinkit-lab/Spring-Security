package com.devlon.springsecurityamigos.service;
import com.devlon.springsecurityamigos.repository.UserRepository;
import com.devlon.springsecurityamigos.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        Student user = userRepository.findByUsername(name);
//        user.setEnabled(true);
        if(user == null) {
            try {
                throw new RuntimeException("No user found");
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }

        return new org.springframework.security.core.userdetails.User (
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }
}
