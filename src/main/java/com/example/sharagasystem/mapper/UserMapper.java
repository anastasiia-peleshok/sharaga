package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.response.UserResponseDto;
import com.example.sharagasystem.security.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserResponseDto toUserResponseDto(User user) {
        if(user == null){
            return null;
        }
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setFirstName(user.getFirstName());
        userResponseDto.setSecondName(userResponseDto.getSecondName());
        userResponseDto.setEmail(user.getEmail());
        return userResponseDto;
    }
}
