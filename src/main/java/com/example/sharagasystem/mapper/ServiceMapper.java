package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Service;
import com.example.sharagasystem.model.dto.request.service.ServiceCreationRequestDto;
import com.example.sharagasystem.model.dto.response.service.ServiceResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    public static Service mapToService(ServiceCreationRequestDto serviceCreationRequestDto) {
        Service service = new Service();
        service.setName(serviceCreationRequestDto.getName());
        service.setMonthlyRate(serviceCreationRequestDto.getMonthlyRate());
        return service;
    }

    public static ServiceResponseDto mapToServiceResponse (Service service) {
        ServiceResponseDto serviceResponseDto = new ServiceResponseDto();
        serviceResponseDto.setId(service.getId());
        serviceResponseDto.setName(service.getName());
        serviceResponseDto.setMonthlyRate(service.getMonthlyRate());
        return serviceResponseDto;
    }

}
