package com.lms.auth_service.controller;

import com.lms.auth_service.dto.LoginRequest;
import com.lms.auth_service.dto.LoginResponse;
import com.lms.auth_service.dto.RegisterRequest;
import com.lms.auth_service.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

}
