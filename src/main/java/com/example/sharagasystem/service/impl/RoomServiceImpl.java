package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.FurnitureMapper;
import com.example.sharagasystem.mapper.RoomMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Gender;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.request.RoomRequestDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomListLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomListResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomResponseDto;
import com.example.sharagasystem.repository.RoomRepository;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.RoomService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final DormitoryService dormitoryService;
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final FurnitureMapper furnitureMapper;

    @Override
    @Transactional
    public Room create(RoomRequestDto roomRequestDto) {
        Room newRoom = new Room();
        newRoom.setNumber(roomRequestDto.getRoomNumber());
        newRoom.setCapacity(roomRequestDto.getCapacity());
        newRoom.setGender(roomRequestDto.getGender());
        calculateFreePlaces(newRoom);
        Room savedRoom = roomRepository.save(newRoom);
        assignRoomToDormitory(savedRoom.getId(), roomRequestDto.getDormitoryId());
        return savedRoom;
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
    public RoomResponseDto getById(UUID id) {
        Room room = findById(id);
        RoomResponseDto roomResponseDto = roomMapper.mapToResponseDto(room);
        List<FurnitureLowInfoResponseDto> list1 = room.getFurnitures()
                .stream()
                .map(furnitureMapper::mapToLowInfoResponseDto)
                .toList();
        roomResponseDto.setFurnitures(list1);
        return roomResponseDto;
    }

    @Override
    @Transactional
    public Room findByNumber(String number, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        return roomRepository.findByNumberAndDormitory(number, dormitory);
    }

    @Override
    @Transactional
    public Page<RoomListLowInfoResponseDto> findAllWithFreePlaces(UUID dormitoryId, Gender gender, String textToSearch, Pageable pageable) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Page<Room> allWithFreePlaces = roomRepository.findAllWithFreePlaces(dormitory, gender, textToSearch, pageable);
        return allWithFreePlaces.map(roomMapper::mapToRoomListLowInfoResponseDto);
    }

    @Override
    @Transactional
    public void assignRoomToDormitory(UUID roomId, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Room room = findById(roomId);
        room.setDormitory(dormitory);
        dormitory.getRooms().add(room);
    }

    @Transactional
    @Override
    public void calculateFreePlaces(Room room) {
        if(room != null) {
            log.warn("Room is null | Cannot calculate free places ");
        }
        int occupiedSeats;

        if(room.getResidents() == null || room.getResidents().isEmpty()) {
            occupiedSeats = 0;
        } else {
            occupiedSeats = room.getResidents().size();
        }
        room.setFree(countFreeSeats(occupiedSeats, room.getCapacity()));
        room.setOccupied(occupiedSeats);
    }

    private Integer countFreeSeats(int occupiedSeats, int capacity) {
        return capacity - occupiedSeats;
    }

}
