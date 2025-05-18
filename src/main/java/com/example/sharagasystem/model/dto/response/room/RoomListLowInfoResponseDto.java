package com.example.sharagasystem.model.dto.response.room;

import com.example.sharagasystem.model.Gender;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomListLowInfoResponseDto {
    private UUID id;
    private String number;
    private Gender gender;
    private Integer free;
}
