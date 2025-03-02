package com.example.sharagasystem.dto.resident;

import lombok.Data;

@Data
public class ResidentRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String phoneNumber;
    private String roomId;
    private String dormitoryId;

}
