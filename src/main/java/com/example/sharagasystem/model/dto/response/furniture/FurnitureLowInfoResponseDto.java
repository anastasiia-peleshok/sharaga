package com.example.sharagasystem.model.dto.response.furniture;

import com.example.sharagasystem.model.FurnitureType;
import com.example.sharagasystem.model.enums.StatusType;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FurnitureLowInfoResponseDto {
    private UUID id;
    private FurnitureType type;
    private StatusType status;
    private String roomNumber;
    private Double price;
    private String itemNumber;
}
