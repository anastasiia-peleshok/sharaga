package com.example.sharagasystem.security.service;

import com.example.sharagasystem.security.dto.request.UserRequestDto;
import com.example.sharagasystem.security.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createUser(UserRequestDto userRequestDto);

    User findByEmail(String email);

}
