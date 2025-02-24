package com.example.sharagasystem.dto.resident;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ResidentResponseDto {
        private static final String CANNOT_BE_NULL_MSG = "cannot be null or blank";
        private String firstName;
        private String lastName;
        private String email;
        private String role;
        private String phoneNumber;
        private String roomNumber;
        private String dormitoryName;


}
