package com.example.sharagasystem.security.service.impl;

import com.example.sharagasystem.security.dto.UserRequestDto;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.repository.UserRepository;
import com.example.sharagasystem.security.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(UserRequestDto user) {
        User newUser = new User();
//        Role role =
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
//        new
        return userRepository.save(newUser);
    }
}
