package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;
import com.example.sharagasystem.mapper.ResidentMapper;
import com.example.sharagasystem.mapper.RoleMapperHelper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.repository.RoleRepository;
import com.example.sharagasystem.repository.RoomRepository;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.BeanDefinitionDsl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {
    private final ResidentRepository residentRepository;
    private final ResidentMapper residentMapper;
    private final DormitoryRepository dormitoryRepository;
    private final RoomRepository roomRepository;
    private final RoleRepository roleRepository;
    private final RoleMapperHelper roleMapperHelper;

    @Override
    public ResidentResponseDto saveResident(ResidentRequestDto residentRequestDto) {
        ResidentDetails resident = residentMapper.toEntity(residentRequestDto, roleMapperHelper);
        ResidentDetails savedUser = residentRepository.save(resident);
        return residentMapper.toDto(savedUser);
    }

    @Override
    public List<ResidentResponseDto> getAllResidents() {
        return residentRepository.findAll().stream().map(residentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ResidentResponseDto getResidentById(UUID id) {
        if (residentRepository.findById(id).isPresent()) {
            return residentMapper.toDto(residentRepository.findById(id).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public ResidentResponseDto getResidentByEmail(String email) {
        if (residentRepository.findByEmail(email).isPresent()) {
            return residentMapper.toDto(residentRepository.findByEmail(email).get());
        }
        throw new IllegalArgumentException("Resident not found");
    }

    @Override
    public void deleteResidentById(UUID id) {
        if (residentRepository.findById(id).isPresent()) {
            residentRepository.delete(residentRepository.findById(id).get());
        } else {
            throw new IllegalArgumentException("Resident not found");
        }
    }

    @Override
    public ResidentResponseDto updateResident(UUID id, ResidentRequestDto updatedResident) {
        if (residentRepository.findById(id).isPresent()) {
            ResidentDetails residentDetails = residentRepository.findById(id).get();
            setUpdatedFields(residentDetails, updatedResident);
            return residentMapper.toDto(residentRepository.save(residentDetails));

        }
        throw new IllegalArgumentException("Resident not found");
    }

    private void setUpdatedFields(final ResidentDetails resident, final ResidentRequestDto updateDto) {
        Optional.ofNullable(updateDto.getFirstName()).ifPresent(resident::setFirstName);
        Optional.ofNullable(updateDto.getLastName()).ifPresent(resident::setLastName);
        Optional.ofNullable(updateDto.getEmail()).ifPresent(resident::setEmail);
        Optional.ofNullable(updateDto.getPhoneNumber()).ifPresent(resident::setPhoneNumber);
        Optional.ofNullable(updateDto.getRole()).ifPresent(roleName -> {
            Role.RoleName enumRoleName = Role.RoleName.valueOf(roleName); // Convert string to enum
            Role roleEntity = roleRepository.findByRoleName(enumRoleName)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid role: " + roleName));
            resident.setRole(roleEntity);
        });

        Optional.ofNullable(updateDto.getDormitoryId()).ifPresent(
                dormitoryName -> {
                    Optional<Dormitory> dormitory = dormitoryRepository.findByName(dormitoryName);
                    dormitory.ifPresent(resident::setDormitory);
                }
        );
        Optional.ofNullable(updateDto.getRoomId()).ifPresent(
                roomNumber -> {
                    Optional<Room> room = roomRepository.findByNumber(roomNumber);
                    room.ifPresent(resident::setRoom);
                }
        );


    }
}
