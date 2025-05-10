package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.request.ResidentRequestDto;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.ResidentResponseDto;
import com.example.sharagasystem.service.ResidentService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/residents")
public class ResidentController {

    private final ResidentService residentService;

    @GetMapping("/{id}")
    public ResponseEntity<ResidentResponseDto> getResidentById(@PathVariable UUID id) {
        return ResponseEntity.ok(residentService.getResidentById(id));
    }

    @GetMapping
    public List<ResidentResponseDto> getResidentByAll() {
        return residentService.getAllResidents();
    }

    @GetMapping("/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<ResidentDetailsLowInfoResponseDto> getResidentsByDormitory(@PathVariable UUID dormitoryId,
                                                                           @RequestParam(required = false, defaultValue = "") String textToSearch,
                                                                           Pageable pageable) {
        Page<ResidentDetailsLowInfoResponseDto> residentsByDormitory = residentService.getResidentsByDormitory(dormitoryId, textToSearch, pageable);
        return residentsByDormitory;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<ResidentResponseDto> assignResidentToDormitory(@RequestBody ResidentRequestDto residentRequestDto) {
        return ResponseEntity.ok(residentService.saveResident(residentRequestDto));
    }

    @PatchMapping("/{residentId}/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void addResidentToDormitory(@PathVariable UUID residentId,
                                       @PathVariable UUID dormitoryId) {
        residentService.addResidentToDormitory(residentId, dormitoryId);
    }
}
