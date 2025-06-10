package com.example.sharagasystem.model.dto.response.room;

import com.example.sharagasystem.model.Gender;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.furniture.FurnitureLowInfoResponseDto;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponseDto {
    private UUID id;
    private String number;
    private Integer capacity;
    private Gender gender;
    private List<ResidentDetailsLowInfoResponseDto> residents;
    private List<FurnitureLowInfoResponseDto> furnitures;
}
