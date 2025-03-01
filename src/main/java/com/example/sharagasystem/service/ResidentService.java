package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.ResidentRequestDto;
import com.example.sharagasystem.dto.ResidentResponseDto;

import java.util.List;
import java.util.UUID;


public interface ResidentService {
    ResidentResponseDto saveResident(ResidentRequestDto resident);

    // Get all residents
    List<ResidentResponseDto> getAllResidents();

    // Get a resident by ID
    ResidentResponseDto getResidentById(UUID id);

    // Get a resident by a unique field, e.g., email
    ResidentResponseDto getResidentByEmail(String email);

    // Delete a resident by ID
    void deleteResidentById(UUID id);

//    // Check if a resident exists by email (for unique constraint checks)
//    boolean existsByEmail(String email);

    // Update resident details
    ResidentResponseDto updateResident(UUID id, ResidentRequestDto updatedResident);
}
