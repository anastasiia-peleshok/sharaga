package com.example.sharagasystem.service;

import com.example.sharagasystem.model.dto.request.service.ServiceCreationRequestDto;
import com.example.sharagasystem.model.dto.request.service.ServiceUpdateRequestDto;
import com.example.sharagasystem.model.dto.response.service.ServiceResponseDto;

import java.util.List;
import java.util.UUID;

public interface ServiceService {

    List<ServiceResponseDto> findAllServices();
    ServiceResponseDto findServiceById(UUID id);
    ServiceResponseDto addService(ServiceCreationRequestDto serviceCreationDto);
    ServiceResponseDto updateService(UUID serviceId, ServiceUpdateRequestDto serviceUpdateRequestDto);
    void deleteService(UUID serviceId);

}
