package com.example.sharagasystem.security.controller;

import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

//    @PostMapping
//    public User createUser(@RequestBody UserRequestDto requestDto) {
////        return userService
//    }

}
