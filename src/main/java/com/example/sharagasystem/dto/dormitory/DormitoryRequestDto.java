package com.example.sharagasystem.dto.dormitory;

import lombok.Data;

@Data
public class DormitoryRequestDto {
    private static final String CANNOT_BE_NULL_MSG = "cannot be null or blank";
    private String address;
    private String name;
}
