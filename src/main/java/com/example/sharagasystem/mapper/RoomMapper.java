package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.room.RoomRequestDto;
import com.example.sharagasystem.dto.room.RoomResponseDto;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {
        DormitoryMapper.class
})
public interface RoomMapper {
    @Mapping(target = "dormitoryId", source = "dormitory.id")
    RoomResponseDto toDto(Room room);

    @Mapping(target = "dormitory", ignore = true)
    Room toEntity(RoomRequestDto roomRequestDto);
}
