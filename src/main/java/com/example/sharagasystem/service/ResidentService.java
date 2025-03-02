package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.resident.ResidenUpdateDto;
import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;

import java.util.List;
import java.util.UUID;


public interface ResidentService {
    ResidentResponseDto saveResident(ResidentRequestDto resident);

    List<ResidentResponseDto> getAllResidents();

    ResidentResponseDto getResidentById(UUID id);

    ResidentResponseDto getResidentByEmail(String email);

    void deleteResidentById(UUID id);

    ResidentResponseDto updateResident(UUID id, ResidenUpdateDto updatedResident);
}
