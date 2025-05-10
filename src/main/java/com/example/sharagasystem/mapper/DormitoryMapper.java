package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.dto.request.DormitoryRequestDto;
import com.example.sharagasystem.model.dto.response.DormitoryResponseDto;
import com.example.sharagasystem.model.dto.response.dormitory.DormitoryLowInfoResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DormitoryMapper {

    Dormitory mapToModel(DormitoryRequestDto dormitory);

    DormitoryResponseDto mapToResponseDto(Dormitory dormitory);

    DormitoryLowInfoResponseDto mapToLowInfoResponseDto(Dormitory dormitory);
}
