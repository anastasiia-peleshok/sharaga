package com.example.sharagasystem.security.dto.response;

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
public class UserAuthResponse {
    private UUID id;
    private String email;
    private Role.RoleName role;
}
