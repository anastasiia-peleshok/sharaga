package com.example.sharagasystem.model.dto.response.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponseDto {
    private UUID id;
    private String name;
    private BigDecimal monthlyRate;
}
