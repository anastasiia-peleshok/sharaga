package com.example.sharagasystem.model.dto.request;

import com.example.sharagasystem.model.Gender;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private UUID dormitoryId;
}
