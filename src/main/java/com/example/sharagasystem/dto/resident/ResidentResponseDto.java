package com.example.sharagasystem.dto.resident;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class ResidentResponseDto {
        private String firstName;
        private String lastName;
        private String email;
        private String role;
        private String phoneId;
        private String roomId;
        private String dormitoryId;


}
