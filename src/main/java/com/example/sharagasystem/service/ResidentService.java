package com.example.sharagasystem.service;


import com.example.sharagasystem.model.ResidentDetails;
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

    ResidentDetails save(ResidentDetails residentDetails);
    // Get all residents
    List<ResidentResponseDto> getAllResidents();

    void assignToRoom(UUID residentId, UUID roomId);

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

    Page<ResidentDetailsLowInfoResponseDto> getResidentsByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable);

    void roomResettlement(UUID roomId, UUID residentId);

    List<ResidentDetails> saveAll(List<ResidentDetails> residentDetails);
}
