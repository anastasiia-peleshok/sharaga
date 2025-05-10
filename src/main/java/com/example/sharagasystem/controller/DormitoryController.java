package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.Dormitory;
import com.example.sharagasystem.model.dto.request.DormitoryRequestDto;
import com.example.sharagasystem.model.dto.response.DormitoryResponseDto;
import com.example.sharagasystem.service.DormitoryService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public List<DormitoryResponseDto> getAll() {
        return dormitoryService.findAllWithDeleteIsNull();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public DormitoryResponseDto getById(@PathVariable UUID id) {
        return dormitoryService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void delete(@PathVariable UUID id) {
        dormitoryService.softDelete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void update(@PathVariable UUID id, @RequestBody DormitoryRequestDto dormitoryRequestDto) {
        dormitoryService.update(id, dormitoryRequestDto);
    }
}
