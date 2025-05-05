package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.DormitoryRequestDto;
import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.service.DormitoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dormitories")
public class DormitoryController {
    private final DormitoryService dormitoryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Dormitory create(@RequestBody DormitoryRequestDto dormitoryRequestDto) {
        return dormitoryService.create(dormitoryRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Dormitory> getAll() {
        return dormitoryService.findAll();
    }

}
