package com.example.sharagasystem.dto.room;

import com.example.sharagasystem.model.Gender;
import lombok.Data;

@Data
public class RoomResponseDto {
    private String number;
    private int capacity;
    private String gender;
    private String dormitoryId;
}
