package com.example.sharagasystem.service;


import com.example.sharagasystem.model.dto.request.ResidentRequestDto;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.ResidentResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ResidentService {
    ResidentResponseDto saveResident(ResidentRequestDto resident);

    void addResidentToDormitory(UUID residentId, UUID dormitoryId);

    // Get all residents
    List<ResidentResponseDto> getAllResidents();

    void assignToRoom(UUID residentId, UUID roomId);

    // Get a resident by ID
    ResidentResponseDto getResidentById(UUID id);

    ResidentResponseDto getResidentByEmail(String email);

    void deleteResidentById(UUID id);

    ResidentResponseDto updateResident(UUID id, ResidentRequestDto updatedResident);

    Page<ResidentDetailsLowInfoResponseDto> getResidentsByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable);
}
