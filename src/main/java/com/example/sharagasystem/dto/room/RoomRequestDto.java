package com.example.sharagasystem.dto.room;

import com.example.sharagasystem.model.Gender;
import lombok.Data;

@Data
public class RoomRequestDto {
    private static final String CANNOT_BE_NULL_MSG = "cannot be null or blank";
    private String number;
    private int capacity;
    private Gender gender;
    private String dormitoryId;
}
