package com.lms.auth_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_master")
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // ✅ THIS FIELD WAS MISSING
    @Column(nullable = false)
    private String role;

    // ✅ USER → has customer | ADMIN → null
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    // =====================
    // GETTERS & SETTERS
    // =====================

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // ✅ REQUIRED BY AuthService
    public String getRole() {
        return role;
    }

    // ✅ REQUIRED BY AuthService
    public void setRole(String role) {
        this.role = role;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
