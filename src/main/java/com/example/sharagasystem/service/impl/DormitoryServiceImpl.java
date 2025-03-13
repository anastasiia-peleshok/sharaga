package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.service.DormitoryService;
import jakarta.transaction.Transactional;
import java.util.UUID;
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
    public Dormitory findById(UUID id) {
        return dormitoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find dormitory with id: " + id));
    }

    @Override
    public Dormitory create(DormitoryRequestDto requestDto) {
        Dormitory dormitory = new Dormitory();
        dormitory.setName(requestDto.getName());
        dormitory.setAddress(requestDto.getAddress());
        dormitory.setCapacity(requestDto.getCapacity());
        return dormitoryRepository.save(dormitory);
    }

    @Override
    public Dormitory update(UUID id, DormitoryRequestDto requestDto) {
        Dormitory dormitory = findById(id);
        dormitory.setName(requestDto.getName());
        dormitory.setAddress(requestDto.getAddress());
        dormitory.setCapacity(requestDto.getCapacity());
        return dormitoryRepository.save(dormitory);
    }


}
