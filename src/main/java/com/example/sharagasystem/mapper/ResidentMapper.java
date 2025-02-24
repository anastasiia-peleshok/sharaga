package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;
import com.example.sharagasystem.model.ResidentDetails;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {
        RoomMapper.class, DormitoryMapper.class, FurnitureMapper.class
})
public interface ResidentMapper {
    @Mapping(target= "roomNumber", source = "room.number", ignore = true)
    @Mapping(target= "dormitoryName", source = "dormitory.name", ignore = true)
    ResidentResponseDto toDto(ResidentDetails residentDetails);

    @Mapping(target= "room", ignore = true)
    @Mapping(target= "dormitory", ignore = true)
    ResidentDetails toEntity(ResidentRequestDto residentResponseDto);
}
