package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.request.service.ServiceCreationRequestDto;
import com.example.sharagasystem.model.dto.response.service.ServiceResponseDto;
import com.example.sharagasystem.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/services")
public class ServiceController {
    private final ServiceService serviceService;

    @PostMapping
    public ServiceResponseDto createService(@RequestBody ServiceCreationRequestDto requestDto){
        return serviceService.addService(requestDto);
    }

    @GetMapping("/{serviceId}")
    public ServiceResponseDto getService(@PathVariable UUID serviceId){
        return serviceService.findServiceById(serviceId);
    }
}
