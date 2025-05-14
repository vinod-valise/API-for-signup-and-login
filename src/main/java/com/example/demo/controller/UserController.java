package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.SignupRequest;
import com.example.demo.service.AuthService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        String message = authService.login(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody SignupRequest request) {
        String message = authService.signup(request);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return ResponseEntity.ok(response);
    }
}
