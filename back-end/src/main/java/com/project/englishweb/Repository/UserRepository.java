package com.project.englishweb.Repository;

import com.project.englishweb.Entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username); //  login
    Optional<User> findByEmail(String email);   
}

