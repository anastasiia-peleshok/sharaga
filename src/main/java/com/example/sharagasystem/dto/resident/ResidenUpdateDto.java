package com.example.sharagasystem.dto.resident;


import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ResidenUpdateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phoneNumber;
    private String roomNumber;
    private String dormitoryName;
}
