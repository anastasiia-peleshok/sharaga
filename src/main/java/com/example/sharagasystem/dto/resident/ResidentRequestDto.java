package com.example.sharagasystem.dto.resident;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ResidentRequestDto {
    private static final String CANNOT_BE_NULL_MSG = "cannot be null or blank";
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String email;
    @Nullable
    private String role;
    @Nullable
    private String phoneNumber;
    @Nullable
    private String roomNumber;
    @Nullable
    private String dormitoryName;

}
