package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.ResidentDetailsMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.dto.request.ResidentRequestDto;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.ResidentResponseDto;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.service.RoleService;
import com.example.sharagasystem.service.DormitoryService;
import com.example.sharagasystem.service.ResidentService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final DormitoryService dormitoryService;
    private final RoleService roleService;
    private final ResidentDetailsMapper residentDetailsMapper;

    private final ModelMapper modelMapper;

    public ResidentDetails toEntity(ResidentRequestDto dto) {
        return modelMapper.map(dto, ResidentDetails.class);
    }

    public ResidentResponseDto toDTO(ResidentDetails entity) {
        return modelMapper.map(entity, ResidentResponseDto.class);
    }
    @Override
    public ResidentResponseDto saveResident(ResidentRequestDto residentRequestDto) {
        ResidentDetails residentDetails = new ResidentDetails();
        residentDetails.setFirstName(residentRequestDto.getFirstName());
        residentDetails.setLastName(residentRequestDto.getLastName());
        residentDetails.setEmail(residentRequestDto.getEmail());
        residentDetails.setPhoneNumber(residentRequestDto.getPhoneNumber());

        Role byName = roleService.findByName(residentRequestDto.getRole());
        residentDetails.setRole(byName);
        ResidentDetails savedUser = residentRepository.save(residentDetails);
        return toDTO(savedUser);
    }

    @Override
    @Transactional
    public void addResidentToDormitory(UUID residentId, UUID dormitoryId) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        ResidentDetails residentDetails = findById(residentId);
        residentDetails.setDormitory(dormitory);
        dormitory.getResidents().add(residentDetails);
    }

    public ResidentDetails findById(UUID residentId) {
        return residentRepository.findById(residentId).orElseThrow(
                () -> new NotFoundException("Resident not found with id: " + residentId)
        );
    }

    @Override
    public List<ResidentResponseDto> getAllResidents() {
        return residentRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public ResidentResponseDto getResidentById(UUID id) {
        if(residentRepository.findById(id).isPresent()){
            return toDTO(residentRepository.findById(id).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public ResidentResponseDto getResidentByEmail(String email) {
        if(residentRepository.findByEmail(email).isPresent()){
            return toDTO(residentRepository.findByEmail(email).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public void deleteResidentById(UUID id) {
        if (residentRepository.findById(id).isPresent()) {
            residentRepository.delete(residentRepository.findById(id).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public ResidentResponseDto updateResident(UUID id, ResidentRequestDto updatedResident) {
        ResidentResponseDto responseDto = new ResidentResponseDto();
        Optional<ResidentDetails> optionalResident = residentRepository.findById(id);
        if(optionalResident.isPresent()){
            ResidentDetails residentDetails = optionalResident.get();
            if(updatedResident.getFirstName() != null){
                residentDetails.setFirstName(updatedResident.getFirstName());
            }
            if(updatedResident.getLastName() != null){
                residentDetails.setLastName(updatedResident.getLastName());
            }
            if(updatedResident.getEmail() != null){
                residentDetails.setEmail(updatedResident.getEmail());
            }
//            if(updatedResident.getRole() != null){
//               residentDetails.setRole(updatedResident.getRole());
//            }
            if(updatedResident.getPhoneNumber() != null){
                residentDetails.setPhoneNumber(updatedResident.getPhoneNumber());
            }
            return toDTO(residentRepository.save(residentDetails));

        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public Page<ResidentDetailsLowInfoResponseDto> getResidentsByDormitory(UUID dormitoryId, String textToSearch, Pageable pageable) {
        Dormitory dormitory = dormitoryService.findById(dormitoryId);
        Page<ResidentDetails> allByDormitory = residentRepository.findAllByDormitory(dormitory,
                textToSearch != null ? textToSearch : "",
                pageable);
        return allByDormitory.map(residentDetailsMapper::mapToResidentDetailsLowInfoResponseDto);
    }
}
