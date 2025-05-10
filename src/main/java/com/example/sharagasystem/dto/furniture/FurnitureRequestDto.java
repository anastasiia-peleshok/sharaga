package com.example.sharagasystem.dto.furniture;

import lombok.Data;

@Data
public class FurnitureRequestDto {
    private String name;
    private String roomId;
    private String userId;
    private String dormitoryId;
}
