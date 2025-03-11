package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.repository.RoomRepository;
import com.example.sharagasystem.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FurnitureMapperHelper {
    private final ResidentRepository userRepository;
    private final RoomRepository roomRepository;
    private final DormitoryRepository dormitoryRepository;


    public ResidentDetails getUserById(String userId) {
        if (userId == null) return null;
        return userRepository.findById(UUID.fromString(userId)).orElseThrow(()-> new IllegalArgumentException("user for furniture is not found"));
    }

    public Room getRoomById(String roomId) {
        if (roomId == null) return null;
        return roomRepository.findById(UUID.fromString(roomId)).orElse(null);
    }

    public Dormitory getDormitoryById(String dormitoryId) {
        if (dormitoryId == null) return null;
        return dormitoryRepository.findById(UUID.fromString(dormitoryId)).orElse(null);
    }
}