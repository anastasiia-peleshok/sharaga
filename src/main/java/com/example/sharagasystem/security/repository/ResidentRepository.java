package com.example.sharagasystem.security.repository;

import com.example.sharagasystem.model.ResidentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ResidentRepository extends JpaRepository<ResidentDetails, UUID> {
    Optional<ResidentDetails> findByEmail(String email);
}
