package com.example.sharagasystem.model.dto.request.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceCreationRequestDto {
    private String name;
    private BigDecimal monthlyRate;
}
