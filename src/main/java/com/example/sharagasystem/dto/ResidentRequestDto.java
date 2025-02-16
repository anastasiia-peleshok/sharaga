package com.example.sharagasystem.dto;

import com.example.sharagasystem.security.model.Role;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResidentRequestDto {
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String email;
    @Nullable
    private Role role;
    @Nullable
    private String phoneNumber;
    @Nullable
    private String roomNumber;
    @Nullable
    private String dormitoryName;

}
