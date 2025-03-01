package com.example.sharagasystem.model.dto.request;

import lombok.Data;

@Data
public class DormitoryRequestDto {
    private String name;
    private String address;
    private String zipCode;
    private Integer floors;
    private String city;
    private Double price;
    private Integer capacity;
}
