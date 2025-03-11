package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.dto.furniture.FurnitureRequestDto;
import com.example.sharagasystem.dto.furniture.FurnitureResponseDto;
import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.mapper.FurnitureMapper;
import com.example.sharagasystem.mapper.FurnitureMapperHelper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.Furniture;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.repository.DormitoryRepository;
import com.example.sharagasystem.repository.FurnitureRepository;
import com.example.sharagasystem.repository.ResidentRepository;
import com.example.sharagasystem.repository.RoomRepository;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.repository.UserRepository;
import com.example.sharagasystem.service.FurnitureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FurnitureServiceImpl implements FurnitureService {
    private final FurnitureMapper furnitureMapper;
    private final FurnitureRepository furnitureRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final ResidentRepository residentRepository;
    private final DormitoryRepository dormitoryRepository;
    private final FurnitureMapperHelper furnitureMapperHelper;

    @Override
    public FurnitureResponseDto saveFurniture(FurnitureRequestDto furniture) {
        Furniture entity = furnitureMapper.toEntity(furniture, furnitureMapperHelper);
        Furniture savedFurniture = furnitureRepository.save(entity);
        return furnitureMapper.toDto(savedFurniture);
    }

    @Override
    public List<FurnitureResponseDto> getFurnitures() {
        return furnitureRepository.findAll().stream().map(furnitureMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public FurnitureResponseDto getFurnituresById(UUID id) {
        if (furnitureRepository.findById(id).isPresent()) {
            return furnitureMapper.toDto(furnitureRepository.findById(id).get());
        }
        throw new IllegalArgumentException("Furniture not found");
    }

    @Override
    public void deleteFurnitureById(UUID id) {
        if (furnitureRepository.findById(id).isPresent()) {
            furnitureRepository.delete(furnitureRepository.findById(id).get());
        } else {
            throw new IllegalArgumentException("Furniture not found");
        }
    }

    @Override
    public FurnitureResponseDto updateFurniture(UUID id, FurnitureRequestDto updatedFurniture) {
        if (furnitureRepository.findById(id).isPresent()) {
            Furniture furniture = furnitureRepository.findById(id).get();
            setUpdatedFields(furniture, updatedFurniture);
            return furnitureMapper.toDto(furnitureRepository.save(furniture));

        }
        throw new IllegalArgumentException("Resident not found");
    }

    private void setUpdatedFields(final Furniture furniture, final FurnitureRequestDto updatedFurnitureDto) {
        Optional.ofNullable(updatedFurnitureDto.getName()).ifPresent(furniture::setName);
        Optional.ofNullable(updatedFurnitureDto.getFurnitureType()).ifPresent(furniture::setFurnitureType);
        Optional.ofNullable(updatedFurnitureDto.getRoomId()).ifPresent(roomId -> {
            Room room = roomRepository.findById(UUID.fromString(roomId))
                    .orElseThrow(() -> new IllegalArgumentException("Invalid room: " + roomId));
            furniture.setRoom(room);
        });
        Optional.ofNullable(updatedFurnitureDto.getUserId()).ifPresent(userId -> {
            ResidentDetails user = residentRepository.findById(UUID.fromString(userId))
                    .orElseThrow(() -> new IllegalArgumentException("Invalid user: " + userId));
            furniture.setUser(user);
        });
        Optional.ofNullable(updatedFurnitureDto.getDormitoryId()).ifPresent(dormitoryId -> {
            Dormitory user = dormitoryRepository.findById(UUID.fromString(dormitoryId))
                    .orElseThrow(() -> new IllegalArgumentException("Invalid dormitory: " + dormitoryId));
            furniture.setDormitory(user);
        });

    }
}
