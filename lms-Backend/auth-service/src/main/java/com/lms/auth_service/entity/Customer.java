package com.lms.auth_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    private String name;
    private String email;
    private String mobile;
    private String address;

    // getters & setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
