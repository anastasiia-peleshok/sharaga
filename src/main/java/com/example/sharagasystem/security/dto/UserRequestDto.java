package com.example.sharagasystem.security.dto;

import com.example.sharagasystem.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
