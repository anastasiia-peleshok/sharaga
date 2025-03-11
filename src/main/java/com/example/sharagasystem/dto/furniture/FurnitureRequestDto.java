package com.example.sharagasystem.dto.furniture;

import com.example.sharagasystem.model.FurnitureType;
import lombok.Data;

@Data
public class FurnitureRequestDto {
    private String name;
    private FurnitureType furnitureType;
    private String roomId;
    private String userId;
    private String dormitoryId;
}
