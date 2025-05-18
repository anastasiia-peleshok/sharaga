package com.example.sharagasystem.security.model.mapper;

import com.example.sharagasystem.security.dto.response.UserAuthResponse;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "role", qualifiedByName = "extractRoleName")
    UserAuthResponse mapToUserAuthResponse(User user);

    @Named("extractRoleName")
    default Role.RoleName extractRoleName(Role role) {
        return role != null ? role.getRoleName() : null;
    }
}
