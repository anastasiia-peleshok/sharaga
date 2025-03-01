package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.ResidentRequestDto;
import com.example.sharagasystem.dto.ResidentResponseDto;
import com.example.sharagasystem.service.ResidentService;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/resident")
public class ResidentController {

    private final ResidentService residentService;

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponseDto> getResidentById(@PathVariable UUID id) {
        return ResponseEntity.ok(residentService.getResidentById(id));
    }

    @GetMapping
    public List<ResidentResponseDto> getResidentByAll() {
        return residentService.getAllResidents();
    }
    @PostMapping
    public ResponseEntity<ResidentResponseDto> addResident(@RequestBody ResidentRequestDto residentRequestDto) {
        return ResponseEntity.ok(residentService.saveResident(residentRequestDto));
    }
}
