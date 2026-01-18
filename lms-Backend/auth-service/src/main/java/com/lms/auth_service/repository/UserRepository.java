package com.lms.auth_service.repository;

import com.lms.auth_service.entity.UserMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserMaster, Long> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<UserMaster> findByUsernameOrEmail(String username, String email);
}
