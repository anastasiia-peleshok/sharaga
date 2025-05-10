package com.example.sharagasystem.model.dto.response.staff;

import com.example.sharagasystem.model.enums.StaffType;
import com.example.sharagasystem.security.model.Role;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffResponseDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String phoneNumber;
    private StaffType staffType;

}
