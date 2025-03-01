package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Furniture;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FurnitureMapper {
    private final RoomMapper roomMapper;
    private final DormitoryMapper dormitoryMapper;

    public FurnitureResponseDto mapToResponse(Furniture furniture) {
        if(furniture == null) {
            return null;
        }
        FurnitureResponseDto responseDto = new FurnitureResponseDto();
        responseDto.setId(furniture.getId());
        responseDto.setName(furniture.getName());
        responseDto.setItemNumber(furniture.getItemNumber());
        responseDto.setFurnitureType(furniture.getType().name());
        responseDto.setPrice(furniture.getPrice());
        responseDto.setStatus(furniture.getStatus() != null ? furniture.getStatus().name() : null);
        responseDto.setRoom(furniture.getRoom() != null ? roomMapper.mapToRoomLowInfoResponseDto(furniture.getRoom()) : null);
        responseDto.setDormitory(furniture.getDormitory() != null ? dormitoryMapper.mapToLowInfoResponseDto(furniture.getDormitory()) : null);
        return responseDto;
    }

    public FurnitureLowInfoResponseDto mapToLowInfoResponseDto(Furniture furniture) {
        if(furniture == null) {
            return null;
        }
        FurnitureLowInfoResponseDto responseDto = new FurnitureLowInfoResponseDto();
        responseDto.setId(furniture.getId());
        responseDto.setItemNumber(furniture.getItemNumber());
        responseDto.setType(furniture.getType() != null ? furniture.getType() : null);
        responseDto.setStatus(furniture.getStatus() != null ? furniture.getStatus() : null);
        responseDto.setPrice(furniture.getPrice());
        responseDto.setRoomNumber(furniture.getRoom() != null ? furniture.getRoom().getNumber() : null);
        return responseDto;
    }
}
