package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.ProjectNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ProjectNumberRepository extends JpaRepository<ProjectNumber, String> {
    Optional<ProjectNumber> findProjectNumberByType(String type);
}
