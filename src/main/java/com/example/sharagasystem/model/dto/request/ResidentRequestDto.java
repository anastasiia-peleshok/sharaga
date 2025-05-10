package com.example.sharagasystem.model.dto.request;

import com.example.sharagasystem.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResidentRequestDto {
//    @Nullable
    private String firstName;
//    @Nullable
    private String lastName;
//    @Nullable
    private String email;
//    @Nullable
    private Role.RoleName role;
//    @Nullable
    private String phoneNumber;
//    @Nullable
    private String roomNumber;
//    @Nullable
    private String dormitoryName;

}
