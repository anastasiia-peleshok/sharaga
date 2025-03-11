package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.furniture.FurnitureRequestDto;
import com.example.sharagasystem.dto.furniture.FurnitureResponseDto;
import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;
import com.example.sharagasystem.service.impl.FurnitureServiceImpl;
import com.example.sharagasystem.service.impl.ResidentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/furniture")
public class FurnitureController {

    private final FurnitureServiceImpl furnitureService;

    @GetMapping("/{id}")
    public FurnitureResponseDto getFurnitureById(@PathVariable UUID id) {
        return furnitureService.getFurnituresById(id);
    }

    @GetMapping
    public List<FurnitureResponseDto> getFurnitures() {
        return furnitureService.getFurnitures();
    }

    @PostMapping
    public FurnitureResponseDto addFurniture(@RequestBody FurnitureRequestDto furnitureRequestDto) {
        return furnitureService.saveFurniture(furnitureRequestDto);
    }

    @DeleteMapping({"/{id}"})
    public void deleteFurniture(@PathVariable UUID id) {
        furnitureService.deleteFurnitureById(id);
    }

    @PutMapping({"/{id}"})
    public FurnitureResponseDto updateFurniture(@PathVariable UUID id, @RequestBody FurnitureRequestDto furnitureRequestDto) {
        return furnitureService.updateFurniture(id, furnitureRequestDto);
    }

}
