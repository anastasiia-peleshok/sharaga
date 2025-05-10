package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.RoomMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.request.RoomRequestDto;
import com.example.sharagasystem.model.dto.response.RoomListResponseDto;
import com.example.sharagasystem.repository.RoomRepository;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.RoomService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final DormitoryService dormitoryService;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public Room create(RoomRequestDto roomRequestDto) {
        Room newRoom = new Room();
        newRoom.setNumber(roomRequestDto.getRoomNumber());
        newRoom.setCapacity(roomRequestDto.getCapacity());
        newRoom.setGender(roomRequestDto.getGender());
//        newRoom.setDormitory(dormitoryService.getByName(roomRequestDto.getNameDormitory()));
        return roomRepository.save(newRoom);
    }

    @Override
    public Page<RoomListResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Page<Room> rooms = roomRepository.findAllByDormitory(dormitory,
                textToSearch != null ? textToSearch : "",
                pageable);
        return rooms.map(roomMapper::mapToRoomListResponseDto);
    }

    @Override
    public Room findById(UUID id) {
        return roomRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Room not found with id: " + id)
        );
    }

    @Override
    @Transactional
    public Room findByNumber(String number, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        return roomRepository.findByNumberAndDormitory(number, dormitory);
    }

    @Override
    @Transactional
    public List<Room> findAllWithFreePlaces(UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        return roomRepository.findAllWithFreePlaces(dormitory);
    }

    @Override
    @Transactional
    public void assignRoomToDormitory(UUID roomId, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Room room = findById(roomId);
        room.setDormitory(dormitory);
        dormitory.getRooms().add(room);
    }
}
