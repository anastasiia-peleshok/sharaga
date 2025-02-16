package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.ResidentRequestDto;
import com.example.sharagasystem.dto.ResidentResponseDto;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.security.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidentrServiceImplementation implements ResidentrService {
private final ResidentRepository residentRepository;

    private final ModelMapper modelMapper;

    public ResidentDetails toEntity(ResidentRequestDto dto) {
        return modelMapper.map(dto, ResidentDetails.class);
    }

    public ResidentResponseDto toDTO(ResidentDetails entity) {
        return modelMapper.map(entity, ResidentResponseDto.class);
    }
    @Override
    public ResidentResponseDto saveResident(ResidentRequestDto residentRequestDto) {
        ResidentDetails resident = toEntity(residentRequestDto);
        ResidentDetails savedUser = residentRepository.save(resident);
        return toDTO(savedUser);
    }

    @Override
    public List<ResidentResponseDto> getAllResidents() {
        return residentRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
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
        if(residentRepository.findById(id).isPresent()){
           residentRepository.delete(residentRepository.findById(id).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public ResidentResponseDto updateResident(UUID id, ResidentRequestDto updatedResident) {
        if(residentRepository.findById(id).isPresent()){
            ResidentDetails residentDetails = residentRepository.findById(id).get();
            if(updatedResident.getFirstName() != null){
                residentDetails.setFirstName(updatedResident.getFirstName());
            }
            if(updatedResident.getLastName() != null){
                residentDetails.setLastName(updatedResident.getLastName());
            }
            if(updatedResident.getEmail() != null){
                residentDetails.setEmail(updatedResident.getEmail());
            }
            if(updatedResident.getRole() != null){
               residentDetails.setRole(updatedResident.getRole());
            }
            if(updatedResident.getPhoneNumber() != null){
                residentDetails.setPhoneNumber(updatedResident.getPhoneNumber());
            }
            return toDTO(residentRepository.save(residentDetails));

        }
        throw new IllegalArgumentException("Resident not found");
    }
}
