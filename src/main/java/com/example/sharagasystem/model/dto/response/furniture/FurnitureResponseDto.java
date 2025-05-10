package com.example.sharagasystem.model.dto.response.furniture;

import com.example.sharagasystem.model.dto.response.dormitory.DormitoryLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomLowInfoResponseDto;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FurnitureResponseDto {
    private UUID id;
    private String name;
    private String itemNumber;
    private String furnitureType;
    private Double price;
    private String status;
    private RoomLowInfoResponseDto room;
    private DormitoryLowInfoResponseDto dormitory;
}
