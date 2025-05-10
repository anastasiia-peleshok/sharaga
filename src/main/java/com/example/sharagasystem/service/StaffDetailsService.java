package com.example.sharagasystem.service;

import com.example.sharagasystem.model.StaffDetails;
import com.example.sharagasystem.model.dto.request.staff.StaffRequestDto;
import com.example.sharagasystem.model.dto.response.staff.StaffDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.staff.StaffResponseDto;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StaffDetailsService {

    StaffResponseDto create(StaffRequestDto requestDto);

    void assignStaffToDormitory(UUID staffId, UUID dormitoryId);

    StaffDetails findById(UUID id);

    Page<StaffDetailsLowInfoResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable);
}
