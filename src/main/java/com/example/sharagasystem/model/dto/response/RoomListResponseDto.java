package com.example.sharagasystem.model.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomListResponseDto {
    private UUID id;
    private String number;
    private Integer capacity;
    private String gender;
    private Integer freeSeats;
    private Integer occupiedSeats;
}
