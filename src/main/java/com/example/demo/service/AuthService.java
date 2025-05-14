package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.SignupRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String signup(SignupRequest request) {
        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            return "Email already registered";
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // Use BCrypt in real apps

        userRepository.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(request.getPassword())) {
                return "Login successful";
            } else {
                return "Invalid password";
            }
        } else {
            return "User not found";
        }
    }
}
