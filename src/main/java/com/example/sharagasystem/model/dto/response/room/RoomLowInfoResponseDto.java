package com.example.sharagasystem.model.dto.response.room;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomLowInfoResponseDto {
    private UUID id;
    private String number;
}
