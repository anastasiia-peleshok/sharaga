package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.FurnitureMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Furniture;
import com.example.sharagasystem.model.dto.request.furniture.FurnitureRequestDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureResponseDto;
import com.example.sharagasystem.repository.FurnitureRepository;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.FurnitureService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FurnitureServiceImpl implements FurnitureService {
    private final FurnitureRepository furnitureRepository;
    private final FurnitureMapper furnitureMapper;
    private final DormitoryService dormitoryService;

    @Override
    @Transactional
    public FurnitureResponseDto create(FurnitureRequestDto furnitureRequestDto) {
        Furniture furniture = new Furniture();
        furniture.setName(furnitureRequestDto.getName());
        furniture.setItemNumber(furnitureRequestDto.getItemNumber());
        furniture.setType(furnitureRequestDto.getType());
        furniture.setStatus(furnitureRequestDto.getStatus());
        furniture.setPrice(furnitureRequestDto.getPrice());
        Furniture savedFurniture = furnitureRepository.save(furniture);
        assignFurnitureToDormitory(savedFurniture.getId(), furnitureRequestDto.getDormitoryId());
        return furnitureMapper.mapToResponse(savedFurniture);
    }

    @Override
    public Page<FurnitureLowInfoResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable) {
        //MAKE FILTER BY TYPES
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Page<Furniture> furnituries = furnitureRepository.findAllByDormitory(dormitory,
                textToSearch != null ? textToSearch : "",
                pageable);
        return furnituries.map(furnitureMapper::mapToLowInfoResponseDto);
    }

    @Override
    public Furniture findById(UUID id) {
        return furnitureRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Furniture not found with id: " + id)
        );
    }

    @Override
    @Transactional
    public void assignFurnitureToDormitory(UUID furnitureId, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Furniture furniture = findById(furnitureId);
        furniture.setDormitory(dormitory);
        dormitory.getFurnitures().add(furniture);
    }
}
