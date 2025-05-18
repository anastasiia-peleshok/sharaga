package com.example.sharagasystem.model.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResidentDetailsLowInfoResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String room;
    private Integer yearsInUniversity;
    private Double debt;
    private Double penaltyPoints;
}
