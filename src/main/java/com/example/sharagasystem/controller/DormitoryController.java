package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.dto.response.DormitoryResponseDto;
import com.example.sharagasystem.mapper.DormitoryMapper;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.service.DormitoryService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dormitory")
public class DormitoryController {
    private final DormitoryService dormitoryService;
    private final DormitoryMapper dormitoryMapper;

    @PostMapping
    public Dormitory create(@RequestBody DormitoryRequestDto dormitoryRequestDto) {
        return dormitoryService.create(dormitoryRequestDto);
    }

    @GetMapping("/{dormitoryId}")
    public DormitoryResponseDto getById(@PathVariable UUID dormitoryId){
        return dormitoryMapper.mapToDormitoryResponseDto(dormitoryService.findById(dormitoryId));
    }

    @PutMapping("/{dormitoryId}")
    public DormitoryResponseDto update(@PathVariable UUID dormitoryId, @RequestBody DormitoryRequestDto dormitoryRequestDto) {
        return dormitoryMapper.mapToDormitoryResponseDto(dormitoryService.update(dormitoryId, dormitoryRequestDto));
    }

}
