package com.example.sharagasystem.security.service;

import com.example.sharagasystem.security.dto.AuthenticationRequest;
import com.example.sharagasystem.security.dto.AuthenticationResponse;
import com.example.sharagasystem.security.dto.RegistrationRequest;

public interface AuthenticationService {

    AuthenticationResponse register(RegistrationRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
