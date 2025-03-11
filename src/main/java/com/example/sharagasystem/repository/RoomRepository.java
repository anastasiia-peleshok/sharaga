package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    public Optional<Room> findByNumber(String number);
}
