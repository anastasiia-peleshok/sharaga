package com.example.sharagasystem.dto.room;

import com.example.sharagasystem.model.Gender;
import lombok.Data;

@Data
public class RoomRequestDto {
    private String number;
    private int capacity;
    private Gender gender;
    private String dormitoryId;
}
