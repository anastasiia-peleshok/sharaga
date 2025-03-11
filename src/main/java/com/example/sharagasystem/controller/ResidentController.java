package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;
import com.example.sharagasystem.service.impl.ResidentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/resident")
public class ResidentController {
    private final ResidentServiceImpl residentServiceImpl;

    @GetMapping("/{id}")
    public ResidentResponseDto getResidentById(@PathVariable UUID id) {
        return residentServiceImpl.getResidentById(id);
    }

    @GetMapping
    public List<ResidentResponseDto> getResidents() {
        return residentServiceImpl.getAllResidents();
    }

    @PostMapping
    public ResidentResponseDto addResident(@RequestBody ResidentRequestDto residentRequestDto) {
        return residentServiceImpl.saveResident(residentRequestDto);
    }

    @DeleteMapping({"/{id}"})
    public void deleteResident(@PathVariable UUID id) {
        residentServiceImpl.deleteResidentById(id);
    }

    @PutMapping({"/{id}"})
    public ResidentResponseDto updateResident(@PathVariable UUID id, @RequestBody ResidentRequestDto residentRequestDto) {
       return residentServiceImpl.updateResident(id, residentRequestDto);
    }

}
