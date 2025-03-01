package com.example.sharagasystem.security.service.impl;

import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.repository.UserRepository;

import java.util.List;

import com.example.sharagasystem.security.service.UserService;
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
