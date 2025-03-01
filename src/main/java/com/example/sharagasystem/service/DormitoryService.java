package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.model.Dormitory;

public interface DormitoryService {

    Dormitory getByName(String name);

    Dormitory create(DormitoryRequestDto requestDto);
}
