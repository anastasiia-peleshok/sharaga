package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.dormitory.DormitoryRequestDto;
import com.example.sharagasystem.dto.dormitory.DormitoryResponseDto;
import com.example.sharagasystem.model.Dormitory;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface DormitoryMapper {
    DormitoryResponseDto toDto(Dormitory dormitory);
    Dormitory toEntity(DormitoryRequestDto dormitoryRequestDto);
}
