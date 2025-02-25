package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;
import com.example.sharagasystem.service.impl.ResidentServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/resident")
public class ResidentController {
    private final ResidentServiceImplementation residentServiceImplementation;

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponseDto> getResidentById(@PathVariable UUID id) {
        return ResponseEntity.ok(residentServiceImplementation.getResidentById(id));
    }
    @GetMapping
    public ResponseEntity<List<ResidentResponseDto>> getResidents() {
        return ResponseEntity.ok(residentServiceImplementation.getAllResidents());
    }
    @PostMapping
    public ResponseEntity<ResidentResponseDto> addResident(@RequestBody ResidentRequestDto residentRequestDto) {
        return ResponseEntity.ok(residentServiceImplementation.saveResident(residentRequestDto));
    }

}
