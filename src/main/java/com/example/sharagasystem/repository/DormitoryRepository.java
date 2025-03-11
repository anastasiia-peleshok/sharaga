package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DormitoryRepository extends JpaRepository<Dormitory, UUID> {
    Optional<Dormitory> findByName(String name);
}
