package com.example.sharagasystem.repository;

import com.example.sharagasystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    public Optional<Room> findByNumber(String number);
}
