package com.example.sharagasystem.service;

import com.example.sharagasystem.model.Gender;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.request.RoomRequestDto;
import com.example.sharagasystem.model.dto.response.room.RoomListLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomListResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    Room create(RoomRequestDto roomRequestDto);

    Page<RoomListResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable);

    Room findById(UUID id);

    Room findByNumber(String number, UUID dormitoryId);

    Page<RoomListLowInfoResponseDto> findAllWithFreePlaces(UUID dormitoryId, Gender gender, String textToSearch, Pageable pageable);

    void assignRoomToDormitory(UUID roomId, UUID dormitoryId);

    void calculateFreePlaces(Room room);
}
