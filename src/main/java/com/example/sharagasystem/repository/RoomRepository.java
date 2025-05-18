package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Gender;
import com.example.sharagasystem.model.Room;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    public Optional<Room> findByNumber(String number);


    @Query("""
            SELECT r
            FROM Room r
            WHERE r.deletedAt IS NULL
            AND r.dormitory = :dormitory
            AND (
                      LOWER(r.number) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                  )
            ORDER BY r.createdAt DESC
            """)
    Page<Room> findAllByDormitory(@Param("dormitory") Dormitory dormitory,
                                  @Param("textToSearch") String textToSearch,
                                  Pageable pageable);


    @Query("""
            SELECT r
            FROM Room r
            WHERE r.deletedAt IS NULL
            AND r.dormitory = :dormitory
            AND r.number LIKE CONCAT('%', :number, '%')
            """)
    Room findByNumberAndDormitory(String number, Dormitory dormitory);

    @Query("""
            SELECT r
            FROM Room r
            WHERE r.deletedAt IS NULL
            AND r.dormitory = :dormitory
            AND r.gender = :gender
            AND (
                      LOWER(r.number) LIKE CONCAT('%', LOWER(:textToSearch), '%')
                  )
            AND r.free > 0
            """)
    Page<Room> findAllWithFreePlaces(@Param("dormitory") Dormitory dormitory,
                                     @Param("gender") Gender gender,
                                     @Param("textToSearch") String textToSearch,
                                     Pageable pageable);
}
