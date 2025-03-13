package com.example.sharagasystem.dto.response;

import com.example.sharagasystem.security.model.User;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DormitoryResponseDto {
    private UUID id;
    private String name;
    private String address;
    private Integer capacity;
    private int amountOfRoom;
    private UserResponseDto commandant;
}
