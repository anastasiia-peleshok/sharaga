package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.model.User;
import com.example.sharagasystem.repository.UserRepository;
import java.util.List;

import com.example.sharagasystem.service.UserService;
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
}
