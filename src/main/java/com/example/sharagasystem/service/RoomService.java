package com.example.sharagasystem.service;

import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.request.RoomRequestDto;
import com.example.sharagasystem.model.dto.response.RoomListResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoomService {
    Room create(RoomRequestDto roomRequestDto);

    Page<RoomListResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable);

    Room findById(UUID id);

    Room findByNumber(String number, UUID dormitoryId);

    List<Room> findAllWithFreePlaces(UUID dormitoryId);

    void assignRoomToDormitory(UUID roomId, UUID dormitoryId);
}
