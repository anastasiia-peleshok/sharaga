package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResidentRepository extends JpaRepository<ResidentDetails, UUID> {
    Optional<ResidentDetails> findByEmail(String email);
}
