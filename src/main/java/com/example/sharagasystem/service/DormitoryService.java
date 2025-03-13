package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.model.Dormitory;
import java.util.UUID;

public interface DormitoryService {

    Dormitory getByName(String name);

    Dormitory findById(UUID id);

    Dormitory create(DormitoryRequestDto requestDto);

    Dormitory update(UUID id, DormitoryRequestDto requestDto);
}
