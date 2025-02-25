package com.example.sharagasystem.mapper;

import com.example.sharagasystem.config.MapperConfig;
import com.example.sharagasystem.dto.furniture.FurnitureRequestDto;
import com.example.sharagasystem.dto.furniture.FurnitureResponseDto;
import com.example.sharagasystem.model.Furniture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {
        RoomMapper.class, DormitoryMapper.class, ResidentMapper.class
})
public interface FurnitureMapper {
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "dormitory", ignore = true)
    @Mapping(target = "user", ignore = true)
    Furniture toEntity(FurnitureRequestDto furnitureRequestDto);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "dormitoryId", source = "dormitory.id")
    @Mapping(target = "userId", source = "user.id")
    FurnitureResponseDto toDto(Furniture furniture);
}
