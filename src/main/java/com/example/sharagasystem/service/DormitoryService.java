package com.example.sharagasystem.service;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.dto.request.DormitoryRequestDto;
import com.example.sharagasystem.model.dto.response.DormitoryResponseDto;
import java.util.List;
import java.util.UUID;

public interface DormitoryService {

    Dormitory getByName(String name);

    DormitoryResponseDto getById(UUID id);

    Dormitory findById(UUID id);

    Dormitory create(DormitoryRequestDto requestDto);

    List<DormitoryResponseDto> findAllWithDeleteIsNull();

    void softDelete(UUID id);

    void update(UUID id, DormitoryRequestDto dormitoryRequestDto);
}
