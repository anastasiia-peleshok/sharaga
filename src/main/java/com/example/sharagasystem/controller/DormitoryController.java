package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.service.DormitoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dormitory")
public class DormitoryController {
    private final DormitoryService dormitoryService;

    @PostMapping
    public Dormitory create(@RequestBody DormitoryRequestDto dormitoryRequestDto) {
        return dormitoryService.create(dormitoryRequestDto);
    }

}
