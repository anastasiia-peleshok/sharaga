package com.example.sharagasystem.model.dto.response;

import com.example.sharagasystem.security.model.Role;
import jakarta.annotation.Nullable;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentResponseDto {
        private UUID id;
        private String firstName;
        private String lastName;
        private String email;
        private Role role;
        private Integer yearsInUniversity;
        @Nullable
        private String phoneNumber;
        @Nullable
        private String roomNumber;
        private String dormitoryName;
}
