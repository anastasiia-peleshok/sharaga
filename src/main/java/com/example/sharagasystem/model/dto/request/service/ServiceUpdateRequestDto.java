package com.example.sharagasystem.model.dto.request.service;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceUpdateRequestDto {
    @Nullable
    private String name;
    @Nullable
    private BigDecimal monthlyRate;
}
