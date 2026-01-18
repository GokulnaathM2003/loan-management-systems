package com.lms.auth_service.service;

import com.lms.auth_service.dto.LoginRequest;
import com.lms.auth_service.dto.LoginResponse;
import com.lms.auth_service.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
