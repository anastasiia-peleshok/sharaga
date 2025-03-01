package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.dto.RoomRequestDto;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.repository.RoomRepository;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final DormitoryService dormitoryService;
    private final RoomRepository roomRepository;

    @Override
    public Room create(RoomRequestDto roomRequestDto) {
        Room newRoom = new Room();
        newRoom.setNumber(roomRequestDto.getRoomNumber());
        newRoom.setCapacity(roomRequestDto.getCapacity());
        newRoom.setGender(roomRequestDto.getGender());
        newRoom.setDormitory(dormitoryService.getByName(roomRequestDto.getNameDormitory()));
        return roomRepository.save(newRoom);
    }
}
