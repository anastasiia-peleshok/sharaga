package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.RoomRequestDto;
import com.example.sharagasystem.model.Room;

public interface RoomService {
    Room create(RoomRequestDto roomRequestDto);
}
