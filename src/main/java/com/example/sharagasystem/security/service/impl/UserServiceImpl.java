package com.example.sharagasystem.security.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.security.dto.request.UserRequestDto;
import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.repository.UserRepository;
import com.example.sharagasystem.security.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }

    @Override
    public Optional<User> findByEmailUser(String email) {
        return userRepository.findByEmail(email);
    }


}
