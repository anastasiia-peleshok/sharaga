package com.example.sharagasystem.dto;

import com.example.sharagasystem.model.Gender;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDto {
    private String roomNumber;
    private int capacity;
    private Gender gender;
    // TODO: add this when be some data
//    private UUID dormitoryId;

    private String nameDormitory;
}
