package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NameAlreadyTakenException;
import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.ServiceMapper;
import com.example.sharagasystem.model.dto.request.service.ServiceCreationRequestDto;
import com.example.sharagasystem.model.dto.request.service.ServiceUpdateRequestDto;
import com.example.sharagasystem.model.dto.response.service.ServiceResponseDto;
import com.example.sharagasystem.repository.ServiceRepository;
import com.example.sharagasystem.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.management.ServiceNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public List<ServiceResponseDto> findAllServices() {
        return serviceRepository.findAll().stream()
                .map(ServiceMapper::mapToServiceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceResponseDto findServiceById(UUID id) {
        com.example.sharagasystem.model.Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Service with id " + id + " not found"));

        return ServiceMapper.mapToServiceResponse(service);

    }

    @Override
    public ServiceResponseDto addService(ServiceCreationRequestDto serviceCreationDto) {
        String name = serviceCreationDto.getName();
        Optional<com.example.sharagasystem.model.Service> service = serviceRepository.findByName(name);
        if (service.isPresent()) {
            throw new NameAlreadyTakenException("There is already present service with name " + name);
        }
        com.example.sharagasystem.model.Service savedService = serviceRepository.save(ServiceMapper.mapToService(serviceCreationDto));
        return ServiceMapper.mapToServiceResponse(savedService);
    }

    @Override
    public ServiceResponseDto updateService(UUID serviceId, ServiceUpdateRequestDto serviceUpdateRequestDto) {
        com.example.sharagasystem.model.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new NotFoundException("Service with id " + serviceId + " not found"));
        if (serviceUpdateRequestDto.getName() != null) {
            service.setName(serviceUpdateRequestDto.getName());
        }
        if (serviceUpdateRequestDto.getMonthlyRate() != null) {
            service.setMonthlyRate(serviceUpdateRequestDto.getMonthlyRate());
        }
        com.example.sharagasystem.model.Service updatedService = serviceRepository.save(service);
        return ServiceMapper.mapToServiceResponse(updatedService);
    }

    @Override
    public void deleteService(UUID serviceId) {
        com.example.sharagasystem.model.Service service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new NotFoundException("Service with id " + serviceId + " not found"));
        serviceRepository.delete(service);
    }
}
