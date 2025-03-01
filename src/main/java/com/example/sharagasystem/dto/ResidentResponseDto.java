package com.example.sharagasystem.dto;

import com.example.sharagasystem.security.model.Role;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentResponseDto {
        private String firstName;
        private String lastName;
        private String email;
        private Role role;
        @Nullable
        private String phoneNumber;
        @Nullable
        private String roomNumber;
        private String dormitoryName;
}
