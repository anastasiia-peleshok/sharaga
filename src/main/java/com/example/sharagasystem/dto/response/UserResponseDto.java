package com.example.sharagasystem.dto.response;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {
    private UUID id;
    private String firstName;
    private String secondName;
    private String email;
}
