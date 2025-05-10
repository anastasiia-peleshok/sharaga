package com.example.sharagasystem.model.dto.request;

import com.example.sharagasystem.model.Gender;
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

//    private String nameDormitory;
}
