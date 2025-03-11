package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.furniture.FurnitureRequestDto;
import com.example.sharagasystem.dto.furniture.FurnitureResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FurnitureService{
    FurnitureResponseDto saveFurniture(FurnitureRequestDto furniture);

    List<FurnitureResponseDto> getFurnitures();

    FurnitureResponseDto getFurnituresById(UUID id);

    void deleteFurnitureById(UUID id);

    FurnitureResponseDto updateFurniture(UUID id, FurnitureRequestDto updatedFurniture);
}
