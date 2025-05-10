package com.example.sharagasystem.security.service;

import com.example.sharagasystem.security.dto.request.AuthenticationRequest;
import com.example.sharagasystem.security.dto.request.RefreshTokenRequest;
import com.example.sharagasystem.security.dto.request.RegistrationRequest;
import com.example.sharagasystem.security.dto.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    AuthenticationResponse refresh(RefreshTokenRequest request);
}
