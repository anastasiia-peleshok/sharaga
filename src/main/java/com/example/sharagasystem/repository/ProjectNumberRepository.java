package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.ProjectNumber;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectNumberRepository extends JpaRepository<ProjectNumber, String> {
    Optional<ProjectNumber> findProjectNumberByType(String type);
}
