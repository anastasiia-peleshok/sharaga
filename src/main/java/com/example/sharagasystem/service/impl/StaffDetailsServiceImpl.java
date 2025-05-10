package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.StaffDetailsMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.StaffDetails;
import com.example.sharagasystem.model.dto.request.staff.StaffRequestDto;
import com.example.sharagasystem.model.dto.response.staff.StaffDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.staff.StaffResponseDto;
import com.example.sharagasystem.repository.StaffDetailsRepository;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.service.RoleService;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.StaffDetailsService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffDetailsServiceImpl implements StaffDetailsService {
    private final StaffDetailsRepository staffDetailsRepository;
    private final RoleService roleService;
    private final StaffDetailsMapper staffDetailsMapper;
    private final DormitoryService dormitoryService;

    @Override
    public StaffResponseDto create(StaffRequestDto requestDto) {
        StaffDetails staffDetails = new StaffDetails();
        staffDetails.setFirstName(requestDto.getFirstName());
        staffDetails.setLastName(requestDto.getLastName());
        staffDetails.setEmail(requestDto.getEmail());
        staffDetails.setPhoneNumber(requestDto.getPhoneNumber());
        staffDetails.setType(requestDto.getType());
        Role role = roleService.findByName(requestDto.getRole());
        staffDetails.setRole(role);
        StaffDetails savedStaffDetails = staffDetailsRepository.save(staffDetails);
        return staffDetailsMapper.mapToResponseDto(savedStaffDetails);
    }

    @Override
    @Transactional
    public void assignStaffToDormitory(UUID staffId, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        StaffDetails staffDetails = findById(staffId);
        dormitory.getStaff().add(staffDetails);
        staffDetails.getDormitory().add(dormitory);
    }

    @Override
    public StaffDetails findById(UUID id) {
        return staffDetailsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("StaffDetails not found with id = " + id)
        );
    }

    @Override
    public Page<StaffDetailsLowInfoResponseDto> findAllByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable) {
//        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Page<StaffDetails> staffDetails = staffDetailsRepository.findAllByDormitory(dormitoryId,
                textToSearch != null ? textToSearch : "",
                pageable);
        return staffDetails.map(staffDetailsMapper::mapToLowInfoResponseDto);
    }
}
