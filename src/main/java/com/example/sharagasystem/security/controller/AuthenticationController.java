package com.example.sharagasystem.security.controller;

import com.example.sharagasystem.security.dto.request.AuthenticationRequest;
import com.example.sharagasystem.security.dto.request.RefreshTokenRequest;
import com.example.sharagasystem.security.dto.request.RegistrationRequest;
import com.example.sharagasystem.security.dto.response.AuthenticationResponse;
import com.example.sharagasystem.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public AuthenticationResponse register(@RequestBody RegistrationRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    @PreAuthorize("permitAll()")
    public AuthenticationResponse register(@RequestBody AuthenticationRequest request){
        return authenticationService.authenticate(request);
    }

    @PostMapping("/refresh")
    @PreAuthorize("permitAll()")
    public AuthenticationResponse refresh(@RequestBody RefreshTokenRequest request){
        log.info("Entering POST /api/v1/auth/refresh");
        return authenticationService.refresh(request);
    }
}
