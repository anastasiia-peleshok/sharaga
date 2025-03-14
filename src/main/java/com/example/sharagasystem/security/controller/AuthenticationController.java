package com.example.sharagasystem.security.controller;

import com.example.sharagasystem.security.dto.AuthenticationRequest;
import com.example.sharagasystem.security.dto.AuthenticationResponse;
import com.example.sharagasystem.security.dto.RegistrationRequest;
import com.example.sharagasystem.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponse register(@RequestBody RegistrationRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse register(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }
}
