package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.DormitoryMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.dto.request.DormitoryRequestDto;
import com.example.sharagasystem.model.dto.response.DormitoryResponseDto;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.service.DormitoryService;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DormitoryServiceImpl implements DormitoryService {
    private final DormitoryRepository dormitoryRepository;
    private final DormitoryMapper dormitoryMapper;


    @Override
    public Dormitory getByName(String name) {
        return dormitoryRepository.findByName(name);
    }

    @Override
    public Dormitory findById(UUID id) {
        return dormitoryRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Dormitory not found with id: " + id)
        );
    }

    @Override
    @Transactional
    public DormitoryResponseDto getById(UUID id) {
        Dormitory dormitory = findById(id);
        return dormitoryMapper.mapToResponseDto(dormitory);
    }

    @Override
    public List<DormitoryResponseDto> findAllWithDeleteIsNull() {
        List<Dormitory> dormitories = dormitoryRepository.findAllByDeletedAtIsNull();
        return dormitories.stream().map(dormitoryMapper::mapToResponseDto).toList();
    }

    @Override
    public Dormitory create(DormitoryRequestDto requestDto) {
        Dormitory dormitory = dormitoryMapper.mapToModel(requestDto);
        return dormitoryRepository.save(dormitory);
    }

    @Override
    @Transactional
    public void softDelete(UUID id) {
        Dormitory dormitory = findById(id);
        dormitory.setDeletedAt(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void update(UUID id, DormitoryRequestDto dormitoryRequestDto) {
        Dormitory dormitory = findById(id);
        setUpdateFields(dormitory, dormitoryRequestDto);
        dormitoryRepository.save(dormitory);
    }

    private void setUpdateFields(Dormitory dormitory, DormitoryRequestDto dormitoryRequestDto) {
        if(dormitoryRequestDto.getName() != null) {
            dormitory.setName(dormitoryRequestDto.getName());
        }
        if(dormitoryRequestDto.getAddress() != null) {
            dormitory.setAddress(dormitoryRequestDto.getAddress());
        }
        dormitory.setPrice(dormitoryRequestDto.getPrice());
        dormitory.setFloors(dormitoryRequestDto.getFloors());
        dormitory.setCapacity(dormitoryRequestDto.getCapacity());
        dormitory.setCity(dormitoryRequestDto.getCity());
        dormitory.setZipCode(dormitoryRequestDto.getZipCode());
    }
}
