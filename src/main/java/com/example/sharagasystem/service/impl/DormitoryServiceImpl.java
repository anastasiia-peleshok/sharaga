package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.service.DormitoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;


    @Override
    @Transactional
    public Dormitory getByName(String name) {
        return dormitoryRepository.findByName(name);
    }

    @Override
    public Dormitory create(DormitoryRequestDto requestDto) {
        Dormitory dormitory = new Dormitory();
        dormitory.setName(requestDto.getDormitoryName());
        dormitory.setAddress(requestDto.getDormitoryAddress());
        return dormitoryRepository.save(dormitory);
    }
}
