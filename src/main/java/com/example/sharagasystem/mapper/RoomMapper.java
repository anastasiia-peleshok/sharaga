package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomListLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomListResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMapper {
    private final ResidentDetailsMapper residentDetailsMapper;

    public RoomListResponseDto mapToRoomListResponseDto(Room room) {
        if(room == null) {
            return null;
        }
        RoomListResponseDto responseDto = new RoomListResponseDto();
        responseDto.setId(room.getId());
        responseDto.setNumber(room.getNumber());
        responseDto.setCapacity(room.getCapacity());
        responseDto.setType(room.getGender().name());
        responseDto.setFreeSeats(room.getFree());
        responseDto.setOccupiedSeats(room.getOccupied());
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

    public RoomListLowInfoResponseDto mapToRoomListLowInfoResponseDto(Room room) {
        if(room == null) {
            return null;
        }
        RoomListLowInfoResponseDto responseDto = new RoomListLowInfoResponseDto();
        responseDto.setId(room.getId());
        responseDto.setNumber(room.getNumber());
        responseDto.setGender(room.getGender());
        responseDto.setFree(room.getFree());
        return responseDto;
    }

    public RoomResponseDto mapToResponseDto(Room room) {
        if(room == null) {
            return null;
        }
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        roomResponseDto.setId(room.getId());
        roomResponseDto.setNumber(room.getNumber());
        roomResponseDto.setGender(room.getGender());
        roomResponseDto.setCapacity(room.getCapacity());
        List<ResidentDetailsLowInfoResponseDto> list = room.getResidents().stream()
                .map(residentDetailsMapper::mapToResidentDetailsLowInfoResponseDto)
                .toList();
        roomResponseDto.setResidents(list);
        return roomResponseDto;
    }
}
