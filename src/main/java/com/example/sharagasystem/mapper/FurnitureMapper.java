package com.example.sharagasystem.mapper;

import com.example.sharagasystem.config.MapperConfig;
import com.example.sharagasystem.dto.furniture.FurnitureRequestDto;
import com.example.sharagasystem.dto.furniture.FurnitureResponseDto;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Furniture;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.repository.RoomRepository;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(config = MapperConfig.class, componentModel = "spring", uses = { FurnitureMapperHelper.class })
public interface FurnitureMapper {
    @Mapping(target = "room", expression = "java(furnitureMapperHelper.getRoomById(furnitureRequestDto.getRoomId()))")
    @Mapping(target = "dormitory", expression = "java(furnitureMapperHelper.getDormitoryById(furnitureRequestDto.getDormitoryId()))")
    @Mapping(target = "user", expression = "java(furnitureMapperHelper.getUserById(furnitureRequestDto.getUserId()))")
    @Mapping(target = "furnitureType", source = "furnitureRequestDto.furnitureType")  // Add this line

    Furniture toEntity(FurnitureRequestDto furnitureRequestDto, @Context FurnitureMapperHelper furnitureMapperHelper);

    @Mapping(target = "roomId", source = "room.id")
    @Mapping(target = "dormitoryId", source = "dormitory.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "furnitureType", source = "furniture.furnitureType")  // Add this line

    FurnitureResponseDto toDto(Furniture furniture);
}