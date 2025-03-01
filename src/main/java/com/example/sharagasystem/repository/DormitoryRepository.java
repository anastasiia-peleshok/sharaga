package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Dormitory;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DormitoryRepository extends JpaRepository<Dormitory, UUID> {

    @Query("""
        SELECT d
        FROM Dormitory d
        WHERE d.deletedAt IS NULL
        AND d.name like :name
        """)
    Dormitory findByName(String name);
}
