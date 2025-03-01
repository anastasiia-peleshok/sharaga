package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.ResidentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<ResidentDetails, UUID> {
    Optional<ResidentDetails> findByEmail(String email);

    @Query("""
            SELECT rd
            FROM ResidentDetails rd
            LEFT JOIN rd.room r
            WHERE rd.deletedAt IS NULL
             AND (
                      LOWER(rd.lastName) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                      OR LOWER(rd.firstName) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                      OR LOWER(r.number) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                  )
            AND rd.dormitory = :dormitory
            """)
    Page<ResidentDetails> findAllByDormitory(@Param("dormitory")Dormitory dormitory,
                                             @Param("textToSearch") String textToSearch,
                                             Pageable pageable);
}
