package com.cts.CityCare.CityCare.repository;

import com.cts.CityCare.CityCare.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {

    // Used in login to find user by email
    Optional<User> findByEmail(String email);

    // Used in registration to reject duplicate emails
    boolean existsByEmail(String email);

    // Used by admin to list all doctors or all nurses
    List<User> findByRole(User.Role role);

    // Used by admin to list active staff only
    List<User> findByRoleAndStatus(User.Role role, User.Status status);
}
