package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.request.staff.StaffRequestDto;
import com.example.sharagasystem.model.dto.response.staff.StaffDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.staff.StaffResponseDto;
import com.example.sharagasystem.service.StaffDetailsService;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffDetailsController {
    private final StaffDetailsService staffDetailsService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public StaffResponseDto createStaff(@RequestBody @Valid StaffRequestDto requestDto) {
        log.info("Entering POST /staff");
        return staffDetailsService.create(requestDto);
    }

    @PatchMapping("/{staffId}/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void assignStaffToDormitory(@PathVariable UUID staffId,
                                       @PathVariable UUID dormitoryId) {
        staffDetailsService.assignStaffToDormitory(staffId, dormitoryId);
    }

    @GetMapping("/dormitory/{dormitoryId}")
    public Page<StaffDetailsLowInfoResponseDto> findAllByDormitory(@PathVariable UUID dormitoryId,
                                                                   @RequestParam(required = false, defaultValue = "") String textToSearch,
                                                                   Pageable pageable) {
        return staffDetailsService.findAllByDormitory(dormitoryId, textToSearch, pageable);
    }
}
