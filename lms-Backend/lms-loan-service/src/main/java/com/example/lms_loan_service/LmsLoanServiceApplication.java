package com.example.lms_loan_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.lms_loan_service.client")
public class LmsLoanServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LmsLoanServiceApplication.class, args);
    }
}
