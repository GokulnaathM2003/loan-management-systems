package com.lms.auth_service.service;

import com.lms.auth_service.dto.LoginRequest;
import com.lms.auth_service.dto.LoginResponse;
import com.lms.auth_service.dto.RegisterRequest;
import com.lms.auth_service.entity.Customer;
import com.lms.auth_service.entity.UserMaster;
import com.lms.auth_service.exception.CustomerNotFoundException;
import com.lms.auth_service.exception.UserAlreadyExistsException;
import com.lms.auth_service.repository.CustomerRepository;
import com.lms.auth_service.repository.UserRepository;
import com.lms.auth_service.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

        private final UserRepository userRepository;
        private final CustomerRepository customerRepository;
        private final PasswordEncoder passwordEncoder;
        private final AuthenticationManager authenticationManager;
        private final JwtUtil jwtUtil;

        public AuthServiceImpl(UserRepository userRepository, CustomerRepository customerRepository,
                        PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
                this.userRepository = userRepository;
                this.customerRepository = customerRepository;
                this.passwordEncoder = passwordEncoder;
                this.authenticationManager = authenticationManager;
                this.jwtUtil = jwtUtil;
        }

        // ================= REGISTER =================
        @Override
        public void register(RegisterRequest request) {

                Customer customer = customerRepository.findById(request.getCustomerId())
                                .orElseThrow(() -> new CustomerNotFoundException("Customer ID not found"));

                if (userRepository.existsByUsername(request.getUsername())) {
                        throw new UserAlreadyExistsException("Username already exists");
                }

                if (userRepository.existsByEmail(request.getEmail())) {
                        throw new UserAlreadyExistsException("Email already exists");
                }

                UserMaster user = new UserMaster();
                user.setUsername(request.getUsername());
                user.setEmail(request.getEmail());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setRole("USER");
                user.setCustomer(customer);

                userRepository.save(user);
        }

        // ================= LOGIN =================
        @Override
        public LoginResponse login(LoginRequest request) {

                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getUsernameOrEmail(),
                                                request.getPassword()));

                UserMaster user = userRepository
                                .findByUsernameOrEmail(
                                                request.getUsernameOrEmail(),
                                                request.getUsernameOrEmail())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                // ✅ CORRECT JWT GENERATION
                String token = jwtUtil.generateToken(
                                user.getUsername(),
                                user.getRole(),
                                user.getCustomer().getCustomerId());

                return new LoginResponse(
                                token,
                                user.getUsername(),
                                user.getRole());
        }
}
