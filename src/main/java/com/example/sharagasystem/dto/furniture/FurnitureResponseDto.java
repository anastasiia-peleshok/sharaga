package com.example.sharagasystem.dto.furniture;

import lombok.Data;

@Data
public class FurnitureResponseDto {
    private String name;
    private String itemNumber;
    private String furnitureType;
    private String roomId;
    private String userId;
    private String dormitoryId;
}
