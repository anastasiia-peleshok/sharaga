package com.example.sharagasystem.service;

import com.example.sharagasystem.model.Furniture;
import com.example.sharagasystem.model.dto.request.furniture.FurnitureRequestDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FurnitureService {

    FurnitureResponseDto create(FurnitureRequestDto furnitureRequestDto);

    Page<FurnitureLowInfoResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable);

    Furniture findById(UUID id);

    void assignFurnitureToDormitory(UUID furnitureId, UUID dormitoryId);
}
