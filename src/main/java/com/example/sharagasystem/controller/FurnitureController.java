package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.request.furniture.FurnitureRequestDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureResponseDto;
import com.example.sharagasystem.service.FurnitureService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/furnituries")
@RequiredArgsConstructor
public class FurnitureController {
    private final FurnitureService furnitureService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public FurnitureResponseDto createFurniture(@RequestBody @Valid FurnitureRequestDto furnitureRequestDto){
        log.info("Entering POST /furnituries");
        return furnitureService.create(furnitureRequestDto);
    }

    @PatchMapping("/{furnitureId}/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void assignFurnitureToDormitory(@PathVariable UUID furnitureId,
                                           @PathVariable UUID dormitoryId){
        log.info("Entering PATCH /furnituries/{furnitureId}/dormitory/{dormitoryId} with furnitureId = {} and dormitoryId = {}", furnitureId, dormitoryId);
        furnitureService.assignFurnitureToDormitory(furnitureId, dormitoryId);
    }

    @GetMapping("/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<FurnitureLowInfoResponseDto> findAllByDormitory(@PathVariable UUID dormitoryId,
                                                                @RequestParam(required = false, defaultValue = "") String textToSearch,
                                                                Pageable pageable){
        log.info("Entering GET /furnituries/{dormitoryId} with dormitoryId {}", dormitoryId);
        return furnitureService.findAllByDormitory(dormitoryId, textToSearch, pageable);
    }
}
