package com.schoolmanagement.service;

import com.schoolmanagement.entity.User;
import com.schoolmanagement.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public boolean validateUser (String username, String password) {
        User user = userRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password); // In a real application, use password hashing
    }
}