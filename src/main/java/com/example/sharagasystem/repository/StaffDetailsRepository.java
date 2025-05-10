package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.StaffDetails;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffDetailsRepository extends JpaRepository<StaffDetails, UUID> {

    @Query("""
            SELECT sd
            FROM StaffDetails sd
            JOIN sd.dormitory d
            WHERE sd.deletedAt IS NULL
            AND d.id = :dormitoryId
            AND (
                      LOWER(sd.firstName) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                      OR LOWER(sd.lastName) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                      OR LOWER(sd.email) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                      OR LOWER(sd.phoneNumber) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                  )
            """)
    Page<StaffDetails> findAllByDormitory(@Param("dormitoryId") UUID dormitoryId,
                                          @Param("textToSearch") String textToSearch,
                                          Pageable pageable);
}
