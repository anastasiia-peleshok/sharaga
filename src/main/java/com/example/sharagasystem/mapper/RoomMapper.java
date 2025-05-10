package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.room.RoomRequestDto;
import com.example.sharagasystem.dto.room.RoomResponseDto;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.response.RoomListResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomLowInfoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomListResponseDto mapToRoomListResponseDto(Room room) {
        if(room == null) {
            return null;
        }
        int occupiedSeats;

        if(room.getResidents() == null || room.getResidents().isEmpty()) {
            occupiedSeats = 0;
        } else {
            occupiedSeats = room.getResidents().size();
        }

        RoomListResponseDto responseDto = new RoomListResponseDto();
        responseDto.setId(room.getId());
        responseDto.setNumber(room.getNumber());
        responseDto.setCapacity(room.getCapacity());
        responseDto.setGender(room.getGender().getName());
        responseDto.setFreeSeats(countFreeSeats(occupiedSeats, room.getCapacity()));
        responseDto.setOccupiedSeats(occupiedSeats);
        return responseDto;
    }

    public RoomLowInfoResponseDto mapToRoomLowInfoResponseDto(Room room) {
        if(room == null) {
            return null;
        }
        RoomLowInfoResponseDto responseDto = new RoomLowInfoResponseDto();
        responseDto.setId(room.getId());
        responseDto.setNumber(room.getNumber());
        return responseDto;
    }

    private Integer countFreeSeats(int occupiedSeats, int capacity) {
        return capacity - occupiedSeats;
    }
}
