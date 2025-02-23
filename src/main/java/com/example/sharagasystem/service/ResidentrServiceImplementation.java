package com.example.sharagasystem.service;

import com.example.sharagasystem.dto.ResidentRequestDto;
import com.example.sharagasystem.dto.ResidentResponseDto;
import com.example.sharagasystem.mapper.ResidentMapper;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.security.repository.ResidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidentrServiceImplementation implements ResidentrService {
private final ResidentRepository residentRepository;
private final ResidentMapper residentMapper;
    @Override
    public ResidentResponseDto saveResident(ResidentRequestDto residentRequestDto) {
        ResidentDetails resident = residentMapper.toEntity(residentRequestDto);
        ResidentDetails savedUser = residentRepository.save(resident);
        return residentMapper.toDTO(savedUser);
    }

    @Override
    public List<ResidentResponseDto> getAllResidents() {
        return residentRepository.findAll().stream().map(residentMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ResidentResponseDto getResidentById(UUID id) {
        if(residentRepository.findById(id).isPresent()){
            return residentMapper.toDTO(residentRepository.findById(id).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public ResidentResponseDto getResidentByEmail(String email) {
        if(residentRepository.findByEmail(email).isPresent()){
            return residentMapper.toDTO(residentRepository.findByEmail(email).get());
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
        ResidentDetails resident = residentMapper.toEntity(updatedResident);
        if(residentRepository.findById(id).isPresent()){
            ResidentDetails residentDetails = residentRepository.findById(id).get();
            if(updatedResident.getFirstName() != null){
                residentDetails.setFirstName(resident.getFirstName());
            }
            if(updatedResident.getLastName() != null){
                residentDetails.setLastName(resident.getLastName());
            }
            if(updatedResident.getEmail() != null){
                residentDetails.setEmail(resident.getEmail());
            }
            if(updatedResident.getRole() != null){
               residentDetails.setRole(resident.getRole());
            }
            if(updatedResident.getPhoneNumber() != null){
                residentDetails.setPhoneNumber(resident.getPhoneNumber());
            }
            return residentMapper.toDTO(residentRepository.save(residentDetails));

        }
        throw new IllegalArgumentException("Resident not found");
    }
}
