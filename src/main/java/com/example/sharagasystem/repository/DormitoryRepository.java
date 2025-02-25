package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DormitoryRepository extends JpaRepository<Dormitory, Integer> {
    Optional<Dormitory> findByName(String name);
}
