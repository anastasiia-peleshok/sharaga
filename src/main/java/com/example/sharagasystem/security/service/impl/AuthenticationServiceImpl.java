package com.example.sharagasystem.security.service.impl;

import com.example.sharagasystem.exception.AuthenticationException;
import com.example.sharagasystem.security.dto.AuthenticationRequest;
import com.example.sharagasystem.security.dto.AuthenticationResponse;
import com.example.sharagasystem.security.dto.RegistrationRequest;
import com.example.sharagasystem.security.jwt.JwtService;
import com.example.sharagasystem.security.model.Role;
import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.repository.RoleRepository;
import com.example.sharagasystem.security.repository.UserRepository;
import com.example.sharagasystem.security.service.AuthenticationService;
import com.example.sharagasystem.security.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Override
    public AuthenticationResponse register(RegistrationRequest request) {
        User user = new User();
        final Optional<User> userFromDb = userRepository.findByEmail(request.getEmail());
        if(userFromDb.isPresent() && userFromDb.get().isActive()) {
            throw new AuthenticationException("This email has already been used before");
        }
        user.setRole(roleRepository.findByName(Role.RoleName.ADMIN)
                .orElseThrow(() -> new AuthenticationException("Role Not Found")));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userService.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
