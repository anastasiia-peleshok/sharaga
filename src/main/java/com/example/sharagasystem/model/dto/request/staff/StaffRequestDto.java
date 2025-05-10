package com.example.sharagasystem.model.dto.request.staff;

import com.example.sharagasystem.model.enums.StaffType;
import com.example.sharagasystem.security.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffRequestDto {
    private String firstName;
    private String lastName;
    @NotBlank(message = "cannot be null or empty")
    private String email;
    @NotNull(message = "cannot be null")
    private Role.RoleName role;
    private String phoneNumber;
    @NotNull(message = "cannot be null")
    private StaffType type;
}
