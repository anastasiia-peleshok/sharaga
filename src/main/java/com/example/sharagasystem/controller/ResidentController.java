package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.ResidentRequestDto;
import com.example.sharagasystem.dto.ResidentResponseDto;
import com.example.sharagasystem.service.ResidentrServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/resident")
public class ResidentController {
    private final ResidentrServiceImplementation residentrServiceImplementation;

    @GetMapping
    public ResponseEntity<ResidentResponseDto> getResidentById(@PathVariable UUID id) {
        return ResponseEntity.ok(residentrServiceImplementation.getResidentById(id));
    }
    @PostMapping
    public ResponseEntity<ResidentResponseDto> addResident(@RequestBody ResidentRequestDto residentRequestDto) {
        return ResponseEntity.ok(residentrServiceImplementation.saveResident(residentRequestDto));
    }
}
