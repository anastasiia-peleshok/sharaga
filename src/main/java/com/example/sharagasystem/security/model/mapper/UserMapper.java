package com.example.sharagasystem.security.model.mapper;

import com.example.sharagasystem.security.dto.response.UserAuthResponse;
import com.example.sharagasystem.security.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserAuthResponse mapToUserAuthResponse(User user) {
        if(user == null) {
            return null;
        }
        UserAuthResponse userAuthResponse = new UserAuthResponse();
        userAuthResponse.setId(user.getId());
        userAuthResponse.setEmail(user.getEmail());
        userAuthResponse.setRole(user.getRole().getRoleName());
        return userAuthResponse;
    }
}
