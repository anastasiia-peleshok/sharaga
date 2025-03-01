package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Furniture;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepository extends JpaRepository<Furniture, UUID> {


    @Query("""
            SELECT f
            FROM Furniture f
            LEFT JOIN FETCH f.room
            WHERE f.deletedAt IS NULL
            AND f.dormitory = :dormitory
            AND (
                      LOWER(f.itemNumber) LIKE CONCAT('%', LOWER(:textToSearch), '%') OR 
                      LOWER(f.name) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                  )
            """)
    Page<Furniture> findAllByDormitory(@Param("dormitory") Dormitory dormitory,
                                       @Param("textToSearch") String textToSearch,
                                       Pageable pageable);

}
