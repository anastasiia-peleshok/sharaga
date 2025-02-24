package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.furniture.FurnitureRequestDto;
import com.example.sharagasystem.dto.furniture.FurnitureResponseDto;
import com.example.sharagasystem.model.Furniture;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {
        RoomMapper.class, DormitoryMapper.class, ResidentMapper.class
})
public interface FurnitureMapper {
    @Mapping(target="room", ignore = true)
    @Mapping(target="dormitory", ignore = true)
    @Mapping(target="user", ignore = true)
    Furniture toEntity(FurnitureRequestDto furnitureRequestDto);

    @Mapping(target="roomNumber", source = "room.number")
    @Mapping(target="dormitoryName", source = "dormitory.name")
    @Mapping(target="userLastName", source = "user.lastName")
    FurnitureResponseDto toDto(Furniture furniture);
}
