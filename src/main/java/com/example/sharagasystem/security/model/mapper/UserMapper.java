package com.example.sharagasystem.security.model.mapper;

import com.example.sharagasystem.security.dto.response.UserAuthResponse;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "role.roleName", target = "role")
    public UserAuthResponse mapToUserAuthResponse(User user);
//        if(user == null) {
//            return null;
//        }
//        UserAuthResponse userAuthResponse = new UserAuthResponse();
//        userAuthResponse.setId(user.getId());
//        userAuthResponse.setEmail(user.getEmail());
//        userAuthResponse.setRole(user.getRole().getRoleName());
//        return userAuthResponse;
//    }
default Role map(String value) {
    Role role = new Role();
    role.setRoleName(Role.RoleName.valueOf(value.toUpperCase()));
    return role;
}
}
