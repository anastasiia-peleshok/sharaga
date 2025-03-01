package com.example.sharagasystem.model.dto.response;

import java.util.UUID;
import lombok.Data;

@Data
public class DormitoryResponseDto {
    private UUID id;
    private String name;
    private String address;
    private String zipCode;
    private Integer floors;
    private String city;
    private Double price;
    private Integer capacity;
}
